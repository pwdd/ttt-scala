package ttt

import org.scalatest.FunSuite

class ComputerSuite extends FunSuite {
  val computer = new Computer(Board.secondPlayer)
  val x = Board.firstPlayer
  val o = computer.marker
  val e = Board.emptySpot

  test("Computer extends 'Player'") {
    assert(computer.isInstanceOf[Player])
  }

  test("getSpot: places marker in the middle if 3x3 board is empty") {
    assert(computer.getSpot(Board.newBoard(9), "", o, x) === 4)
  }

  test("getSpot: placer marker in the middle if 4x4 board is empty") {
    assert(computer.getSpot(Board.newBoard(16), "", o, x) === 6)
  }

  test("getSpot: blocks opponent from winning") {
    val board = List(
      x, x, e,
      o, e, e,
      o, e, e)
    assert(computer.getSpot(board, "", o, x) === 2)
  }

  test("getSpot: wins when it has the chance") {
    val board = List(
      x, x, e,
      o, o, e,
      e, e, e)
    assert(computer.getSpot(board, "", o, x) === 5)
  }

  test("getSpot: avoids fork") {
    val board = List(
      o, e, e,
      e, x, e,
      e, e, x)
    assert(computer.getSpot(board, "", o, x) === 2 ||
      computer.getSpot(board, "", o, x) === 6)
  }
}
