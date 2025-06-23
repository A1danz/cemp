class AuthViewController: UIViewController {
    private let authComponent: AuthComponent
    private var unsubscribe: (() -> Void)?
    private let navController = UINavigationController()

    init(component: AuthComponent) {
        self.authComponent = component
        super.init(nibName: nil, bundle: nil)

        addChild(navController)
        view.addSubview(navController.view)
        navController.view.frame = view.bounds
        navController.didMove(toParent: self)

        unsubscribe = component.childStack.subscribe { [weak self] stack in
            guard let self = self else { return }

            let childVC: UIViewController
            switch stack.active {
            case let welcome as AuthComponentChildWelcome:
                childVC = WelcomeViewController(component: welcome.component)
            case let login as AuthComponentChildLogin:
                childVC = LoginViewController(component: login.component)
            case let register as AuthComponentChildRegister:
                childVC = RegisterViewController(component: register.component)
            default:
                childVC = UIViewController()
            }

            self.navController.setViewControllers([childVC], animated: true)
        }
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        unsubscribe?()
    }
}
