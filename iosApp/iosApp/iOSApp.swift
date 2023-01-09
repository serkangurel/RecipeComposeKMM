import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        RecipeIosAppInitKt.recipeIosAppInit()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
