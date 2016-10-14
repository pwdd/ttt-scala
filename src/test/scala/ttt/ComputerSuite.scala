package ttt

import org.scalatest.FunSuite

class ComputerSuite extends FunSuite {
  val computer = new Computer(Board.secondPlayer)

  test("Computer extends 'Player'") {
    assert(computer.isInstanceOf[Player])
  }
}
