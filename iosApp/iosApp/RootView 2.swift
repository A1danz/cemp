//
//  RootView 2.swift
//  iosApp
//
//  Created by Айдан Галеев on 23.06.2025.
//

import SwiftUI
import ComposeApp

struct RootView: View {
    @ObservedObject var stack: ObservableValue<ChildStack<AnyObject, RootComponentChild>>

    var body: some View {
        ZStack {
            switch stack.value.active.instance {
            case let auth as RootComponentChildAuth:
                AuthView(component: auth.component)
            default:
                Text("Unknown screen")
            }
        }
    }
}
