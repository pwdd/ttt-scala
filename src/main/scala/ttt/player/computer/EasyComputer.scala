package ttt.player.computer

import scala.util.Random

import ttt.Board

class EasyComputer(val marker: Symbol) extends ttt.player.Player {
  val isAI = true

  def getSpot(board: List[Symbol]) = {
    val spots = Board.availableSpots(board)
    Random.shuffle(spots).head
  }
}
