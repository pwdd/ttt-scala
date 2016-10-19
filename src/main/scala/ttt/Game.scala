package ttt

object Game {
  def gameLoop(board: List[Symbol], currentPlayer: Player, opponent: Player): Unit = {
    val spot = currentPlayer.getSpot(board)
    val newBoard = Board.move(board, currentPlayer.marker, spot)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if EvalGame.isDraw(b) => View.printMessage(Messenger.draw(b))
      case _ => View.printMessage(Messenger.win(EvalGame.winnerMarker(board), EvalGame.winCombo(board)))
    }

    if (EvalGame.gameOver(newBoard)) {
      finalMsg(newBoard)
      View.printMessage(Messenger.strBoard(newBoard))
    } else {
      View.printMessage(Messenger.currentPlayerIs(opponent.marker))
      View.printMessage(Messenger.strBoard(newBoard))
      gameLoop(newBoard, opponent, currentPlayer)
    }
  }

  def play(): Unit = {
    View.printMessage("Starting the game...")

    val choiceGameType= Prompt.getUserChoice(
      Messenger.chooseGameType,
      Messenger.invalidGameType,
      Validation.isValidGameType)

    val opponent = if (choiceGameType == "1") new User(Board.secondPlayer) else new Computer(Board.secondPlayer)

    val choiceBoardDimension = Prompt.getUserChoice(
      Messenger.chooseBoardDimension,
      Messenger.invalidBoardDimension,
      Validation.isValidBoardDimension)

    val board = Board.newBoard(Board.length(choiceBoardDimension.toInt))

    View.printMessage(Messenger.currentPlayerIs(Board.firstPlayer))
    View.printMessage(Messenger.strBoard(board))


    gameLoop(board, new User(Board.firstPlayer), opponent)
  }
}
