//
//  AuthView.swift
//  iosApp
//
//  Created by Айдан Галеев on 23.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct AuthView: View {
    let component: AuthComponent
    @ObservedObject var stack: ObservableValue<ChildStack<AnyObject, AuthComponentChild>>
    
    init(component: AuthComponent) {
        self.component = component
        self.stack = ObservableValue(component.childStack)
    }

    var body: some View {
        ZStack {
            switch stack.value.active.instance {
            case let welcome as AuthComponentChildWelcome:
                WelcomeView(component: welcome.component)
            case let login as AuthComponentChildLogin:
                LoginView(component: login.component)
            case let registration as AuthComponentChildRegister:
                RegistrationView(component: registration.component)
            default:
                Text("Unknown screen")
            }
        }
    }
}
