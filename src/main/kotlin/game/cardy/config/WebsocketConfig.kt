package game.cardy.config

import game.cardy.controller.ChatHandler
import game.cardy.controller.GameHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.WebSocketConfigurer

import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


@Configuration
@EnableWebSocket
class WebsocketConfig: WebSocketConfigurer{
    @Autowired
    private lateinit var gameHandler: GameHandler

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatHandler()!!, "/chat").setAllowedOrigins("*")
        registry.addHandler(gameHandler, "/game").setAllowedOrigins("*")
    }

    @Bean
    fun chatHandler(): WebSocketHandler? {
        return ChatHandler()
    }
}
