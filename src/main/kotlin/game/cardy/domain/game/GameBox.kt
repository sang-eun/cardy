package game.cardy.domain.game

import org.springframework.stereotype.Component

@Component
class GameBox() {
    final val games: Map<String, Game>

    init {
        games = mapOf(ProposeGame().getGameType() to ProposeGame())
    }

    fun getGameByType(gameType: String): Game {
        // TODO: 예외처리 추가
        return games[gameType]!!
    }
}
