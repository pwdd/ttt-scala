package ttt.Messenger

class English extends Messenger {
  val chooseANumber = "Please enter a number from 1 to 9: "

  val invalidMove = "\nYour choice is not valid. \n"

  val chooseGameType =
    "What kind of game would you like to play?\n\n" +
      ttt.Validation.validGameTypes('humanXHuman) +
      ". Human vs Human\n" +
      ttt.Validation.validGameTypes('humanXComputer) +
       ". Human vs Unbeatable Computer\n\n" +
      "Please enter the correspondent number: "

  val invalidGameType = "\nThere is no such a game. \n"

  val chooseBoardDimension =
    "Choose the dimension of the board:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Please enter '" + ttt.Validation.validBoardDimensions('threeByThree) +
      "' or '" + ttt.Validation.validBoardDimensions('fourByFour) + "': "

  val invalidBoardDimension = "\nThere is no board with that dimension. \n"

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
}
