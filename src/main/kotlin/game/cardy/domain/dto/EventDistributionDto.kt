package game.cardy.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class EventDistributionDto(val event: String, val playerName: String, val content: String?) {
}
