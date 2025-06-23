import UIKit

class RegisterViewController: UIViewController {
    init(component: RegisterComponent) {
        super.init(nibName: nil, bundle: nil)
        view.backgroundColor = .systemBackground

        let label = UILabel()
        label.text = "Register Screen"
        label.font = UIFont.systemFont(ofSize: 24, weight: .bold)
        label.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(label)

        NSLayoutConstraint.activate([
            label.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor),
        ])
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
