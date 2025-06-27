import SwiftUI
import ComposeApp

extension SwiftUI.Color {
    init(appColor: AppColor) {
        let hex = appColor.hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        
        let r = Double((int >> 16) & 0xFF) / 255
        let g = Double((int >> 8) & 0xFF) / 255
        let b = Double(int & 0xFF) / 255
        
        self.init(red: r, green: g, blue: b)
    }
}

extension Font {
    init(fontSpec: FontSpec) {
        let size = CGFloat(fontSpec.size)
        let weight: Font.Weight = {
            switch fontSpec.weight {
            case 100: return .ultraLight
            case 200: return .thin
            case 300: return .light
            case 400: return .regular
            case 500: return .medium
            case 600: return .semibold
            case 700: return .bold
            case 800: return .heavy
            case 900: return .black
            default: return .regular
            }
        }()
        self = .system(size: size, weight: weight)
    }
}

class CEMPTheme: ObservableObject {
    @Published var isDarkMode: Bool = false
    
    func updateFromSystem(_ colorScheme: ColorScheme) {
        isDarkMode = (colorScheme == .dark)
    }
    
    var mainBackground: SwiftUI.Color {
        Color(appColor: isDarkMode ?
            DarkTheme().mainBackground :
            LightTheme().mainBackground)
    }
    
    var secondaryBackground: SwiftUI.Color {
        Color(appColor: isDarkMode ?
            DarkTheme().secondaryBackground :
            LightTheme().secondaryBackground)
    }
    
    var blueText: SwiftUI.Color {
        Color(appColor: isDarkMode ?
            DarkTheme().blueText :
            LightTheme().blueText)
    }
    
    var textColor: SwiftUI.Color {
        Color(appColor: isDarkMode ?
            DarkTheme().textColor :
            LightTheme().textColor)
    }
    
    var text36ExtraBold: Font { Font(fontSpec: Typography().text36ExtraBold) }
    var text28Bold: Font { Font(fontSpec: Typography().text28Bold) }
    var text28SemiBold: Font { Font(fontSpec: Typography().text28SemiBold) }
    var text24Bold: Font { Font(fontSpec: Typography().text24Bold) }
    var text24Medium: Font { Font(fontSpec: Typography().text24Medium) }
    var text20SemiBold: Font { Font(fontSpec: Typography().text20SemiBold) }
    var text16SemiBold: Font { Font(fontSpec: Typography().text16SemiBold) }
    var text14Bold: Font { Font(fontSpec: Typography().text14Bold) }
    var text14SemiBold: Font { Font(fontSpec: Typography().text14SemiBold) }
    var text14Medium: Font { Font(fontSpec: Typography().text14Medium) }
    var text14MediumPlaceholder: Font { Font(fontSpec: Typography().text14MediumPlaceholder) }
    var text12SemiBold: Font { Font(fontSpec: Typography().text12SemiBold) }
    var text12Medium: Font { Font(fontSpec: Typography().text12Medium) }
} 


// Пример использования:
    // @Environment(\.colorScheme) var colorScheme
    // @StateObject private var theme = CEMPTheme()

    //     var body: some View {
    //     }
    //     .onChange(of: colorScheme) { newScheme in
    //         theme.updateFromSystem(newScheme)
    //     }
    //     .onAppear {
    //         theme.updateFromSystem(colorScheme)
    //     }
