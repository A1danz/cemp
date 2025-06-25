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

    var body: some View {
        VStack(spacing: 20) {
            Text("Welcome screen")
                .font(.title)
                .padding()

            Button(action: {
                component.onIntent(intent__: WelcomeComponentIntentLoginClicked())
            }) {
                Text("Login")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            Button(action: {
                component.onIntent(intent__: WelcomeComponentIntentRegisterClicked())
            }) {
                Text("Register")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.green)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
        }
        .padding()
    }
}

