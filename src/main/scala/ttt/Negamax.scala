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
      val winner = if (Rules.gameOver(board) && !Rules.isDraw(board)) Rules.winner(board)
      else Board.emptySpot

      winner match {
        case cp if winner == currentPlayerMarker => 100 - depth
        case o if winner == opponentMarker => depth - 100
        case _ => 0
      }
    }

    if (Rules.gameOver(board)) {
      boardAnalysis()
    } else {
      scores(board, currentPlayerMarker, opponentMarker, depth).max
    }
  }

  def bestMove(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int = 0): Int = {
    if (Board.isBoardEmpty(board)) {
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


