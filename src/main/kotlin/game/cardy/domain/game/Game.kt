package game.cardy.domain.game

import game.cardy.domain.Order

interface Game {
    fun getGameType(): String

    fun getRule(): List<Order>

    fun getQuestion(): String
}
