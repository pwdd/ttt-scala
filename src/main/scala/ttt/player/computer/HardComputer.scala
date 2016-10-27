package ttt.player.computer

class HardComputer(val marker: Symbol) extends ttt.player.Player {
  val isAI = true

  def getSpot(board: List[Symbol]): Int = {
    val currentPlayerMarker = currentPlayer(board)
    val opponentMarker = opponent(currentPlayerMarker)
    val depth = 0

    if (isBoardEmpty(board)) {
      center(board.length)
    } else {
      search(board, currentPlayerMarker, opponentMarker, depth)
    }
  }

  private def search(board: List[Symbol], currentPlayerMarker: Symbol, opponentMarker: Symbol, depth: Int) = {
    val search = new Negamax
    search.score(board, currentPlayerMarker, opponentMarker, depth)
    search.bestMove
  }

  private val evaluation = ttt.EvalGame
  private val currentPlayer = evaluation.currentPlayerMarker _
  private val opponent = evaluation.opponentMarker _
  private val isBoardEmpty = ttt.Board.isEmpty _
  private val center = ttt.Board.center _
}
