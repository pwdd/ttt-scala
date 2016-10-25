package ttt.player

class User(val marker: Symbol, val messenger: ttt.messenger.Messenger) extends Player {

  val isAI = false

  private def isNumericString(input: String): Boolean = {

    def isNumber: Boolean = input.matches("^\\d*$")

    def isEmptyStr: Boolean = input == ""

    !isEmptyStr && isNumber
  }

  def getSpot(board: List[Symbol]): Int = {
    val invalidMessage = messenger.invalidMove

    val input = ttt.Prompt.getUserChoice(
      messenger.chooseANumber(board.length),
      invalidMessage,
      isNumericString)

    def inputToNumber: Int = input.toInt - 1

    if (ttt.Validation.isValidMove(board, inputToNumber)) {
      inputToNumber
    }
    else {
      ttt.View.printMessage(invalidMessage)
      getSpot(board)
    }
  }
}
