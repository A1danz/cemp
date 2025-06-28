//
//  CustomTextField.swift
//  iosApp
//
//  Created by Нияз Ризванов on 20.06.2025.
//

import SwiftUI

struct CustomTextField: View {
    let placeholder: String
    @Binding var text: String
    let isSecure: Bool
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    init(_ placeholder: String, text: Binding<String>, isSecure: Bool = false) {
        self.placeholder = placeholder
        self._text = text
        self.isSecure = isSecure
    }
    
    var body: some View {
        Group {
            if isSecure {
                SecureField(placeholder, text: $text)
            } else {
                TextField(placeholder, text: $text)
            }
        }
        .font(theme.text16SemiBold)
        .foregroundColor(theme.textColor)
        .autocapitalization(.none)
        .padding()
        .background(theme.secondaryBackground)
        .cornerRadius(20)
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
} 
