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
    gameOver(board) || depth >= maxDepth
  }

  private def boardAnalysis(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int): Int = {

    winner(board) match {
      case Some(cp) if cp == currentPlayerMarker => baseDepth - depth
      case Some(o) if o == opponentMarker => depth - baseDepth
      case _ => 0
    }
  }

  private def runSearch(board: List[Symbol],
                        currentPlayerMarker: Symbol,
                        opponentMarker: Symbol,
                        depth: Int,
                        alpha: Double,
                        beta: Double) = {

    var maxScore = Double.NegativeInfinity
    var alphaCopy = alpha

    val spots = availableSpots(board)

    breakable {
      spots.foreach { spot =>
        val newBoard = move(board, currentPlayerMarker, spot)
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

  private val move = ttt.Board.move _
  private val availableSpots = ttt.Board.availableSpots _
  private val evaluation = ttt.EvalGame
  private val winner = evaluation.winnerMarker _
  private val gameOver = evaluation.gameOver _
}
