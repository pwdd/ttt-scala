package ttt

object Board {
  val emptySpot = '_
  val firstPlayer = 'x
  val secondPlayer = 'o

  val boardSize = 3
  val boardLength = boardSize * boardSize

  def newBoard(length: Int): List[Symbol] = List.fill(length)(emptySpot)

  def move(board: List[Symbol], marker: Symbol, spot: Int): List[Symbol] = {
    board.updated(spot, marker)
  }

  def isBoardFull(board: List[Symbol]): Boolean = !board.contains(emptySpot)

  def isBoardEmpty(board: List[Symbol]): Boolean = board == newBoard(boardLength)

  def isSpotAvailable(board: List[Symbol], spot: Int): Boolean = board(spot) == emptySpot

  def availableSpots(board: List[Symbol]): List[Int] = {
    board.zipWithIndex.collect{ case(e, i) if e == emptySpot => i }
  }
}