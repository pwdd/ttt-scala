package ttt

abstract class Player {
  val marker: Symbol

  def getSpot(board: List[Symbol],
              message: String,
              currentPlayerMarker: Symbol,
              opponentMarker: Symbol,
              depth: Int): Int
}
