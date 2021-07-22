package game.cardy.config

import game.cardy.controller.ChatHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.WebSocketConfigurer

import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


@Configuration
@EnableWebSocket
class WebsocketConfig: WebSocketConfigurer{
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatHandler()!!, "/chat").setAllowedOrigins("*")
    }

    @Bean
    fun chatHandler(): WebSocketHandler? {
        return ChatHandler()
    }
}
