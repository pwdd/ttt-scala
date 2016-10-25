package ttt.player.computer

import org.scalatest.FunSuite
import ttt.Board
import ttt.player.Player

class HardComputerSuite extends FunSuite {
  val x = Board.firstPlayer
  val o = Board.secondPlayer
  val e = Board.emptySpot
  val computer = new HardComputer(o)

  test("Computer extends 'Player'") {
    assert(computer.isInstanceOf[Player])
  }

  test("getSpot: places marker in the middle if 3x3 board is empty") {
    assert(computer.getSpot(Board.newBoard(9)) === 4)
  }

  test("getSpot: placer marker in the middle if 4x4 board is empty") {
    assert(computer.getSpot(Board.newBoard(16)) === 6)
  }

  test("getSpot: blocks opponent from winning in a 3x3 board") {
    val board = List(
      x, x, e,
      e, e, e,
      o, e, e)
    assert(computer.getSpot(board) === 2)
  }

  test("getSpot: blocks opponent from winning in a 4x4 board") {
    val board = List(
      x, x, x, e,
      o, e, e, e,
      o, e, e, e,
      e, e, e, e)
    assert(computer.getSpot(board) === 3)
  }

  test("getSpot: wins when it has the chance in a 3x3 board") {
    val board = List(
      x, x, e,
      o, o, e,
      x, e, e)
    assert(computer.getSpot(board) === 5)
  }

  test("getSpot: wins when it has the chance in a 4x4 board") {
    val board = List(
      x, x, x, e,
      o, o, o, e,
      x, e, e, e,
      e, e, e, e)
    assert(computer.getSpot(board) === 7)
  }

  test("getSpot: avoids fork") {
    val board = List(
      o, e, e,
      e, x, e,
      e, e, x)
    assert(List(2, 6).contains(computer.getSpot(board)))
  }
}
