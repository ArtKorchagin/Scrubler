import UIKit
import SwiftUI
import common

struct ComposeView: UIViewControllerRepresentable {

    let rootComponent: RootComponent
    
    init(rootComponent: RootComponent)
    {
        self.rootComponent = rootComponent
    }
    
    func makeUIViewController(context: Context) -> UIViewController
    {
        return Main_iosKt.MainViewController(rootComponent: rootComponent)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    let rootComponent: RootComponent
    
    init(rootComponent: RootComponent) {
        self.rootComponent = rootComponent
    }
    
    var body: some View {
        ComposeView(rootComponent: rootComponent)
                .ignoresSafeArea(.all, edges: .bottom)
    }
}
