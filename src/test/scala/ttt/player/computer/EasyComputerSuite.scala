package ttt.player.computer

import org.scalatest.FunSuite

class EasyComputerSuite extends FunSuite {
  val x = ttt.Board.firstPlayer
  val e = ttt.Board.emptySpot
  val computer = new EasyComputer(x)

  test("getSpot: returns an index of an available spot") {
    val board = ttt.Board.newBoard(9)
    assert(ttt.Board.availableSpots(board) contains computer.getSpot(board))
  }

  test("getSpot: returns an index of an available spot in a board with some spots taken") {
    val board = List(
      x, e, e,
      e, e, e,
      e, e, e)
    val spots = ttt.Board.availableSpots(board)
    val spot = computer.getSpot(board)
    assert(spots contains spot)
    assert(spot !== 0)
  }
}
