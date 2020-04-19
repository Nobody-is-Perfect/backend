package com.nobodyisperfect.backend.persistence

import com.nobodyisperfect.backend.model.Game
import com.nobodyisperfect.backend.model.Player
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerRepository {
    val players: MutableMap<UUID, Player> = mutableMapOf();

    init {
        players[UUID.fromString("45c89dd9-ea9a-4bf6-afdb-934fc2781fd6")] = Player(
            UUID.fromString("45c89dd9-ea9a-4bf6-afdb-934fc2781fd6"),
            "player1"
        )

        players[UUID.fromString("4ff09569-a2dd-467f-83bd-bc963830e79c")] = Player(
                UUID.fromString("4ff09569-a2dd-467f-83bd-bc963830e79c"),
                "player2"
        )

        players[UUID.fromString("1cbfd890-5a10-4fae-96bb-b26b0f5d22e5")] = Player(
                UUID.fromString("1cbfd890-5a10-4fae-96bb-b26b0f5d22e5"),
                "player3"
        )
    }

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