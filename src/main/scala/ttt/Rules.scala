package ttt

import scala.io.StdIn.readInt

object Rules {
  def isDraw(board: List[Symbol]): Boolean = Board.isBoardFull(board) && Board.winCombo(board).isEmpty

  def gameOver(board: List[Symbol]): Boolean = isDraw(board) || Board.winCombo(board).nonEmpty
}
