import Foundation
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
       // Инициализация Koin при старте iOS
        SharedDIKt.doInitKoin()
       // или если функция называется иначе — используйте HelperKt.doInitKoin()
   }
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
        var appDelegate: AppDelegate
        
        var body: some Scene {
            WindowGroup {
                RootView(stack: ObservableValue(appDelegate.rootHolder.root.childStack))
            }
        }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let rootHolder: RootHolder = RootHolder()
}
