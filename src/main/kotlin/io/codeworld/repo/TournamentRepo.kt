package io.codeworld.repo.tournament

import io.codeworld.entity.tournaments.Tournament
import io.codeworld.entity.tournaments.Tournaments
import io.codeworld.entity.tournaments.tournaments
import io.micronaut.context.annotation.Prototype
import io.micronaut.data.annotation.Repository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.find
import org.ktorm.entity.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TournamentRepo {

    @Inject
    lateinit var database: Database

    fun findAll(): List<Tournament> {
        return database.from(Tournaments)
            .select()
            .orderBy(Tournaments.id.desc())
            .map { row -> Tournaments.createEntity(row) }
    }

    fun save(tournament: Tournament): Tournament {
        val key = database.insertAndGenerateKey(Tournaments) {
            set(it.name, tournament.name)
            set(it.description, tournament.description)
            set(it.startDate, tournament.startDate)
            set(it.endDate, tournament.endDate)
            set(it.config, tournament.config)
            set(it.currentRound, tournament.currentRound)
            set(it.draftDeadline, tournament.draftDeadline)
            set(it.type, tournament.type)
            set(it.createdAt, tournament.createdAt)
        }

        return find(key as Int)!!
    }

    fun update(tournamentId: Int, tournament: Tournament): Tournament {
        find(tournamentId) ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Tournament not found")

        tournament.id = tournamentId
        database.tournaments.update(tournament)

        return find(tournamentId)!!
    }

    fun delete(tournamentId: Int) {
        val tournament = find(tournamentId) ?: throw HttpStatusException(HttpStatus.NOT_FOUND, "Tournament not found")

        tournament.delete()
    }

    fun find(tournamentId: Int): Tournament? {
        return database.tournaments.find { it.id eq tournamentId }
    }
}