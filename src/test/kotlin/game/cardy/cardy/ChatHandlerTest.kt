package game.cardy.cardy

import com.google.gson.Gson
import game.cardy.controller.ChatHandler
import game.cardy.domain.dto.EventDistributionDto
import game.cardy.domain.dto.MessageResponseDto
import game.cardy.domain.message.EnterMessage
import game.cardy.util.GsonUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.socket.*
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatHandlerTest {

//    @Autowired
//    lateinit var mvc:MockMvc

    lateinit var client: WebSocketClient

    val hostMessageQueue = ArrayBlockingQueue<String>(1)
    val player1MessageQueue = ArrayBlockingQueue<String>(1)

    @LocalServerPort
    lateinit var randomServerPort: Optional<String>

    @BeforeEach
    fun setup() {
        client = StandardWebSocketClient()
        println(randomServerPort)
    }

    @Test
    fun enterRoom() {
        // 방 생성
        val hostSession = createSession(hostMessageQueue)
        hostSession.sendMessage(createRoomMessage("ENTER", "host"))
        Thread.sleep(1000)

        val messageResponse = GsonUtils.fromJson(hostMessageQueue.poll(), MessageResponseDto::class.java)
        assert(messageResponse.result == "success")
        val roomId = messageResponse.roomId

        // 방 입장
        val playerSession = createSession(player1MessageQueue)
        playerSession.sendMessage(enterRoomMessage("ENTER", "player1", roomId!!))
        Thread.sleep(1000)

        val enterMessageResponse = GsonUtils.fromJson(player1MessageQueue.poll(), MessageResponseDto::class.java)
        assert(enterMessageResponse.playerInfos[0].isHost)
        assert(enterMessageResponse.playerInfos[0].name == "host")
        val enterMessageEvent = GsonUtils.fromJson(hostMessageQueue.poll(), EventDistributionDto::class.java)
        assert(enterMessageEvent.event == "ENTER")

        // player1 채팅
        playerSession.sendMessage(chatMessage("CHAT", "안녕하세요", roomId))
        Thread.sleep(1000)

        val chatMessageEvent = GsonUtils.fromJson(hostMessageQueue.poll(), EventDistributionDto::class.java)
        assert(chatMessageEvent.event == "CHAT")
    }

    fun createSession(queue: BlockingQueue<String>): WebSocketSession {
        return client.doHandshake(TestHandler(queue), "ws://localhost:${randomServerPort.get()}/chat")
            .get()
    }

    fun createRoomMessage(messageType: String, playerName: String): WebSocketMessage<String> {
        return TextMessage("{\"messageType\":\"$messageType\",\"playerName\":\"$playerName\"}")
    }

    fun enterRoomMessage(messageType: String, playerName: String, roomId: String): WebSocketMessage<String> {
        return TextMessage("{\"messageType\":\"$messageType\",\"playerName\":\"$playerName\",\"roomId\":\"$roomId\"}")
    }

    fun chatMessage(messageType: String, content: String, roomId: String): WebSocketMessage<String> {
        return TextMessage("{\"messageType\":\"$messageType\",\"content\":\"$content\",\"roomId\":\"$roomId\"}")
    }

    inner class TestHandler(val queue: BlockingQueue<String>): WebSocketHandler {
        override fun afterConnectionEstablished(session: WebSocketSession) {
            println("established")
        }

        override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
            println("message: ${message.payload}")
            queue.add(message.payload as String)
        }

        override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
            println("error")
        }

        override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
            println("closed")
        }

        override fun supportsPartialMessages(): Boolean {
            println("??")
            return false;
        }

    }
}
