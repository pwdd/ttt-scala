package ttt.player.computer

import scala.util.Random

class EasyComputer(val marker: Symbol) extends ttt.player.Player {
  val isAI = true

  def getSpot(board: List[Symbol]) = {
    val spots = availableSpots(board)
    Random.shuffle(spots).head
  }

  private val availableSpots = ttt.Board.availableSpots _
}
