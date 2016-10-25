package ttt

import ttt.messenger._
import ttt.player._

class Game(val messenger: Messenger) {

  private def isComputerXComputer(firstPlayer: Player, secondPlayer: Player): Boolean = {
    firstPlayer.isAI && secondPlayer.isAI
  }

  def gameLoop(
                board: List[Symbol],
                currentPlayer: Player,
                opponent: Player,
                messenger: Messenger,
                waitTime: Int = 0): Unit = {
    val spot = currentPlayer.getSpot(board)
    val newBoard = Board.move(board, currentPlayer.marker, spot)

    def wait = if (isComputerXComputer(currentPlayer, opponent)) View.wait(waitTime)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if EvalGame.isDraw(b) => View.printMessage(messenger.draw)
      case _ => View.printMessage(messenger.win(EvalGame.winnerMarker(board), EvalGame.winCombo(board)))
    }

    if (EvalGame.gameOver(newBoard)) {
      wait
      View.printMessage(messenger.strBoard(newBoard))
      finalMsg(newBoard)
    } else {
      wait
      View.printMessage(messenger.currentPlayerIs(opponent.marker))
      View.printMessage(messenger.strBoard(newBoard))
      gameLoop(newBoard, opponent, currentPlayer, messenger, waitTime)
    }
  }
}
