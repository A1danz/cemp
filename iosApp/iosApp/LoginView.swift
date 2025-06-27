//
//  LoginView.swift
//  iosApp
//
//  Created by Айдан Галеев on 25.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct LoginView: View {
    let component: LoginComponent
    
    @StateValue
    private var model: LoginComponentModel
    
    init(component: LoginComponent) {
        self.component = component
        _model = StateValue(component.model)
    }
    
    var body: some View {
        VStack {
            TextField(StringRes.feature_auth_email_placeholder.desc().localized(), text: Binding(
                    get: { model.email },
                    set: { newValue in
                        component.onIntent(loginIntent: LoginComponentIntentEmailChanged(value: newValue))
                    }
                ))
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .autocapitalization(.none)
            
            
            SecureField(StringRes.feature_auth_password_placeholder.desc().localized(), text: Binding(
                            get: { model.password },
                            set: { newValue in
                                component.onIntent(loginIntent: LoginComponentIntentPasswordChanged(value: newValue))
                            }
                        ))
                        .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Text(model.globalError?.localized() ?? "global error")
            Text(model.emailError?.localized() ?? "email error")
            Text(model.passwordError?.localized() ?? "password error")
            
            Button(StringRes.feature_auth_log_in_btn.desc().localized()) {
               component.onIntent(loginIntent: LoginComponentIntentLoginClicked())
           }
        }
    }
}
