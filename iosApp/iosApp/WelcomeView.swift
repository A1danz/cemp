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
            Image(uiImage: ImageRes.ic_cs.toUIImage()!)
                       .resizable()
                       .frame(width: 28, height: 28)
            
            Text(StringRes.feature_auth_welcome_title.desc().localized())
                .font(.title)
                .padding()

            Button(action: {
                component.onIntent(welcomeIntent: WelcomeComponentIntentLoginClicked())
            }) {
                Text(StringRes.feature_auth_log_in_btn.desc().localized())
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            Button(action: {
                component.onIntent(welcomeIntent: WelcomeComponentIntentRegisterClicked())
            }) {
                Text(StringRes.feature_auth_register_btn.desc().localized())
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

