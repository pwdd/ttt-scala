package ttt

import scala.util.control.Breaks.break

object Negamax {
  val baseDepth = 100
  val maxDepth = 6
  var bestIndex = -1

  def scores(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int): List[Int] = {
    val spots = Board.availableSpots(board)
    val newBoards = spots.map(Board.move(board, currentPlayerMarker, _))
    newBoards.map(- score(_, opponentMarker, currentPlayerMarker, depth + 1))
  }

  def score(board: List[Symbol],
            currentPlayerMarker: Symbol,
            opponentMarker: Symbol,
            depth: Int,
            alpha: Int = -100000,
            beta: Int = 100000): Int = {

    var maxScore = -100000

    def boardAnalysis(): Int = {
      lazy val winner = EvalGame.winnerMarker(board)

      winner match {
        case cp if winner == currentPlayerMarker => baseDepth - depth
        case o if winner == opponentMarker => depth - baseDepth
        case _ => 0
      }
    }

    if (EvalGame.gameOver(board)) {
      boardAnalysis()
    } else {
      var alphaCopy = alpha
      val availableSpots = Board.availableSpots(board)

      availableSpots.foreach { spot =>
        val newBoard = Board.move(board, currentPlayerMarker, spot)
        lazy val negamaxScore = -score(newBoard, opponentMarker, currentPlayerMarker, depth + 1, -beta, -alpha)
        if (negamaxScore > maxScore) {
          maxScore = negamaxScore
          if (depth == 0) bestIndex = spot
        }
        alphaCopy = Math.max(maxScore, alphaCopy)
        if (alphaCopy >= beta) {
          break()
        }
      }
      alphaCopy
    }
  }

  def bestMove(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int = 0): Int = {
    if (Board.isEmpty(board)) {
      4
    } else {
      score(board, currentPlayerMarker, opponentMarker, depth)
      bestIndex
    }
  }
}


