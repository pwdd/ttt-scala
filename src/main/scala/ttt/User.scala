package ttt

object User {
  val marker = Board.firstPlayer
  val role = 'human

  def getSpot(board: List[Symbol], message: String): Int = {
    val input = View.prompt(message).trim

    def isNumber: Boolean = input.matches("^\\d*$")

    def inputToNumber(): Int = input.toInt - 1

    def isEmptyStr: Boolean = input == ""

    if (!isEmptyStr &&
      isNumber &&
      Validation.isValidMove(board, inputToNumber()))
      inputToNumber()
    else getSpot(board, Messenger.invalidMove)
  }
}
