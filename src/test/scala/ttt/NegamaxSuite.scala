package ttt

import org.scalatest.FunSuite

class NegamaxSuite extends FunSuite {
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  test("score: returns 0 if game will end in a tie") {
    val board = List(o, o, x,
                     x, x, e,
                     o, o, x)
    assert(Negamax.score(board, o, x, 2) === 0)
  }

  test("score: returns 99 if current player will win") {
    val board = List(o, o, e,
                     x, x, o,
                     x, o, x)
    assert(Negamax.score(board, o, x, 0) === 99)
  }

  test("score: returns -98 if current player will win") {
    val board = List(x, o, x,
                     x, x, o,
                     e, o, e)
    assert(Negamax.score(board, o, x, 0) === -98)
  }

  test("bestMove: places marker in the middle if board is empty") {
    assert(Negamax.bestMove(Board.newBoard(Board.length), o, x) === 4)
  }

  test("bestMove: blocks opponent from winning") {
    val board = List(x, x, e,
                     o, e, e,
                     o, e, e)
    assert(Negamax.bestMove(board, o, x) === 2)
  }

  test("bestMove: wins when it has the chance") {
    val board = List(x, x, e,
                     o, o, e,
                     e, e, e)
    assert(Negamax.bestMove(board, o, x) === 5)
  }

  test("bestMove: avoids fork") {
    val board = List(o, e, e,
                     e, x, e,
                     e, e, x)
    assert(Negamax.bestMove(board, o, x) === 2 ||
           Negamax.bestMove(board, o, x) === 6)
  }
}
