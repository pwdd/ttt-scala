package ttt

object Runner {

  def play(waitTime: Int = 0): Unit = {

    def chosenLanguage = {
      val choice = Prompt.getUserChoice(
        "\nEnter " + Validation.validLanguages('english) + " for English | " +
          "Introduzca " + Validation.validLanguages('spanish) + " para Español | " +
          "Digite " + Validation.validLanguages('portuguese) + " para Português: ",
        "Not valid | No es válido | Não é um número válido",
        Validation.isValidLanguage)

      choice match {
        case spanish if choice == Validation.validLanguages('spanish) => new messenger.Spanish
        case portuguese if choice == Validation.validLanguages('portuguese) => new messenger.Portuguese
        case _ => new messenger.English
      }
    }

    val messengerSuite = chosenLanguage

    val game = new Game(messengerSuite)

    val gameType= Prompt.getUserChoice(
      messengerSuite.chooseGameType,
      messengerSuite.invalidGameType,
      Validation.isValidGameType)

    def defineComputer(marker: Symbol, first: Boolean) = {
      val choice = Prompt.getUserChoice(
        messengerSuite.computerLevel(first),
        messengerSuite.invalidComputerLevel,
        Validation.isValidComputerType)
      if (choice == Validation.validComputerTypes('easy)) new player.computer.EasyComputer(marker)
      else new player.computer.HardComputer(marker)
    }

    def getOpponent: player.Player = gameType match {
      case bothHuman if gameType == Validation.validGameTypes('humanXHuman) =>
        new player.User(
          Board.secondPlayer, messengerSuite)
      case _ => defineComputer(Board.secondPlayer, false)
    }

    def getFirstPlayer: player.Player = gameType match {
      case computerXComputer if gameType == Validation.validGameTypes('computerXComputer) =>
        defineComputer(Board.firstPlayer, true)
      case _ => new player.User(
        Board.firstPlayer, messengerSuite)
    }

    val firstPlayer = getFirstPlayer

    val opponent = getOpponent

    val boardDimension = Prompt.getUserChoice(
      messengerSuite.chooseBoardDimension,
      messengerSuite.invalidBoardDimension,
      Validation.isValidBoardDimension)

    val board = Board.newBoard(Board.length(boardDimension.toInt))

    View.printMessage(messengerSuite.currentPlayerIs(Board.firstPlayer))
    View.printMessage(messengerSuite.strBoard(board))

    game.gameLoop(board, firstPlayer, opponent, messengerSuite, waitTime)
  }
}
