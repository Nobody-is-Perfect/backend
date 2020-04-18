package com.nobodyisperfect.backend.model

import java.util.*

class Game(val id: UUID = UUID.randomUUID(), val name: String, val numberOfRounds: Int? = 3, var status: GameStatus? = GameStatus.WAITING_FOR_PLAYERS) {
    val players: MutableList<Player> = mutableListOf<Player>();

}