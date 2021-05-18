package io.codeworld.controllers
import io.codeworld.entity.tournaments.Tournament
import io.codeworld.services.TournamentService
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal
import javax.inject.Inject


@Controller("/tournaments")
@Produces(MediaType.APPLICATION_JSON)
@Secured(SecurityRule.IS_ANONYMOUS)
class TournamentsController {

    @Inject
    lateinit var tournamentsService: TournamentService

    @Get("/{tournamentId}")
    fun get(
        @PathVariable tournamentId: Int
    ): Tournament {
        val tournament = tournamentsService.find(tournamentId)
        return tournament?.let { tournament }
            ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Tournament not found")
    }

    @Get()
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun getAll(): List<Tournament> = tournamentsService.findAll()

    @Post()
    fun create(
        @Body tournament: Tournament
    ): Tournament = tournamentsService.create(tournament)!!

    @Put("/{tournamentId}")
    fun update(
        @PathVariable tournamentId: Int,
        @Body tournament: Tournament,
    ): Tournament = tournamentsService.update(tournamentId, tournament)

    @Delete("/{tournamentId}")
    fun delete(
        @PathVariable tournamentId: Int
    ) = tournamentsService.delete(tournamentId)


    @Get("/verification")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    fun verify(principal: Principal): String {
        return principal.toString()
    }

}

