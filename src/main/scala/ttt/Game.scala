package ttt

import ttt.messenger._
import ttt.player._

class Game(val messenger: Messenger) {
  def gameLoop(board: List[Symbol],
               currentPlayer: Player,
               opponent: Player,
               messenger: Messenger,
               waitTime: Int = 0): Unit = {

    val spot = currentPlayer.getSpot(board)
    val newBoard = move(board, currentPlayer.marker, spot)

    def delay() = if (isComputerXComputer(currentPlayer, opponent)) sleep(waitTime)

    def finalMsg(board: List[Symbol]): Unit = board match {
      case b if draw(b) => printer(messenger.draw)
      case _ => printer(messenger.win(winner(board), winCombo(board)))
    }

    if (gameOver(newBoard)) {
      delay()
      printer(messenger.strBoard(newBoard))
      finalMsg(newBoard)
    } else {
      delay()
      printer(messenger.currentPlayerIs(opponent.marker))
      printer(messenger.strBoard(newBoard))
      gameLoop(newBoard, opponent, currentPlayer, messenger, waitTime)
    }
  }

  private def isComputerXComputer(firstPlayer: Player, secondPlayer: Player): Boolean = {
    firstPlayer.isAI && secondPlayer.isAI
  }

  private val move = Board.move _
  private val sleep = View.delay _
  private val printer = View.printMessage _
  private val evaluation = EvalGame
  private val draw = evaluation.isDraw _
  private val winner = evaluation.winnerMarker _
  private val winCombo = evaluation.winCombo _
  private val gameOver = evaluation.gameOver _
}
