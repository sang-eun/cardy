package game.cardy.domain

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*

@Component
class ChatRooms {
    val sessionMap = mutableMapOf<WebSocketSession, Player>()
    val roomIdMap = mutableMapOf<String, MutableList<Player>>()

    fun enterChannel(player: Player) {
        sessionMap[player.session] = player
        if (roomIdMap[player.roomId] == null) {
            roomIdMap[player.roomId!!] = mutableListOf(player)
        } else {
            roomIdMap[player.roomId]!!.add(player)
        }
    }

    fun getNewRoomId(): String {
        return UUID.randomUUID().toString()
    }

    fun getPlayer(session: WebSocketSession, playerName: String?, roomId: String? = null): Player {
        return sessionMap[session] ?: Player(playerName!!, session, roomId)
    }

    fun leaveChannel(player: Player) {
        sessionMap.remove(player.session)
        roomIdMap[player.roomId]?.remove(player)

        if (roomIdMap[player.roomId]?.size == 0) {
            roomIdMap.remove(player.roomId)
        }
    }

    fun distributeMessage(player: Player, content: String) {
        getReceivers(player).forEach { p ->
            if (p.session != player.session) {
                p.session.sendMessage(TextMessage(content))
            }
        }
    }

    fun getReceivers(player: Player): List<Player> {
        return roomIdMap[player.roomId] ?: arrayListOf()
    }
}
