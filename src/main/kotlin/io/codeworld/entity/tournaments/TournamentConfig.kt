package io.codeworld.entity.tournaments

data class TournamentConfig (
    var salaryCap: Int,
    var participatingAnglers: List<Angler>,
    var maxRounds: Int,
    var maxDraftSize: Int,
    var cashGameType: String,
    var gppDistibution: List<PrizeDistribution>
)

/**
 * Ex: Top 1 gets 10%
 */
data class PrizeDistribution(
    var startPosition: Int,
    var endPosition: Int,
    var payoutPercent: Double,
)

data class Angler(
    var name: String,
)
