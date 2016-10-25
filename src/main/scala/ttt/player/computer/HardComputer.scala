package ttt.player.computer

class HardComputer(val marker: Symbol) extends ttt.player.Player {
  val isAI = true

  def getSpot(board: List[Symbol]): Int = {
    val currentPlayerMarker = ttt.EvalGame.currentPlayerMarker(board)
    val opponentMarker = ttt.EvalGame.opponentMarker(currentPlayerMarker)
    val depth = 0

    if (ttt.Board.isEmpty(board)) {
      ttt.Board.center(board.length)
    } else {
      search(board, currentPlayerMarker, opponentMarker, depth)
    }
  }

  private def search(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int) = {
    val search = new Negamax
    search.score(board, currentPlayerMarker, opponentMarker, depth)
    search.bestMove
  }
}
