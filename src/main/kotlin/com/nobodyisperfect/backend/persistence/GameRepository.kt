package com.nobodyisperfect.backend.persistence

import com.nobodyisperfect.backend.model.Game
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameRepository {
    val games: MutableMap<UUID, Game> = mutableMapOf();

    init {
        games[UUID.fromString("a488f279-59ef-4e70-be8c-18b14248478a")] = Game(
                UUID.fromString("a488f279-59ef-4e70-be8c-18b14248478a"),
                "myGame"
        )
    }

    fun all(): MutableCollection<Game> {
        return this.games.values
    }

    fun find(id: UUID): Game?
    {
        return this.games[id]
    }

    fun add(game: Game)
    {
        games[game.id] = game;
    }
}