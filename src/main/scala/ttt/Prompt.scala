package ttt

import scala.io.StdIn.readLine

object Prompt {
  def prompt(message: String): String = {
    readLine(message)
  }

  def getSpot(board: List[Symbol], message: String): Int = {
    val input = prompt(message).trim

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
