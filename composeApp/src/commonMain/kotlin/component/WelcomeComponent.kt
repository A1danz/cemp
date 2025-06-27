package component

interface WelcomeComponent {

    fun onIntent(welcomeIntent: Intent)

    sealed interface Intent {
        data object LoginClicked : Intent
        data object RegisterClicked : Intent
    }
}