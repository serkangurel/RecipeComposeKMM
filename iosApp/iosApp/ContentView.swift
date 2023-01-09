import SwiftUI
import shared


struct ContentView: View {

    @StateObject
    private var mainHolder = MainHolder()

    var body: some View {
        UIKitToSwiftUI(component: mainHolder.lifecycleRegistry.mainComponent) {
            print("do nothing")
        }
            .onAppear { mainHolder.lifecycleRegistry.onResume() }
            .onDisappear { mainHolder.lifecycleRegistry.onStop() }
    }

}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

private class MainHolder : ObservableObject {
    let lifecycleRegistry: IosLifecycleRegistry
    
    init() {
        lifecycleRegistry = IosLifecycleRegistry()
    }
    
    deinit {
        lifecycleRegistry.onDestroy()
    }
}
