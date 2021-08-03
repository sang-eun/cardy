package game.cardy.domain.service

import game.cardy.domain.dto.QuestionRequestDto
import game.cardy.domain.dto.QuestionResponseDto
import game.cardy.domain.dto.RuleRequestDto
import game.cardy.domain.dto.RuleResponseDto
import game.cardy.domain.game.GameBox
import game.cardy.util.GsonUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuestionService {

    @Autowired
    lateinit var gameBox: GameBox

    fun getQuestion(payload: String): String {
        val questionRequestDto = GsonUtils.fromJson(payload, QuestionRequestDto::class.java)

        return GsonUtils.toJson(
            QuestionResponseDto(gameBox.getGameByType(questionRequestDto.gameType).getQuestion())
        )
    }
}
