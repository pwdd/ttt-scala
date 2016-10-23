package ttt

import ttt.Messenger._

object Game {
  val validGameTypes = Map('humanXHuman -> "1", 'humanXComputer -> "2")
  val validBoardDimensions = Map('threeByThree -> "3", 'fourByFour -> "4")
  val validLanguages = Map('english -> "1", 'spanish -> "2")

  private def chosenLanguage = {
    val choice = Prompt.getUserChoice(
      "\nEnter 1 for English | Introduzca 2 para Español: ",
      "Not valid | No es válido",
      Validation.isValidGameType)

    if (choice == validLanguages('english)) new English else new Spanish
  }

  def gameLoop(board: List[Symbol], currentPlayer: Player, opponent: Player, messenger: Messenger): Unit = {
    val spot = currentPlayer.getSpot(board)
    val newBoard = Board.move(board, currentPlayer.marker, spot)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if EvalGame.isDraw(b) => View.printMessage(messenger.draw(b))
      case _ => View.printMessage(messenger.win(EvalGame.winnerMarker(board), EvalGame.winCombo(board)))
    }

    if (EvalGame.gameOver(newBoard)) {
      View.printMessage(messenger.strBoard(newBoard))
      finalMsg(newBoard)
    } else {
      View.printMessage(messenger.currentPlayerIs(opponent.marker))
      View.printMessage(messenger.strBoard(newBoard))
      gameLoop(newBoard, opponent, currentPlayer, messenger)
    }
  }

  def play(): Unit = {

    val messenger = chosenLanguage

    def getOpponent(gameType: String): Player = {
      if (gameType == validGameTypes('humanXHuman)) {
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

    gameLoop(board, new User(Board.firstPlayer, messenger.chooseANumber, messenger.invalidMove), opponent, messenger)
  }
}
