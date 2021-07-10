package game.cardy.domain.message

import game.cardy.domain.ChatRooms
import game.cardy.domain.Player
import game.cardy.domain.dto.EventDistributionDto
import game.cardy.util.GsonUtils

abstract class MessageFrame(protected val player: Player, protected val chatRooms: ChatRooms) {
    open fun processMessage() {
        processPlayerRequest()
        val content = GsonUtils.toJson(getProcessedContent())
        chatRooms.distributeMessage(player, content)
    }

    abstract fun processPlayerRequest()
    abstract fun getProcessedContent(): EventDistributionDto
}
