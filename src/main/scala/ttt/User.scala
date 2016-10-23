package ttt

class User(val marker: Symbol, val validMessage: String = "", val invalidMessage: String = "") extends Player {

  private def isNumericString(input: String): Boolean = {

    def isNumber: Boolean = input.matches("^\\d*$")

    def isEmptyStr: Boolean = input == ""

    !isEmptyStr && isNumber
  }

  def getSpot(board: List[Symbol]): Int = {

    val input = Prompt.getUserChoice(validMessage, invalidMessage, isNumericString)

    def inputToNumber: Int = input.toInt - 1

    if (Validation.isValidMove(board, inputToNumber)) inputToNumber
    else {
      View.printMessage(invalidMessage)
      getSpot(board)
    }
  }
}
