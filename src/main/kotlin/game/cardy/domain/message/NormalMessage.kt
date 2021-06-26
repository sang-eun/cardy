package game.cardy.domain.message

import game.cardy.annotation.Message
import game.cardy.domain.ChatRooms
import game.cardy.domain.Player

@Message
class NormalMessage(player: Player, private val content: String, chatRooms: ChatRooms) : MessageFrame(player, chatRooms) {

    override fun processPlayerRequest() {
    }

    override fun getProcessedContent(): String {
        return content
    }
}
