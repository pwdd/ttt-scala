package ttt.player

abstract class Player {
  val marker: Symbol
  val isAI: Boolean

  def getSpot(board: List[Symbol]): Int
}
