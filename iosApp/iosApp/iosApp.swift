import Foundation
import SwiftUI
import ComposeApp
//import FirebaseCore

@main
struct iOSApp: App {
    
    init() {
//        FirebaseApp.configure()
        SharedDIKt.doInitKoin()
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
