package ttt

import scala.util.control.Breaks.break

object Negamax {
  val baseDepth = 100
  var bestIndex = -1

  def score(board: List[Symbol],
            currentPlayerMarker: Symbol,
            opponentMarker: Symbol,
            depth: Int,
            alpha: Int = -100000,
            beta: Int = 100000): Int = {

    var maxScore = -100000

    def boardAnalysis(): Int = {
      val winner = EvalGame.winnerMarker(board)

      winner match {
        case Some(cp) if cp == currentPlayerMarker => baseDepth - depth
        case Some(o) if o == opponentMarker => depth - baseDepth
        case _ => 0
      }
    }

    def maxDepth(depth: Int, length: Int): Int = if (depth <= 4 && length > 9) 4 else 6

    if (EvalGame.gameOver(board) || depth >= maxDepth(depth, board.length)) {
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
}


