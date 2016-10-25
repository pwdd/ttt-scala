package ttt.player.computer

import scala.util.control.Breaks.{break, breakable}

class Negamax {
  val baseDepth = 100
  val maxDepth = 7

  var bestMove = -1

  def score(board: List[Symbol],
            currentPlayerMarker: Symbol,
            opponentMarker: Symbol,
            depth: Int,
            alpha: Double = Double.NegativeInfinity,
            beta: Double = Double.PositiveInfinity): Double = {

    if (isFinalState(board, depth)) {
      boardAnalysis(board, currentPlayerMarker, opponentMarker, depth)
    }

    else {
      runSearch(board, currentPlayerMarker, opponentMarker, depth, alpha, beta)
    }
  }

  private def isFinalState(board: List[Symbol], depth: Int) = {
    ttt.EvalGame.gameOver(board) || depth >= maxDepth
  }

  private def boardAnalysis(board: List[Symbol],
                            currentPlayerMarker: Symbol,
                            opponentMarker: Symbol,
                            depth: Int): Int = {

    ttt.EvalGame.winnerMarker(board) match {
      case Some(cp) if cp == currentPlayerMarker => baseDepth - depth
      case Some(o) if o == opponentMarker => depth - baseDepth
      case _ => 0
    }
  }

  private def runSearch(
                         board: List[Symbol],
                         currentPlayerMarker: Symbol,
                         opponentMarker: Symbol,
                         depth: Int,
                         alpha: Double,
                         beta: Double) = {

    var maxScore = Double.NegativeInfinity
    var alphaCopy = alpha

    val availableSpots = ttt.Board.availableSpots(board)

    breakable {
      availableSpots.foreach { spot =>
        val newBoard = ttt.Board.move(board, currentPlayerMarker, spot)
        val negamaxScore = -score(newBoard, opponentMarker, currentPlayerMarker, depth + 1, -beta, -alphaCopy)

        if (negamaxScore > maxScore) {
          maxScore = negamaxScore
          if (depth == 0) bestMove = spot
        }

        alphaCopy = Math.max(maxScore, alphaCopy)

        if (alphaCopy >= beta) {
          break()
        }
      }
    }
    alphaCopy
  }
}


