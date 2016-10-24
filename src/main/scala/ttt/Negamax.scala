package ttt

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

    var maxScore = Double.NegativeInfinity

    def isFinalState = {
      EvalGame.gameOver(board) || depth >= maxDepth
    }

    def boardAnalysis(): Int = {
      val winner = EvalGame.winnerMarker(board)

      winner match {
        case Some(cp) if cp == currentPlayerMarker => baseDepth - depth
        case Some(o) if o == opponentMarker => depth - baseDepth
        case _ => 0
      }
    }

    if (isFinalState) {
      boardAnalysis()
    }

    else {
      var alphaCopy = alpha
      val availableSpots = Board.availableSpots(board)

      breakable {
        availableSpots.foreach { spot =>
          val newBoard = Board.move(board, currentPlayerMarker, spot)
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
}


