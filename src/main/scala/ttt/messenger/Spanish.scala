package ttt.messenger

class Spanish extends Messenger {

  val invalidMove = "\nSu opción no es válida. \n"

  val chooseGameType =
    "¿Qué tipo de juego le gustaría jugar?\n\n" +
      ttt.Validation.validGameTypes('humanXHuman) +
      ". Humano x Humano\n" +
      ttt.Validation.validGameTypes('humanXComputer) +
      ". Humano x Computadora\n" +
      ttt.Validation.validGameTypes('humanXComputer) +
      ". Computadora x Computadora\n\n" +
      "Por favor, introduzca el número correspondiente: "

  val invalidGameType = "\nNo hay un juego de este tipo. \n"

  val chooseBoardDimension =
    "Elige la dimensión del tablero:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Por favor introduzca '" + ttt.Validation.validBoardDimensions('threeByThree) +
      "' o '" + ttt.Validation.validBoardDimensions('fourByFour) + "': "

  val invalidBoardDimension = "\nNo hay un tablero con esta dimensión. \n"

  def chooseANumber(board: List[Symbol]) = "Introduzca un número entre 1 y " + board.length + ": "

  def currentPlayerIs(player: Symbol): String = "\nEl actual jugador es '" + player.name + "'"

  def draw(board: List[Symbol]): String = "¡Empate!\n"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "Jugador '" + marker.name + "' ganó en las casillas " + posToStr + "\n"
      case _ => ""
    }
  }
}
