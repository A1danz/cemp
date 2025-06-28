//
//  LoginView.swift
//  iosApp
//
//  Created by Айдан Галеев on 25.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

// MARK: - Login View
struct LoginView: View {
    let component: LoginComponent
    
    @StateValue
    private var model: LoginComponentModel
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    @Environment(\.presentationMode) var presentationMode

    init(component: LoginComponent) {
        self.component = component
        _model = StateValue(component.model)
    }
    
    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            VStack(spacing: 30) {

                Spacer()

                // Title
                VStack(spacing: 10) {
                    Text("Cemp")
                        .font(theme.text36ExtraBold)
                        .foregroundColor(theme.textColor)
                    
                    Text("Вход")
                        .font(theme.text36ExtraBold)
                        .foregroundColor(theme.textColor)
                }
                .padding(.top, 50)
                

                
                VStack(spacing: 20) {
                    // Email field
                    CustomTextField(
                        "Email", 
                        text: Binding(
                            get: { model.email },
                            set: { newValue in
                                component.onIntent(loginIntent: LoginComponentIntentEmailChanged(value: newValue))
                            }
                        )
                    )
                    
                    // Error for email
                    if let emailError = model.emailError?.localized(), !emailError.isEmpty {
                        Text(emailError)
                            .font(theme.text12Medium)
                            .foregroundColor(.red)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    
                    // Password field
                    CustomTextField(
                        "Пароль", 
                        text: Binding(
                            get: { model.password },
                            set: { newValue in
                                component.onIntent(loginIntent: LoginComponentIntentPasswordChanged(value: newValue))
                            }
                        ), 
                        isSecure: true
                    )
                    
                    // Error for password
                    if let passwordError = model.passwordError?.localized(), !passwordError.isEmpty {
                        Text(passwordError)
                            .font(theme.text12Medium)
                            .foregroundColor(.red)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    
                    // Global error message
                    if let globalError = model.globalError?.localized(), !globalError.isEmpty {
                        Text(globalError)
                            .font(theme.text14Medium)
                            .foregroundColor(.red)
                            .multilineTextAlignment(.center)
                    }
                    
                    // Log In button
                    CustomButton(
                        "Войти", 
                        style: .primary, 
                        isLoading: model.isLoading
                    ) {
                        component.onIntent(loginIntent: LoginComponentIntentLoginClicked())
                    }
                }
                .padding(.horizontal, 40)
                
                Spacer()
            }
        }
        .navigationBarHidden(true)
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
}
