package ttt.messenger

class Portuguese extends Messenger {

  val invalidMove = "\nSua escolha não é válida. \n"

  val chooseGameType =
    "Que tipo de jogo você gostaria de jogar?\n\n" +
      ttt.Validation.validGameTypes('humanXHuman) +
      ". Humano x Humano\n" +
      ttt.Validation.validGameTypes('humanXComputer) +
      ". Humano x Computador\n" +
      ttt.Validation.validGameTypes('computerXComputer) +
      ". Computador x Computador\n\n" +
      "Por favor, entre o número correspondente: "

  val invalidGameType = "\nEsse tipo de jogo não existe. \n"

  val chooseBoardDimension =
    "Escolha o tamanho do tabuleiro:\n\n" +
      "3 x 3\n" +
      "4 x 4\n\n" +
      "Entre '" + ttt.Validation.validBoardDimensions('threeByThree) +
      "' ou '" + ttt.Validation.validBoardDimensions('fourByFour) + "': "

  val invalidBoardDimension = "\nNão existe tabuleiro com essa dimensão. \n"

  val draw = "Velha!\n"

  val invalidComputerLevel = "Não existe computador deste nível\n"

  def computerLevel(first: Boolean): String = {
    val choices =
      ttt.Validation.validComputerTypes('easy) +
        ". fácil\n" +
        ttt.Validation.validComputerTypes('hard) +
        ". imbatível\n\n"

    val sentence = " computador pode ser \n\n" + choices + "Digite o número correspondente: "

    if (first) "O primeiro" + sentence
    else "O segundo" + sentence
  }

  def chooseANumber(boardLength: Int) = "Entre um número de 1 a " + boardLength + ": "

  def currentPlayerIs(player: Symbol): String = "\nAgora é a vez do jogador '" + player.name + "'"

  def win(winner: Option[Symbol], position: List[Int]): String = {
    val indexToUserFriendlyNumbers = position.map(_ + 1)
    val posToStr = indexToUserFriendlyNumbers.mkString(", ")

    winner match {
      case Some(marker) => "O jogador '" + marker.name + "' venceu nas casas " + posToStr + "\n"
      case _ => ""
    }
  }
}
