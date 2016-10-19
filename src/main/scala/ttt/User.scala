package ttt

class User(val marker: Symbol) extends Player {
  val messenger = Messenger

  private def isNumericString(input: String): Boolean = {

    def isNumber: Boolean = input.matches("^\\d*$")

    def isEmptyStr: Boolean = input == ""

    !isEmptyStr && isNumber
  }

  def getSpot(board: List[Symbol]): Int = {

    val input = Prompt.getUserChoice(messenger.chooseANumber, messenger.invalidMove, isNumericString)

    def inputToNumber: Int = input.toInt - 1

    if (Validation.isValidMove(board, inputToNumber)) inputToNumber
    else {
      View.printMessage(messenger.invalidMove)
      getSpot(board)
    }
  }
}
