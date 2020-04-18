package com.nobodyisperfect.backend.persistence

import com.nobodyisperfect.backend.model.Game
import com.nobodyisperfect.backend.model.Player
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerRepository {
    val players: MutableMap<UUID, Player> = mutableMapOf();

    fun all(): MutableCollection<Player> {
        return this.players.values
    }

    fun find(playerId: UUID): Player?
    {
        return this.players[playerId]
    }

    fun add(player: Player)
    {
        this.players[player.id] = player;
    }
}