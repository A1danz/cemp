package utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object GlobalEvents {
    private val _channel = Channel<GlobalEvent>(Channel.CONFLATED)
    val channel: Flow<GlobalEvent> = _channel.receiveAsFlow()

    suspend fun send(event: GlobalEvent) {
        _channel.send(event)
    }

}

sealed interface GlobalEvent {
    data object NavigateToLogin : GlobalEvent
}