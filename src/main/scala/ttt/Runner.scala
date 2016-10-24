package ttt

import ttt.messenger._
import ttt.player.computer.Computer
import ttt.player.{Player, User}

object Runner {

  def play(): Unit = {

    def chosenLanguage = {
      val choice = Prompt.getUserChoice(
        "\nEnter " + Validation.validLanguages('english) + " for English | " +
          "Introduzca " + Validation.validLanguages('spanish) + " para Español | + " +
          "Digite " + Validation.validLanguages('portuguese) + " para Português: ",
        "Not valid | No es válido | Não é um número válido",
        Validation.isValidLanguage)

      choice match {
        case spanish if choice == Validation.validLanguages('spanish) => new Spanish
        case portuguese if choice == Validation.validLanguages('portuguese) => new Portuguese
        case _ => new English
      }
    }

    val messenger = chosenLanguage

    val game = new Game(messenger)

    def getOpponent(gameType: String): Player = {
      if (gameType == Validation.validGameTypes('humanXHuman)) {
        new User(Board.secondPlayer, messenger.chooseANumber, messenger.invalidMove)
      } else {
        new Computer(Board.secondPlayer)
      }
    }

    val gameType= Prompt.getUserChoice(
      messenger.chooseGameType,
      messenger.invalidGameType,
      Validation.isValidGameType)

    val opponent = getOpponent(gameType)

    val boardDimension = Prompt.getUserChoice(
      messenger.chooseBoardDimension,
      messenger.invalidBoardDimension,
      Validation.isValidBoardDimension)

    val board = Board.newBoard(Board.length(boardDimension.toInt))

    View.printMessage(messenger.currentPlayerIs(Board.firstPlayer))
    View.printMessage(messenger.strBoard(board))

    game.gameLoop(board, new User(Board.firstPlayer, messenger.chooseANumber, messenger.invalidMove), opponent, messenger)
  }
}
