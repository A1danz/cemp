//
//  RootView.swift
//  iosApp
//
//  Created by Айдан Галеев on 23.06.2025.
//

import SwiftUI
import ComposeApp
import Foundation

struct RootView: View {
    @ObservedObject var stack: ObservableValue<ChildStack<AnyObject, RootComponentChild>>

    var body: some View {
        ZStack {
            switch stack.value.active.instance {
            case let auth as RootComponentChildAuth:
                AuthView(component: auth.component)
            case let main as RootComponentChildMain:
                MainView(component: main.component)
            default:
                Text("Unknown screen")
            }
        }
    }
} 