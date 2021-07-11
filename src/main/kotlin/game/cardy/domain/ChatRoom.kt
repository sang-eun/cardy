package game.cardy.domain

class ChatRoom(val players: MutableList<Player>, val playerColors: PlayerColorPalette, val host: String) {

    fun toPlayerInfos(): List<PlayerInfo> {
        return playerColors.palette.filter { it.playerName != null }.map {
                PlayerInfo(it.playerName!!, it.color, it.playerName == host)
        }
    }

    fun addPlayer(player: Player): Player {
        players.add(player)
        player.color = playerColors.assignColor(player)
        return player
    }
}
