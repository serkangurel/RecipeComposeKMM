//
//  UIKitToSwiftUI.swift
//  iosApp
//
//  Created by Serkan on 10.01.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import SwiftUI
import shared

struct UIKitToSwiftUI: UIViewControllerRepresentable {
    var component: MainComponent
    private let onClose: () -> ()
    
    init(component: MainComponent, onClose: @escaping () -> ()) {
        self.component = component
        self.onClose = onClose
    }

    func makeUIViewController(context: Context) -> UIViewController {
        return RecipeIosAppKt.recipeIosApp(component: component)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {

    }
}
