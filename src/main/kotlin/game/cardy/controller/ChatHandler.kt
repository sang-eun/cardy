package game.cardy.controller

import com.google.gson.Gson
import game.cardy.domain.ChatRooms
import game.cardy.domain.MessageType
import game.cardy.domain.dto.MessageRequestDto
import game.cardy.domain.message.EnterMessage
import game.cardy.domain.message.LeaveMessage
import game.cardy.domain.message.NormalMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.TextMessage

import org.springframework.web.socket.WebSocketSession
import java.lang.Exception

//닉네임 중복 및 8명 넘을경우 처리
//상대편에서 연결 끊으면 자동으로 LEAVE메시지 받은것처럼 처리
//requestDto 막넣어도 잘돌아가는데 검사좀
class ChatHandler: TextWebSocketHandler() {

    @Autowired
    private lateinit var chatRooms: ChatRooms
    private val gson = Gson()

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, textMessage: TextMessage) {
        val messageRequestDto = gson.fromJson(textMessage.payload, MessageRequestDto::class.java)
        val sender = chatRooms.getPlayer(session, messageRequestDto.playerName, messageRequestDto.roomId)

        val message = when(messageRequestDto.messageType) {
            MessageType.ENTER -> EnterMessage(sender, chatRooms)
            MessageType.LEAVE -> LeaveMessage(sender, chatRooms)
            else -> NormalMessage(sender, messageRequestDto.content!!, chatRooms)
        }

        message.processMessage()
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val leaver = chatRooms.getPlayer(session, null, null)
        val message = LeaveMessage(leaver, chatRooms)
        message.processMessage()
    }
}
