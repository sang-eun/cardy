package game.cardy.domain.message

import game.cardy.domain.ChatRooms
import game.cardy.domain.Player

abstract class MessageFrame(protected val player: Player, protected val chatRooms: ChatRooms) {
    open fun processMessage() {
        processPlayerRequest()
        val content = getProcessedContent()
        chatRooms.distributeMessage(player, content)
    }

    abstract fun processPlayerRequest()
    abstract fun getProcessedContent(): String
}
