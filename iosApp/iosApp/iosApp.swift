import Foundation
import SwiftUI

@main
struct iOSApp: App {
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
