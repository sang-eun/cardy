package game.cardy.domain

import org.springframework.web.socket.WebSocketSession

class Player constructor(val name: String, val session: WebSocketSession, var roomId: String?, var color: String?) {
}
