//
//  WelcomeView.swift
//  iosApp
//
//  Created by Айдан Галеев on 25.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct WelcomeView: View {
    let component: WelcomeComponent
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            VStack(spacing: 40) {
                Spacer()
                
                // Welcome title
                VStack(spacing: 20) {

                    Text("Welcome to Cemp")
                        .font(.system(size: 28, weight: .bold))
                        .foregroundColor(theme.textColor)
                        .multilineTextAlignment(.center)
                }
                .padding(.vertical, 30)

                
                VStack(spacing: 20) {
                    // Log In button
                    CustomButton(
                        "Log In",
                        style: .primary,
                        isLoading: false
                    ) {
                        component.onIntent(welcomeIntent: WelcomeComponentIntentLoginClicked())
                    }
                    
                    // Sign Up button
                    CustomButton(
                        "Sign Up",
                        style: .secondary,
                        isLoading: false
                    ) {
                        component.onIntent(welcomeIntent: WelcomeComponentIntentRegisterClicked())
                    }
                }
                .padding(.horizontal, 40)
                .padding(.bottom, 80)

                Spacer()
            }
        }
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
}

