import SwiftUI
import common
import Security

@main
struct app_iosApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    @Environment(\.scenePhase)
    var scenePhase: ScenePhase

    var rootHolder: RootHolder { appDelegate.rootHolder }

    var body: some Scene {
        WindowGroup {
            ContentView( rootComponent: rootHolder.root)
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                        case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
                        case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
                        case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
                        @unknown default: break
                    }
            }
        }
    }
}
