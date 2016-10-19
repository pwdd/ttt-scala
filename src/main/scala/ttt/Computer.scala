package ttt

class Computer(val marker: Symbol) extends Player {
  def getSpot(board: List[Symbol]): Int = {
    val currentPlayerMarker = EvalGame.currentPlayerMarker(board)
    val opponentMarker = EvalGame.opponentMarker(currentPlayerMarker)
    val depth = 0

    if (Board.isEmpty(board)) {
      Board.center(board.length)
    } else {
      Negamax.score(board, currentPlayerMarker, opponentMarker, depth)
      Negamax.bestMove
    }
  }
}
