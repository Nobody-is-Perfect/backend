package com.nobodyisperfect.backend.persistence

import com.nobodyisperfect.backend.model.Game
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameRepository {
    val games: MutableMap<UUID, Game> = mutableMapOf();

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