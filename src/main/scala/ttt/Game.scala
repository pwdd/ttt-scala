package ttt

import ttt.messenger._
import ttt.player.Player
import ttt.player.computer.Computer

class Game(val messenger: Messenger) {
  def isComputerXComputer(firstPlayer: Player, secondPlayer: Player): Boolean = {
    firstPlayer.isInstanceOf[Computer] && secondPlayer.isInstanceOf[Computer]
  }

  def gameLoop(board: List[Symbol], currentPlayer: Player, opponent: Player, messenger: Messenger): Unit = {
    val spot = currentPlayer.getSpot(board)
    val newBoard = Board.move(board, currentPlayer.marker, spot)

    def wait = if (isComputerXComputer(currentPlayer, opponent)) View.wait(1000)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if EvalGame.isDraw(b) => View.printMessage(messenger.draw(b))
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
      gameLoop(newBoard, opponent, currentPlayer, messenger)
    }
  }
}
