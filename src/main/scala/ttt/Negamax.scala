package ttt

object Negamax {
  val baseDepth = 100

  def scores(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int): List[Int] = {
    val spots = Board.availableSpots(board)
    val newBoards = spots.map(Board.move(board, currentPlayerMarker, _))
    newBoards.map(- score(_, opponentMarker, currentPlayerMarker, depth + 1))
  }

  def score(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int): Int = {

    def boardAnalysis(): Int = {
      val winner = EvalGame.winnerMarker(board)

      winner match {
        case cp if winner == currentPlayerMarker => baseDepth - depth
        case o if winner == opponentMarker => depth - baseDepth
        case _ => 0
      }
    }

    if (EvalGame.gameOver(board)) {
      boardAnalysis()
    } else {
      scores(board, currentPlayerMarker, opponentMarker, depth).max
    }
  }

  def bestMove(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int = 0): Int = {
    if (Board.isEmpty(board)) {
      4
    } else {
      val spots = Board.availableSpots(board)
      val scoresList = scores(board, currentPlayerMarker, opponentMarker, depth)
      val maxValue = scoresList.max
      val best = scoresList.indexOf(maxValue)
      spots(best)
    }
  }
}

