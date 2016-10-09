package ttt

object Rules {
  val winCombos = List(List(0, 1, 2),
    List(3, 4, 5),
    List(6, 7, 8),
    List(0, 3, 6),
    List(1, 4, 7),
    List(2, 5, 8),
    List(0, 4, 8),
    List(2, 4, 6))

  def winCombo(board: List[Symbol]): List[Int] = {

    def hasRepeatedMarkers(board: List[Symbol], indexes: List[Int]): Boolean = {
      val markersOnIndexes = indexes.map(board(_))
      val first = markersOnIndexes.head
      first != Board.emptySpot && markersOnIndexes.forall(e => e == first)
    }

    val pairs = winCombos.map(hasRepeatedMarkers(board, _)).zipWithIndex
    val winAt = pairs.indexWhere(_._1)

    if (winAt == -1) List()
    else winCombos(winAt)
  }

  def isDraw(board: List[Symbol]): Boolean = Board.isBoardFull(board) && winCombo(board).isEmpty

  def gameOver(board: List[Symbol]): Boolean = isDraw(board) || winCombo(board).nonEmpty

  def winner(board: List[Symbol]) = {
    board(winCombo(board).head)
  }
}
