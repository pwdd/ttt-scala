package ttt

object Validation {

  def isValidMove(board: List[Symbol], spot: Int): Boolean = {

    def isInRange: Boolean = spot >= 0 && spot < board.length

    isInRange && Board.isSpotAvailable(board, spot)
  }

  private def isValid(validInput: Map[Symbol, String], input: String): Boolean = {
    validInput.values.toList.contains(input)
  }

  def isValidGameType(input: String): Boolean = isValid(Game.validGameTypes, input)

  def isValidBoardDimension(input: String): Boolean = isValid(Game.validBoardDimensions, input)

  def isValidLanguage(input: String): Boolean = isValid(Game.validLanguages, input)
}
