package ttt

object Board {
  val emptySpot = Symbol("_")

  val winCombos = List(List(0, 1, 2),
                       List(3, 4, 5),
                       List(6, 7, 8),
                       List(0, 3, 6),
                       List(1, 4, 7),
                       List(2, 5, 8),
                       List(0, 4, 8),
                       List(2, 4, 6))

  val boardSize = 3
  val boardLength = boardSize * boardSize

  def newBoard(length: Int): List[Symbol] = List.fill(length)(emptySpot)
}