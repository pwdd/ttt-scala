package ttt

object Messenger {
  val chooseANumber = "Please enter a number from 1 to 9: "
  val invalidMove = "Your choice is not valid. " +  "\n" + chooseANumber

  def currentPlayerIs(player: Symbol): String = "\nCurrent player is '" + player.name + "'"

  def finalMessage(winner: Symbol, position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")
    "Player '" + winner.name + "' won on positions " + posToStr + "\n"
  }

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val separator = "\n---|---|---\n"

    def symbolToStr(marker: Symbol): String = {
      if (marker == Board.emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(Board.boardSize).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    "\n" + buildStrBoard(board.map(symbolToStr)).toString + "\n"
  }
}
