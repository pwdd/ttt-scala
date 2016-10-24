package ttt

import ttt.messenger._
import ttt.player.Player

class Game(val messenger: Messenger) {
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
}
