//
//  RegistrationView.swift
//  iosApp
//
//  Created by Нияз Ризванов on 20.06.2025.
//

import SwiftUI
import ComposeApp

struct RegistrationView: View {
    let component: RegisterComponent
    
    @StateValue
    private var model: RegisterComponentModel
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    @Environment(\.presentationMode) var presentationMode
    
    init(component: RegisterComponent) {
        self.component = component
        _model = StateValue(component.model)
    }
    
    var body: some View {
        ZStack {
            theme.mainBackground
                .ignoresSafeArea()
            
            VStack(spacing: 30) {
                
                // Title
                VStack(spacing: 10) {
                    Text("Cemp")
                        .font(theme.text36ExtraBold)
                        .foregroundColor(theme.textColor)
                    
                    Text("Registration")
                        .font(theme.text36ExtraBold)
                        .foregroundColor(theme.textColor)
                }
                .padding(.top, 50)

                Spacer()

                VStack(spacing: 20) {
                    // Name field
                    CustomTextField(
                        "Имя", 
                        text: Binding(
                            get: { model.name },
                            set: { newValue in
                                component.onIntent(registerIntent: RegisterComponentIntentNameChanged(value: newValue))
                            }
                        )
                    )
                    
                    // Error for name
                    if let nameError = model.nameError?.localized(), !nameError.isEmpty {
                        Text(nameError)
                            .font(theme.text12Medium)
                            .foregroundColor(.red)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    
                    // Username field
                    CustomTextField(
                        "Имя пользователя", 
                        text: Binding(
                            get: { model.username },
                            set: { newValue in
                                component.onIntent(registerIntent: RegisterComponentIntentUsernameChanged(value: newValue))
                            }
                        )
                    )
                    
                    // Error for username
                    if let usernameError = model.usernameError?.localized(), !usernameError.isEmpty {
                        Text(usernameError)
                            .font(theme.text12Medium)
                            .foregroundColor(.red)
                            .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    
                    // Email field
                    CustomTextField(
                        "Email", 
                        text: Binding(
                            get: { model.email },
                            set: { newValue in
                                component.onIntent(registerIntent: RegisterComponentIntentEmailChanged(value: newValue))
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
                                component.onIntent(registerIntent: RegisterComponentIntentPasswordChanged(value: newValue))
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
                    
                    // Confirm Password field
                    CustomTextField(
                        "Подтвердить пароль", 
                        text: Binding(
                            get: { model.confirmPassword },
                            set: { newValue in
                                component.onIntent(registerIntent: RegisterComponentIntentConfirmPasswordChanged(value: newValue))
                            }
                        ), 
                        isSecure: true
                    )
                    
                    // Error for confirm password
                    if let confirmPasswordError = model.confirmPasswordError?.localized(), !confirmPasswordError.isEmpty {
                        Text(confirmPasswordError)
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
                    
                    // Register button
                    CustomButton(
                        "Зарегистрироваться", 
                        style: .primary, 
                        isLoading: model.isLoading
                    ) {
                        component.onIntent(registerIntent: RegisterComponentIntentRegisterClicked())
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
