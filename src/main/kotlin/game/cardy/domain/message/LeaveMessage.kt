package game.cardy.domain.message

import game.cardy.annotation.Message
import game.cardy.domain.ChatRooms
import game.cardy.domain.Player
import game.cardy.domain.dto.EventDistributionDto

@Message
class LeaveMessage(player: Player, chatRooms: ChatRooms) : MessageFrame(player, chatRooms){

    override fun processPlayerRequest() {
        chatRooms.leaveChannel(player)
    }

    override fun getProcessedContent(): EventDistributionDto {
        return EventDistributionDto("LEAVE", player.name, "${player.name}이 퇴장하셨습니다.")
    }
}
