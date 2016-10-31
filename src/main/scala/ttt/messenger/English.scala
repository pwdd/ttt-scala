package ttt.messenger

class English extends Messenger {
  val invalidMove = "\nYour choice is not valid. \n"

  val chooseGameType =
    "What kind of game would you like to play?\n\n" +
      humanXHuman +
      ". Human vs Human\n" +
      humanXComputer +
       ". Human vs Computer\n" +
      computerXComputer +
      ". Computer vs Computer\n\n" +
      "Please enter the correspondent number: "

  val invalidGameType = "\nThere is no such a game. \n"

  val chooseBoardDimension =
    "Choose the dimension of the board:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Please enter '" + threeByThree +
      "' or '" + fourByFour + "': "

  val invalidBoardDimension = "\nThere is no board with that dimension. \n"

  val draw = "The game tied!\n"

  val invalidComputerLevel = "There is no such computer player\n"

  def computerLevel(first: Boolean): String = {
    val choices =
      easy +
        ". easy\n" +
        hard +
        ". unbeatable\n\n"

    val order = if (first) "First" else "Second"

    order + " computer player can be \n\n" + choices + "Please enter the correspondent number: "
  }

  def chooseANumber(boardLength: Int) = "Please enter a number from 1 to " + boardLength + ": "

  def currentPlayerIs(player: Symbol): String = "\nCurrent player is '" + player.name + "'"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "Player '" + marker.name + "' won on positions " + posToStr + "\n"
      case _ => ""
    }
  }
}
