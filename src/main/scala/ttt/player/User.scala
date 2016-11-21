package ttt.player

import ttt.View
import ttt.Prompt
import ttt.Validation

class User(val marker: Symbol, val messenger: ttt.messenger.Messenger) extends Player {
  val isAI = false

  def getSpot(board: List[Symbol]): Int = {
    val invalidMessage = messenger.invalidMove

    val input = Prompt.getUserChoice(messenger.chooseANumber(board.length), invalidMessage, isNumericString)

    def inputToNumber: Int = input.toInt - 1

    if (Validation.isValidMove(board, inputToNumber)) {
      inputToNumber
    }
    else {
      View.printMessage(invalidMessage)
      getSpot(board)
    }
  }

  private def isNumericString(input: String): Boolean = {

    def isNumber: Boolean = input.matches("^\\d*$")

    def isEmptyStr: Boolean = input == ""

    !isEmptyStr && isNumber
  }
}
