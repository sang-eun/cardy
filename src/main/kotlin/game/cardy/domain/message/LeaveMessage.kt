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

    override fun getProcessedContent(): List<EventDistributionDto> {
        val events = mutableListOf(EventDistributionDto("LEAVE", player.name, "${player.name}이 퇴장하셨습니다."))
        if(player.name == chatRooms.getHost(player.roomId!!)) {
            events.add(EventDistributionDto("NEW_HOST", player.name, "${player.name}이 새로운 방장으로 선출되셨습니다."))
        }
        return events
    }
}
