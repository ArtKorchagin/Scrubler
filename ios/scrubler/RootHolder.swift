//
//  RootHolder.swift
//  scrubler
//
//  Created by Корчагин Артур on 15.07.2023.
//

import UIKit
import SwiftUI
import common

class RootHolder : ObservableObject {
    
    let root: RootComponent
    let lifecycle: LifecycleRegistry

    init() {
        Main_iosKt.InitKoinDi()
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        root = DefaultRootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle)
            // ,storeFactory: Main_iosKt.buildStoreFactory()
        )
        LifecycleRegistryExtKt.create(lifecycle)
    }

    deinit {
        // Destroy the root component before it is deallocated
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
