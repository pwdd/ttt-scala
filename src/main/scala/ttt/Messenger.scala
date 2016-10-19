package ttt

object Messenger {
  val chooseANumber = "Please enter a number from 1 to 9: "
  val invalidMove = "\nYour choice is not valid. \n"

  def currentPlayerIs(player: Symbol): String = "\nCurrent player is '" + player.name + "'"

  def draw(board: List[Symbol]): String = "The game tied!\n"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "Player '" + marker.name + "' won on positions " + posToStr + "\n"
      case _ => ""
    }
  }

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val dashes = "---"
    val separator = "\n" + List.fill(Board.size(board))(dashes).mkString(pipe) + "\n"

    def symbolToStr(marker: Symbol): String = {
      if (marker == Board.emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(Board.size(board)).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    "\n" + buildStrBoard(board.map(symbolToStr)).toString + "\n"
  }

  val chooseGameType =
    "What kind of game would you like to play?\n\n" +
      "1. Human vs Human\n" +
      "2. Human vs Unbeatable Computer\n\n" +
      "Please enter the correspondent number: "

  val invalidGameType = "\nThere is no such a game. \n"

  val chooseBoardDimension =
    "Choose the dimension of the board:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Please enter '3' or '4': "

  val invalidBoardDimension = "\nThere is no board with that dimension. \n"
}
