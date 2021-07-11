package game.cardy.domain

class PlayerColorPalette {
    private val RED = "#DC3838"
    private val PINK = "#EF657A"
    private val ORANGE = "#F7631E"
    private val YELLOW = "#FAA328"
    private val GREEN = "#B2C224"
    private val CYAN = "#33BEB8"
    private val BLUE = "#3FA4D8"
    private val PURPLE = "#A463D8"

    val palette = listOf(PlayerColor(RED,null), PlayerColor(PINK, null), PlayerColor(ORANGE, null), PlayerColor(YELLOW, null), PlayerColor(GREEN, null), PlayerColor(CYAN, null), PlayerColor(BLUE, null), PlayerColor(PURPLE, null))

    fun assignColor(player: Player): String {
        // 이 전에 인원수 체크 필요
        val colorPair = palette.find { it.playerName == null }
        colorPair!!.playerName = player.name
        return colorPair.color
    }

    inner class PlayerColor(val color: String, var playerName: String?){

    }
}
