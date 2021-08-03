package game.cardy.controller

import game.cardy.domain.MessageType
import game.cardy.domain.dto.MessageRequestDto
import game.cardy.domain.message.EnterMessage
import game.cardy.domain.message.LeaveMessage
import game.cardy.domain.message.NormalMessage
import game.cardy.domain.service.RuleService
import game.cardy.util.GsonUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.lang.Exception

@Component
class GameHandler: TextWebSocketHandler() {

    @Autowired
    private lateinit var ruleService: RuleService

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, textMessage: TextMessage) {
        val messageRequestDto = GsonUtils.fromJson(textMessage.payload, Map::class.java)

        val response = when(messageRequestDto["type"]) {
            "GAME_START" -> ruleService.getRule(textMessage.payload)
//            "GET_QUESTION" ->
            else -> "???"
        }
//        val sender = chatRooms.getPlayer(session, messageRequestDto.playerName, messageRequestDto.roomId)
//
//        val message = when(messageRequestDto.messageType) {
//            MessageType.ENTER -> EnterMessage(sender, chatRooms)
//            MessageType.LEAVE -> LeaveMessage(sender, chatRooms)
//            else -> NormalMessage(sender, messageRequestDto.content!!, chatRooms)
//        }
//
//        message.processMessage()

        session.sendMessage(TextMessage(response))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
//        val leaver = chatRooms.getPlayer(session, null, null)
//        val message = LeaveMessage(leaver, chatRooms)
//        message.processMessage()
    }
}
