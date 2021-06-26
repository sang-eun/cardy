package game.cardy.domain.message

import game.cardy.annotation.Message
import game.cardy.domain.ChatRooms
import game.cardy.domain.Player
import game.cardy.domain.dto.MessageResponseDto
import game.cardy.util.GsonUtils
import org.springframework.web.socket.TextMessage

@Message
class LeaveMessage(player: Player, chatRooms: ChatRooms) : MessageFrame(player, chatRooms){

    override fun processPlayerRequest() {
        chatRooms.leaveChannel(player)
    }

    override fun getProcessedContent(): String {
        return "${player.name}이 퇴장하셨습니다."
    }
}
