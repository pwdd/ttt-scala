package ttt

object Validation {
  val validGameTypes = Map('humanXHuman -> "1", 'humanXComputer -> "2", 'computerXComputer -> "3")
  val validBoardDimensions = Map('threeByThree -> "3", 'fourByFour -> "4")
  val validLanguages = Map('english -> "1", 'spanish -> "2", 'portuguese -> "3")

  def isValidMove(board: List[Symbol], spot: Int): Boolean = {

    def isInRange: Boolean = spot >= 0 && spot < board.length

    isInRange && Board.isSpotAvailable(board, spot)
  }

  private def isValid(validInput: Map[Symbol, String], input: String): Boolean = {
    validInput.values.toList.contains(input)
  }

  def isValidGameType(input: String): Boolean = isValid(validGameTypes, input)

  def isValidBoardDimension(input: String): Boolean = isValid(validBoardDimensions, input)

  def isValidLanguage(input: String): Boolean = isValid(validLanguages, input)
}
