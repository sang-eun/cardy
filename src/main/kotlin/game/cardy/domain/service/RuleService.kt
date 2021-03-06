package game.cardy.domain.service

import game.cardy.domain.dto.RuleRequestDto
import game.cardy.domain.dto.RuleResponseDto
import game.cardy.domain.game.GameBox
import game.cardy.util.GsonUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RuleService {

    @Autowired
    lateinit var gameBox: GameBox

    fun getRule(payload: String): String {
        val ruleRequestDto = GsonUtils.fromJson(payload, RuleRequestDto::class.java)

        return GsonUtils.toJson(
            RuleResponseDto(gameBox.getGameByType(ruleRequestDto.gameType).getRule()))
    }
}
