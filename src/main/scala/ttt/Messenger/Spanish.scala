package ttt.Messenger

class Spanish extends Messenger {
  val chooseANumber = "Introduzca un número entre 1 y 9: "

  val invalidMove = "\nSu opción no es válida. \n"

  val chooseGameType =
    "¿Qué tipo de juego le gustaría jugar?\n\n" +
      "1. Humano vs Humano\n" +
      "2. Humano x Computadora Imbatible\n\n" +
      "Por favor, introduzca el número correspondiente: "

  val invalidGameType = "\nNo hay un juego de este tipo. \n"

  val chooseBoardDimension =
    "Elige la dimensión del tablero:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Por favor introduzca '3' o '4': "

  val invalidBoardDimension = "\nNo hay un tablero con esta dimensión. \n"

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
