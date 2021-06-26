package game.cardy.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class MessageResponseDto(val result: String, val roomId: String?, val reason: String?) {
}
