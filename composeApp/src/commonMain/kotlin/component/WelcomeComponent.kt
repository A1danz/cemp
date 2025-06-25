package component

interface WelcomeComponent {

    fun onIntent(intent: Intent)

    sealed interface Intent {
        data object LoginClicked : Intent
        data object RegisterClicked : Intent
    }
}