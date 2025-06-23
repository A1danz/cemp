import UIKit

class WelcomeViewController: UIViewController {
    private let component: WelcomeComponent

    init(component: WelcomeComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
        view.backgroundColor = .systemBackground
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        let loginButton = UIButton(type: .system)
        loginButton.setTitle("Login", for: .normal)
        loginButton.addTarget(self, action: #selector(onLogin), for: .touchUpInside)

        let registerButton = UIButton(type: .system)
        registerButton.setTitle("Register", for: .normal)
        registerButton.addTarget(self, action: #selector(onRegister), for: .touchUpInside)

        let stack = UIStackView(arrangedSubviews: [loginButton, registerButton])
        stack.axis = .vertical
        stack.spacing = 16
        stack.translatesAutoresizingMaskIntoConstraints = false
        stack.alignment = .center

        view.addSubview(stack)

        NSLayoutConstraint.activate([
            stack.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            stack.centerYAnchor.constraint(equalTo: view.centerYAnchor),
        ])
    }

    @objc private func onLogin() {
        component.onLoginClick()
    }

    @objc private func onRegister() {
        component.onRegisterClick()
    }
}
