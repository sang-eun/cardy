package game.cardy.domain.message

import game.cardy.annotation.Message
import game.cardy.domain.ChatRooms
import game.cardy.domain.Player
import game.cardy.domain.dto.MessageResponseDto
import game.cardy.util.GsonUtils
import org.springframework.web.socket.TextMessage

@Message
class EnterMessage(player: Player, chatRooms: ChatRooms) : MessageFrame(player, chatRooms) {

    override fun processPlayerRequest() {
        if(player.roomId == null) {
            val roomId = chatRooms.getNewRoomId()
            player.roomId = roomId
        }
        chatRooms.enterChannel(player)
        player.session.sendMessage(TextMessage(GsonUtils.toJson(MessageResponseDto("success", player.roomId, null))))
    }

    override fun getProcessedContent(): String {
        return "${player.name}이 입장하셨습니다."
    }
}
