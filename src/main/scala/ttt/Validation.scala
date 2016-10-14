package ttt

object Validation {

  def isValidMove(board: List[Symbol], spot: Int): Boolean = {

    def isInRange: Boolean = spot >= 0 && spot < board.length

    isInRange && Board.isSpotAvailable(board, spot)
  }

  def isValidGameType(input: String): Boolean = {
    val validChoices = List("1", "2")
    validChoices.contains(input)
  }
}
