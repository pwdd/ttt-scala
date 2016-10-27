package ttt.player

class User(val marker: Symbol, val messenger: ttt.messenger.Messenger) extends Player {
  val isAI = false

  def getSpot(board: List[Symbol]): Int = {
    val invalidMessage = messenger.invalidMove

    val input = getChoice(messenger.chooseANumber(board.length), invalidMessage, isNumericString)

    def inputToNumber: Int = input.toInt - 1

    if (isValidMove(board, inputToNumber)) {
      inputToNumber
    }
    else {
      printer(invalidMessage)
      getSpot(board)
    }
  }

  private val printer = ttt.View.printMessage _
  private val getChoice = ttt.Prompt.getUserChoice _
  private val isValidMove = ttt.Validation.isValidMove _

  private def isNumericString(input: String): Boolean = {

    def isNumber: Boolean = input.matches("^\\d*$")

    def isEmptyStr: Boolean = input == ""

    !isEmptyStr && isNumber
  }
}
