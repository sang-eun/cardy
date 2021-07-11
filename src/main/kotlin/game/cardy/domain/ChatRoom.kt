package game.cardy.domain

import java.util.*


class ChatRoom(val players: MutableList<Player>, val playerColors: PlayerColorPalette, var host: String) {

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

    fun removePlayer(player: Player) {
        players.remove(player)

        if(player.name == host) {
            chooseNewHost()
        }
    }

    private fun chooseNewHost() {
        if(players.isEmpty()) {
            return
        }

        val newHostIdx = Random().nextInt(players.size)
        host = players[newHostIdx].name
    }
}
