class RootViewController: UIViewController {
    private let rootComponent: RootComponent
    private let navController = UINavigationController()
    private var unsubscribe: (() -> Void)?

    init(rootComponent: RootComponent) {
        self.rootComponent = rootComponent
        super.init(nibName: nil, bundle: nil)

        addChild(navController)
        view.addSubview(navController.view)
        navController.view.frame = view.bounds
        navController.didMove(toParent: self)

        // Подписка на childStack
        unsubscribe = rootComponent.childStack.subscribe { [weak self] stack in
            guard let self = self else { return }

            switch stack.active {
            case let authChild as RootComponentChildAuth:
                let authVC = AuthViewController(component: authChild.component)
                self.navController.setViewControllers([authVC], animated: true)

            // другие Child’ы могут быть добавлены здесь позже
            default:
                break
            }
        }
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        unsubscribe?()
    }
}
