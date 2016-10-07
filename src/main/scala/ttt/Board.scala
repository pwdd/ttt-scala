package ttt

object Board {
  val emptySpot = '_
  val firstPlayer = 'x
  val secondPlayer = 'o

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

  def move(board: List[Symbol], marker: Symbol, spot: Int): List[Symbol] = {
    board.updated(spot, marker)
  }

  def isFull(board: List[Symbol]): Boolean = !board.contains(emptySpot)

  def isSpotAvailable(board: List[Symbol], spot: Int): Boolean = board(spot) == emptySpot

  def availableSpots(board: List[Symbol]): List[Int] = {
    board.zipWithIndex.collect{ case(e, i) if e == emptySpot => i }
  }

  def hasRepeatedMarkers(board: List[Symbol]): Boolean = {
    false
  }
}