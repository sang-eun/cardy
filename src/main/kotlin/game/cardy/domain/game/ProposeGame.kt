package game.cardy.domain.game

import game.cardy.domain.Order
import game.cardy.domain.Rule

class ProposeGame: Game {
    override fun getGameType(): String {
        return "propose"
    }

    override fun getRule(): List<Order> {
        return listOf(
            Order(Rule.GET_QUESTION, Rule.GET_ORIGIN_CARDS),
            Order(Rule.WAIT, Rule.MAKE_COMBINATION),
            Order(Rule.SHOW_COMBINATION, Rule.WATCH_COMBINATION),
            Order(Rule.SELECT_WINNER, Rule.WAIT),
            Order(Rule.WATCH_RESULT, Rule.WATCH_RESULT)
        )
    }

    override fun getQuestion(): String {
        return "프로포즈 멘트를 만들어주세요"
    }
}
