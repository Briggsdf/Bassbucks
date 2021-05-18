package io.codeworld.services

import io.codeworld.entity.tournaments.Tournament
import io.codeworld.repo.tournament.TournamentRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TournamentService {

    @Inject
    lateinit var tournamentRepo: TournamentRepo

    fun findAll(): List<Tournament> {

        return this.tournamentRepo.findAll()
    }

    fun create(tournament: Tournament): Tournament? {
        return this.tournamentRepo.save(tournament)
    }

    fun update(tournamentId: Int, tournament: Tournament): Tournament {
        return this.tournamentRepo.update(tournamentId, tournament)
    }

    fun delete(tournamentId: Int) {
        return this.tournamentRepo.delete(tournamentId)
    }

    fun find(tournamentId: Int): Tournament? {
        return this.tournamentRepo.find(tournamentId);
    }
}