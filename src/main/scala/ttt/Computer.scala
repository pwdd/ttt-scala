package ttt

class Computer(val marker: Symbol) extends Player {
  def getSpot(board: List[Symbol],
              message: String = "",
              currentPlayerMarker: Symbol,
              opponentMarker: Symbol,
              depth: Int = 0): Int = {

    if (Board.isEmpty(board)) {
      Board.center(board.length)
    } else {
      Negamax.score(board, currentPlayerMarker, opponentMarker, depth)
      Negamax.bestIndex
    }
  }
}
