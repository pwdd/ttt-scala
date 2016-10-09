package ttt

object Game {
  def gameLoop(board: List[Symbol], currentPlayer: Symbol, opponent: Symbol): Unit = {
    val spot = Prompt.getSpot(board, Messenger.chooseANumber)
    val newBoard = Board.move(board, currentPlayer, spot)

    println(Messenger.strBoard(newBoard))

    if (Rules.gameOver(newBoard))
      println(Messenger.finalMessage(Rules.winner(newBoard), Rules.winCombo(newBoard)))
    else gameLoop(newBoard, opponent, currentPlayer)
  }

  def play(): Unit = {

    def initialMsg() = {
      println("Welcome to Tic Tac Toe")
      println(Messenger.strBoard(Board.newBoard(9)))
    }

    initialMsg()
    gameLoop(Board.newBoard(9), Board.firstPlayer, Board.secondPlayer)
  }
}
