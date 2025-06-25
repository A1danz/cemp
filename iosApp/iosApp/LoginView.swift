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
            TextField("Email", text: Binding(
                    get: { model.email },
                    set: { newValue in
                        component.onIntent(intent: LoginComponentIntentEmailChanged(value: newValue))
                    }
                ))
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .autocapitalization(.none)

            
            SecureField("Password", text: Binding(
                            get: { model.password },
                            set: { newValue in
                                component.onIntent(intent: LoginComponentIntentPasswordChanged(value: newValue))
                            }
                        ))
                        .textFieldStyle(RoundedBorderTextFieldStyle())
            
            Text(model.globalError ?? "global error")
            
           Button("Log in") {
               component.onIntent(intent: LoginComponentIntentLoginClicked())
           }
        }
    }
}
