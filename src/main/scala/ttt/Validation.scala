package ttt

object Validation {

  def isInRange(board: List[Symbol], spot: Int): Boolean = {
    spot >= 0 && spot < board.length
  }

  def isValidMove(board: List[Symbol], spot: Int): Boolean = {
    isInRange(board, spot) && Board.isSpotAvailable(board, spot)
  }
}
