package com.nobodyisperfect.backend.api.rest

import com.nobodyisperfect.backend.exception.GameAlreadyStartedException
import com.nobodyisperfect.backend.exception.NotFoundException
import com.nobodyisperfect.backend.exception.TooFewPlayersException
import com.nobodyisperfect.backend.exception.TooManyPlayersException
import com.nobodyisperfect.backend.model.Game
import com.nobodyisperfect.backend.model.GameStatus
import com.nobodyisperfect.backend.model.Player
import com.nobodyisperfect.backend.persistence.GameRepository
import com.nobodyisperfect.backend.persistence.PlayerRepository
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class RestController(private val playerRepository: PlayerRepository, private val gameRepository: GameRepository, private val simpleMessagingTemplate: SimpMessagingTemplate) {

    @GetMapping("/players")
    fun getPlayers(): MutableCollection<Player> {
        return this.playerRepository.all()
    }

    @PostMapping("/players")
    fun createPlayer(@RequestBody player: Player): Player {
        this.playerRepository.add(player)

        return player
    }

    @GetMapping("/games")
    fun getGames(): MutableCollection<Game> {
        return this.gameRepository.all()
    }

    @PostMapping("/games")
    fun createGame(@RequestBody game: Game): Game {
        this.gameRepository.add(game)

        return game
    }

    @PostMapping("/games/{gameId}/players")
    fun joinGame(@PathVariable gameId: String, @RequestBody player: Player): Game? {
        val foundGame = this.gameRepository.find(UUID.fromString(gameId)) ?: throw NotFoundException(gameId, "Game")
        val foundPlayer = this.playerRepository.find(player.id)
                ?: throw NotFoundException(player.id.toString(), "Player")

        if (GameStatus.WAITING_FOR_PLAYERS != foundGame.status) {
            throw GameAlreadyStartedException()
        }

        if (foundGame.players.size >= 8) {
            throw TooManyPlayersException()
        }

        foundGame.players.add(foundPlayer)

        return foundGame
    }

    @PostMapping("/games/{gameId}/start")
    fun startGame(@PathVariable gameId: String): Game? {
        val foundGame = this.gameRepository.find(UUID.fromString(gameId)) ?: throw NotFoundException(gameId, "Game")

        if (GameStatus.WAITING_FOR_PLAYERS != foundGame.status) {
            throw GameAlreadyStartedException()
        }

        if (foundGame.players.size <= 2) {
            throw TooFewPlayersException()
        }

        foundGame.status = GameStatus.STARTED

        this.simpleMessagingTemplate.convertAndSend(
                "/topic/games/$gameId",
                "{'event': 'gameStarted' }"
        )

        return foundGame
    }
}