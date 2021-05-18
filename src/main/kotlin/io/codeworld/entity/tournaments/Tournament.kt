package io.codeworld.entity.tournaments

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.jackson.json
import org.ktorm.schema.*
import org.ktorm.support.postgresql.pgEnum

interface Tournament : Entity<Tournament> {
    companion object : Entity.Factory<Tournament>() {}

    var id: Int?
    var name: String?
    var description: String?
    var startDate: Long?
    var endDate: Long?
    var config: TournamentConfig?
    var currentRound: Int?
    var draftDeadline: Int?
    var type: TournamentType?
    var createdAt: Long?
}

object Tournaments : Table<Tournament>("tournaments") {
    val id = int("id").primaryKey().bindTo { it.id }

    val name = varchar("name").bindTo{ it.name }

    val description = varchar("description").bindTo { it.description }

    val startDate = long("startDate").bindTo { it.startDate }

    val endDate = long("endDate").bindTo { it.endDate }

    val config = json<TournamentConfig>("config").bindTo { it.config }

    val currentRound = int("currentRound").bindTo { it.currentRound }

    val draftDeadline = int("draftDeadline").bindTo { it.draftDeadline }

    val type = pgEnum<TournamentType>("type").bindTo { it.type }

    val createdAt = long("createdAt").bindTo { it.createdAt }
}

val Database.tournaments get() = this.sequenceOf(Tournaments)
