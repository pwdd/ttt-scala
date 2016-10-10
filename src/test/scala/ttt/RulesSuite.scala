package ttt

import org.scalatest.FunSuite

class RulesSuite extends FunSuite {
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  test("winCombo: returns the indexes of the second row if it has repeated markers") {
    val board = List(x, o, e,
                     x, x, x,
                     o, o, e)
    assert(Rules.winCombo(board) === List(3, 4, 5))
  }

  test("winCombo: returns the indexes of the third column if it has repeated markers") {
    val board = List(x, o, o,
                     x, e, o,
                     e, x, o)
    assert(Rules.winCombo(board) === List(2, 5, 8))
  }

  test("winCombo: returns the indexes of a diagonal if it has repeated markers") {
    val board = List(e, o, x,
                     e, x, o,
                     x, e, o)
    assert(Rules.winCombo(board) === List(2, 4, 6))
  }

  test("winCombo: returns an empty list if there is no winning combo") {
    val board = List(x, x, o,
                     o, o, x,
                     x, o, x)
    assert(Rules.winCombo(board) === List())
  }

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

  test("winner: returns firstPlayer if it won the game") {
    assert(Rules.winner(List(x, x, x,
                             o, o, e,
                             e, e, e))
                        === x)
  }

  test("winner: returns seconcPlayer if it won the game") {
    assert(Rules.winner(List(x, x, o,
                             e, o, e,
                             o, x, e))
                        === o)
  }
}
