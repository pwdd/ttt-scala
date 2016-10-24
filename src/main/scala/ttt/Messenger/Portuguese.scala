package ttt.Messenger

class Portuguese extends Messenger {
  val chooseANumber = "Entre um número de 1 a 9: "

  val invalidMove = "\nSua escolha não é válida. \n"

  val chooseGameType =
    "Que tipo de jogo você gostaria de jogar?\n\n" +
      ttt.Validation.validGameTypes('humanXHuman) +
      ". Humano x Humano\n" +
      ttt.Validation.validGameTypes('humanXComputer) +
      "2. Humano x Computador imbatível\n\n" +
      "Por favor, entre o número correspondente: "

  val invalidGameType = "\nEsse tipo de jogo não existe. \n"

  val chooseBoardDimension =
    "Escolha o tamanho do tabuleiro:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Entre '" + ttt.Validation.validBoardDimensions('threeByThree) +
      "' ou '" + ttt.Validation.validBoardDimensions('fourByFour) + "': "

  val invalidBoardDimension = "\nNão existe tabuleiro com essa dimensão. \n"

  def currentPlayerIs(player: Symbol): String = "\nAgora é a vez do jogador '" + player.name + "'"

  def draw(board: List[Symbol]): String = "Velha!\n"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "O jogador '" + marker.name + "' venceu nas casas " + posToStr + "\n"
      case _ => ""
    }
  }
}
