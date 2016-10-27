package ttt

object EvalGame {

  def winCombos(size: Int) = {
    val indexes = Board.indexes(size * size)
    val rows = Board.rows(indexes, size)
    val columns = Board.columns(rows)
    val diagonals = Board.diagonals(rows, size)

    List(rows, columns, diagonals).flatten
  }

  def winCombo(board: List[Symbol]): List[Int] = {

    def hasRepeatedMarkers(board: List[Symbol], indexes: List[Int]): Boolean = {
      val markersOnIndexes = indexes.map(board(_))
      val first = markersOnIndexes.head
      first != emptySpot && markersOnIndexes.forall(e => e == first)
    }

    def indexedWinCombos(combos: List[List[Int]]) = combos.map(hasRepeatedMarkers(board, _)).zipWithIndex

    val getWinCombos = winCombos(Board.size(board))
    val pairs = indexedWinCombos(getWinCombos)
    val winAt = pairs.indexWhere(_._1)

    if (winAt == -1) List()
    else getWinCombos(winAt)
  }

  def isDraw(board: List[Symbol]): Boolean = {
    isFull(board) && winCombo(board).isEmpty
  }

  def gameOver(board: List[Symbol]): Boolean = isDraw(board) || winCombo(board).nonEmpty

  def winnerMarker(board: List[Symbol]): Option[Symbol] = {
    val marker = winCombo(board).headOption
    marker.map(board(_))
  }

  def currentPlayerMarker(board: List[Symbol]): Symbol = {

    def countMarker(marker: Symbol): Int = board.count(_ == marker)

    val countX = countMarker(firstPlayer)
    val countO = countMarker(secondPlayer)

    countX match {
      case equal if countX == countO => firstPlayer
      case more if countX > countO => secondPlayer
      case _ => firstPlayer
    }
  }

  def opponentMarker(currentPlayerMarker: Symbol): Symbol = {
    if (currentPlayerMarker == firstPlayer) secondPlayer
    else firstPlayer
  }

  private val (emptySpot, firstPlayer, secondPlayer) = (Board.emptySpot, Board.firstPlayer, Board.secondPlayer)
  private val isFull = Board.isFull _
}
