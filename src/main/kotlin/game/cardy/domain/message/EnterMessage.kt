package game.cardy.domain.message

import game.cardy.annotation.Message
import game.cardy.domain.ChatRooms
import game.cardy.domain.Player
import game.cardy.domain.dto.EventDistributionDto
import game.cardy.domain.dto.MessageResponseDto
import game.cardy.util.GsonUtils
import org.springframework.web.socket.TextMessage

@Message
class EnterMessage(player: Player, chatRooms: ChatRooms) : MessageFrame(player, chatRooms) {

    override fun processPlayerRequest() {
        if (player.roomId == null) {
            val roomId = chatRooms.getNewRoomId()
            player.roomId = roomId
        }
        val chatRoom = chatRooms.enterChannel(player)
        player.session.sendMessage(TextMessage(GsonUtils.toJson(MessageResponseDto("success", player.roomId, null, chatRoom.toPlayerInfos()))))
    }

    override fun getProcessedContent(): List<EventDistributionDto> {
        return listOf(EventDistributionDto("ENTER", player.name, "${player.name}이 입장하셨습니다."))
    }
}
