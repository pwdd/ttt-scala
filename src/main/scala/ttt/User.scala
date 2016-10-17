package ttt

class User(val marker: Symbol) extends Player {

  def getSpot(board: List[Symbol],
              message: String,
              currentPlayerMarker: Symbol = '_,
              opponentMarker: Symbol = '_,
              depth: Int = 0): Int = {
    val input = Prompt.prompt(message)

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
