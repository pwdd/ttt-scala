package ttt

object Game {
  def gameLoop(board: List[Symbol], currentPlayer: Player, opponent: Player): Unit = {
    val spot = currentPlayer.getSpot(board, Messenger.chooseANumber)
    val newBoard = Board.move(board, currentPlayer.marker, spot)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if Rules.isDraw(b) => println(Messenger.draw(b))
      case _ => println(Messenger.winner(Rules.winner(board), Rules.winCombo(board)))
    }

    if (Rules.gameOver(newBoard)) {
      View.printMessage(Messenger.strBoard(board))
      finalMsg(newBoard)
    } else {
      View.printMessage(Messenger.currentPlayerIs(opponent.marker))
      View.printMessage(Messenger.strBoard(newBoard))
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
    gameLoop(board, new Player(Board.firstPlayer, 'human), new Player(Board.secondPlayer, 'human))
  }
}
