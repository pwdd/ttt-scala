package ttt

object Game {
  def gameLoop(board: List[Symbol], currentPlayer: Symbol, opponent: Symbol): Unit = {
    val spot = Prompt.getSpot(board, Messenger.chooseANumber)
    val newBoard = Board.move(board, currentPlayer, spot)

    View.printMessage(Messenger.currentPlayerIs(opponent))
    View.printMessage(Messenger.strBoard(newBoard))

    if (Rules.gameOver(newBoard)) {
      if (Rules.isDraw(newBoard)) println(Messenger.draw(newBoard))
      else println(Messenger.winner(Rules.winner(newBoard), Rules.winCombo(newBoard)))
    } else {
      gameLoop(newBoard, opponent, currentPlayer)
    }
  }

  def play(): Unit = {
    val board = Board.newBoard(Board.boardLength)

    def initialMsg() = {
      View.printMessage("Starting the game...")
      View.printMessage(Messenger.currentPlayerIs(Board.firstPlayer))
      View.printMessage(Messenger.strBoard(board))
    }

    initialMsg()
    gameLoop(board, Board.firstPlayer, Board.secondPlayer)
  }
}
