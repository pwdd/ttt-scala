package ttt

object Runner {
  def play(waitTime: Int = 0): Unit = {

    def chosenLanguage = {
      val choice = getInput(
        "\nEnter " + english + " for English | " +
          "Introduzca " + spanish + " para Español | " +
          "Digite " + portuguese + " para Português: ",
        "Not valid | No es válido | Não é um número válido",
        validation.isValidLanguage)

      choice match {
        case es if choice == spanish => new messenger.Spanish
        case pt if choice == portuguese => new messenger.Portuguese
        case _ => new messenger.English
      }
    }

    val messengerSuite = chosenLanguage

    val game = new Game(messengerSuite)

    val gameType= getInput(
      messengerSuite.chooseGameType,
      messengerSuite.invalidGameType,
      validation.isValidGameType)

    def defineComputer(marker: Symbol, first: Boolean) = {
      val choice = getInput(
        messengerSuite.computerLevel(first),
        messengerSuite.invalidComputerLevel,
        validation.isValidComputerType)
      if (choice == easy) new player.computer.EasyComputer(marker)
      else new player.computer.HardComputer(marker)
    }

    def getOpponent: player.Player = gameType match {
      case bothHuman if gameType == humanXHuman =>
        new player.User(
          secondPlayer, messengerSuite)
      case _ => defineComputer(secondPlayer, false)
    }

    def getFirstPlayer: player.Player = gameType match {
      case cxc if gameType == computerXComputer =>
        defineComputer(firstPlayer, true)
      case _ => new player.User(
        firstPlayer, messengerSuite)
    }

    val currentPlayer = getFirstPlayer

    val opponent = getOpponent

    val boardDimension = getInput(
      messengerSuite.chooseBoardDimension,
      messengerSuite.invalidBoardDimension,
      Validation.isValidBoardDimension)

    val board = Board.newBoard(Board.length(boardDimension.toInt))

    View.printMessage(messengerSuite.currentPlayerIs(firstPlayer))
    View.printMessage(messengerSuite.strBoard(board))

    game.gameLoop(board, currentPlayer, opponent, messengerSuite, waitTime)
  }

  private val firstPlayer = Board.firstPlayer
  private val secondPlayer = Board.secondPlayer
  private val getInput = Prompt.getUserChoice _
  private val validation = Validation
  private lazy val english = validation.validLanguages('english)
  private lazy val spanish = validation.validLanguages('spanish)
  private lazy val portuguese = validation.validLanguages('portuguese)
  private lazy val easy = validation.validComputerTypes('easy)
  private lazy val humanXHuman = validation.validGameTypes('humanXHuman)
  private lazy val computerXComputer = validation.validGameTypes('computerXComputer)
}
