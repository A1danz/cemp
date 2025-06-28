//
//  CustomButton.swift
//  iosApp
//
//  Created by Нияз Ризванов on 20.06.2025.
//

import SwiftUI

struct CustomButton: View {
    let title: String
    let action: () -> Void
    let style: ButtonStyle
    let isLoading: Bool
    
    @StateObject private var theme = CEMPTheme()
    @Environment(\.colorScheme) var colorScheme
    
    enum ButtonStyle {
        case primary
        case secondary
    }
    
    init(_ title: String, style: ButtonStyle = .primary, isLoading: Bool = false, action: @escaping () -> Void) {
        self.title = title
        self.style = style
        self.isLoading = isLoading
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            HStack {
                if isLoading {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: textColor))
                        .scaleEffect(0.8)
                } else {
                    Text(title)
                        .font(theme.text16SemiBold)
                        .foregroundColor(textColor)
                }
            }
            .frame(maxWidth: .infinity)
            .frame(height: 55)
            .background(backgroundColor)
            .overlay(overlay)
            .cornerRadius(20)
        }
        .disabled(isLoading)
        .onAppear {
            theme.updateFromSystem(colorScheme)
        }
        .onChange(of: colorScheme) { newScheme in
            theme.updateFromSystem(newScheme)
        }
    }
    
    private var backgroundColor: Color {
        switch style {
        case .primary:
            return theme.isDarkMode == true ? .white : .black
        case .secondary:
            return Color.clear
        }
    }
    
    private var textColor: Color {
        switch style {
        case .primary:
            return  theme.isDarkMode == true ? .black : .white
        case .secondary:
            return theme.isDarkMode == true ? .white : .black
        }
    }
    
    private var overlay: some View {
        Group {
            if style == .secondary {
                RoundedRectangle(cornerRadius: 20)
                    .stroke(theme.isDarkMode == true ? .white : .black, lineWidth: 0.5)
            } else {
                EmptyView()
            }
        }
    }
} 
