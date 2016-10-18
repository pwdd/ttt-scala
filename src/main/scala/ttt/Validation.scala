package ttt

object Validation {

  def isValidMove(board: List[Symbol], spot: Int): Boolean = {

    def isInRange: Boolean = spot >= 0 && spot < board.length

    isInRange && Board.isSpotAvailable(board, spot)
  }

  private def isValid(validInput: List[String], input: String): Boolean = validInput.contains(input)

  def isValidGameType(input: String): Boolean = {
    isValid(List("1", "2"), input)
  }

  def isValidBoardDimension(input: String): Boolean = {
    isValid(List("3", "4"), input)
  }
}
