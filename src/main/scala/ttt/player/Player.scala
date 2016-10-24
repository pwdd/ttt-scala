package ttt.player

abstract class Player {
  val marker: Symbol

  def getSpot(board: List[Symbol]): Int
}
