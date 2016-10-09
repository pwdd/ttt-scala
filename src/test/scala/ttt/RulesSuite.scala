package ttt

import org.scalatest.FunSuite

class RulesSuite extends FunSuite {
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  test("isDraw: returns false if board is not full") {
    assert(!Rules.isDraw(List(x, x, o,
                              e, x, x,
                              o, o, x)))
  }

  test("isDraw: returns false if board is full and there is a winner") {
    assert(!Rules.isDraw(List(x, x, x,
                              o, o, x,
                              o, x, o)))
  }

  test("isDraw: returns true if board is full and there is no winner") {
    assert(Rules.isDraw(List(x, x, o,
                             o, o, x,
                             x, o, x)))
  }

  test("gameOver: returns false if board is empty") {
    assert(!Rules.gameOver(Board.newBoard(9)))
  }

  test("gameOver: returns false if there are emptySpots and no winner") {
    assert(!Rules.gameOver(List(x, x, e,
                                o, o, e,
                                e, e, x)))
  }

  test("gameOver: returns true if board is full and there is no winner") {
    assert(Rules.gameOver(List(x, x, o,
                               o, o, x,
                               x, o, x)))
  }

  test("gameOver: returns ture if board is not full but there is a winner") {
    assert(Rules.gameOver(List(x, x, x,
                               o, o, e,
                               e, e, e)))
  }

}
