package game.cardy.domain.service

import game.cardy.domain.Order
import game.cardy.domain.Rule
import game.cardy.domain.dto.RuleRequestDto
import game.cardy.domain.dto.RuleResponseDto
import game.cardy.domain.exception.NoGameTypeExistsException
import game.cardy.util.GsonUtils
import org.springframework.stereotype.Service

@Service
class RuleService {
    fun getRule(payload: String): String {
        val ruleRequestDto = GsonUtils.fromJson(payload, RuleRequestDto::class.java)

        if (ruleRequestDto.gameType == "propose") {
            return GsonUtils.toJson(RuleResponseDto(
                listOf(
                    Order(Rule.GET_QUESTION, Rule.GET_ORIGIN_CARDS),
                    Order(Rule.WAIT, Rule.MAKE_COMBINATION),
                    Order(Rule.SHOW_COMBINATION, Rule.WATCH_COMBINATION),
                    Order(Rule.SELECT_WINNER, Rule.WAIT),
                    Order(Rule.WATCH_RESULT, Rule.WATCH_RESULT)
                )
            ))
        }

        throw NoGameTypeExistsException()
    }
}
