package com.nobodyisperfect.backend.api.socket

import com.nobodyisperfect.backend.persistence.GameRepository
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo

class WebSocketController(private val gameRepository: GameRepository) {

//    @MessageMapping("/games/{gameId}")
//    @SendTo("/topic/games/{gameId}")
//    fun simple(@DestinationVariable gameId: String?): Simple? {
//        return Simple(fleetId, driverId)
//    }
}