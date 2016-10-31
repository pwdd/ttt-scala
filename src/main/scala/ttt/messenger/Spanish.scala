package ttt.messenger

class Spanish extends Messenger {

  val invalidMove = "\nSu opción no es válida. \n"

  val chooseGameType =
    "¿Qué tipo de juego le gustaría jugar?\n\n" +
      humanXHuman +
      ". Humano x Humano\n" +
      humanXComputer +
      ". Humano x Computadora\n" +
      computerXComputer +
      ". Computadora x Computadora\n\n" +
      "Por favor, introduzca el número correspondiente: "

  val invalidGameType = "\nNo hay un juego de este tipo. \n"

  val chooseBoardDimension =
    "Elige la dimensión del tablero:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Por favor introduzca '" + threeByThree +
      "' o '" + fourByFour + "': "

  val invalidBoardDimension = "\nNo hay un tablero con esta dimensión. \n"

  val draw = "¡Empate!\n"

  val invalidComputerLevel = "No hay computadora con este nivel\n"

  def computerLevel(first: Boolean): String = {
    val choices =
      easy +
        ". fácil\n" +
        hard +
        ". imbatible\n\n"

    val order = if (first) "La primera" else "La segunda"

    order +
      " computadora puede ser \n\n" +
        choices +
        "Por favor, introduzca el número correspondiente:"
  }

  def chooseANumber(boardLength: Int) = "Introduzca un número entre 1 y " + boardLength + ": "

  def currentPlayerIs(player: Symbol): String = "\nEl actual jugador es '" + player.name + "'"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "Jugador '" + marker.name + "' ganó en las casillas " + posToStr + "\n"
      case _ => ""
    }
  }
}
