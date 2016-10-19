package ttt

object EvalGame {
  def winCombos(size: Int) = {
    val indexes = Board.indexes(size * size)
    val rows = Board.rows(indexes, size)
    val columns = ttt.Board.columns(rows)
    val diagonals = ttt.Board.diagonals(rows, size)

    List(rows, columns, diagonals).flatten
  }

  def winCombo(board: List[Symbol]): List[Int] = {

    def hasRepeatedMarkers(board: List[Symbol], indexes: List[Int]): Boolean = {
      val markersOnIndexes = indexes.map(board(_))
      val first = markersOnIndexes.head
      first != Board.emptySpot && markersOnIndexes.forall(e => e == first)
    }

    val getWinCombos = winCombos(Board.size(board))
    val pairs = getWinCombos.map(hasRepeatedMarkers(board, _)).zipWithIndex
    val winAt = pairs.indexWhere(_._1)

    if (winAt == -1) List()
    else getWinCombos(winAt)
  }

  def isDraw(board: List[Symbol]): Boolean = Board.isFull(board) && winCombo(board).isEmpty

  def gameOver(board: List[Symbol]): Boolean = isDraw(board) || winCombo(board).nonEmpty

  def winnerMarker(board: List[Symbol]): Option[Symbol] = {
    val marker = winCombo(board).headOption
    marker.map(board(_))
  }
}
