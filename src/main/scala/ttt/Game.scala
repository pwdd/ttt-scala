package ttt

object Game {
  def gameLoop(board: List[Symbol], currentPlayer: Symbol, opponent: Symbol): Unit = {
    val spot = Prompt.getSpot(board, Messenger.chooseANumber)
    val newBoard = Board.move(board, currentPlayer, spot)

    println(Messenger.currentPlayerIs(opponent))
    println(Messenger.strBoard(newBoard))

    if (Rules.gameOver(newBoard)) {
      if (Rules.isDraw(newBoard)) println(Messenger.draw(newBoard))
      else println(Messenger.winner(Rules.winner(newBoard), Rules.winCombo(newBoard)))
    } else {
      gameLoop(newBoard, opponent, currentPlayer)
    }
  }

  def play(): Unit = {

    def initialMsg() = {
      println("Starting the game...")
      println(Messenger.currentPlayerIs(Board.firstPlayer))
      println(Messenger.strBoard(Board.newBoard(9)))
    }

    initialMsg()
    gameLoop(Board.newBoard(9), Board.firstPlayer, Board.secondPlayer)
  }
}
