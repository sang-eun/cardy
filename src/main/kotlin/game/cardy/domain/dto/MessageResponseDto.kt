package game.cardy.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude
import game.cardy.domain.PlayerInfo

@JsonInclude(JsonInclude.Include.NON_NULL)
class MessageResponseDto(val result: String, val roomId: String?, val reason: String?, val playerInfos: List<PlayerInfo>) {
}
