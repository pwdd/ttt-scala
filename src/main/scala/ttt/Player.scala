package ttt

abstract class Player {
  val marker: Symbol

  def getSpot(board: List[Symbol]): Int
}
