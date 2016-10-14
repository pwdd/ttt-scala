package ttt

class Computer(val marker: Symbol) extends Player {
  def getSpot(board: List[Symbol],
              message: String = "",
              currentPlayerMarker: Symbol,
              opponentMarker: Symbol,
              depth: Int = 0): Int = {

    Negamax.bestMove(board, currentPlayerMarker, opponentMarker, depth)
  }
}
