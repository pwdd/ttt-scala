package ttt

import org.scalatest.FunSuite

class EvalGameSuite extends FunSuite {
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  test("winCombo: returns the indexes of the second row") {
    val board = List(
      x, o, e,
      x, x, x,
      o, o, e)
    assert(EvalGame.winCombo(board) === List(3, 4, 5))
  }

  test("winCombo: returns the indexes of the third column") {
    val board = List(
      x, o, o,
      x, e, o,
      e, x, o)
    assert(EvalGame.winCombo(board) === List(2, 5, 8))
  }

  test("winCombo: returns the indexes of a diagonal") {
    val board = List(
      e, o, x,
      e, x, o,
      x, e, o)
    assert(EvalGame.winCombo(board) === List(2, 4, 6))
  }

  test("winCombo: returns an empty list if there is no winning combo") {
    val board = List(
      x, x, o,
      o, o, x,
      x, o, x)
    assert(EvalGame.winCombo(board) === List())
  }

  test("isDraw: returns false if board is not full") {
    assert(!EvalGame.isDraw(List(
      x, x, o,
      e, x, x,
      o, o, x)))
  }

  test("isDraw: returns false if board is full and there is a winner") {
    assert(!EvalGame.isDraw(List(
      x, x, x,
      o, o, x,
      o, x, o)))
  }

  test("isDraw: returns true if board is full and there is no winner") {
    assert(EvalGame.isDraw(List(
      x, x, o,
      o, o, x,
      x, o, x)))
  }

  test("gameOver: returns false if board is empty") {
    assert(!EvalGame.gameOver(Board.newBoard(9)))
  }

  test("gameOver: returns false if there are emptySpots and no winner") {
    assert(!EvalGame.gameOver(List(
      x, x, e,
      o, o, e,
      e, e, x)))
  }

  test("gameOver: returns true if board is full and there is no winner") {
    assert(EvalGame.gameOver(List(
      x, x, o,
      o, o, x,
      x, o, x)))
  }

  test("gameOver: returns true if board is not full but there is a winner") {
    assert(EvalGame.gameOver(List(
      x, x, x,
      o, o, e,
      e, e, e)))
  }

  test("winnerMarker: returns firstPlayer if it won the game") {
    assert(EvalGame.winnerMarker(List(
      x, x, x,
      o, o, e,
      e, e, e)) === Some(x))
  }

  test("winnerMarker: returns secondPlayer if it won the game") {
    assert(EvalGame.winnerMarker(List(
      x, x, o,
      e, o, e,
      o, x, e)) === Some(o))
  }

  test("winnerMarker: returns None if there is no winner") {
    assert(EvalGame.winnerMarker(Board.newBoard(9)) === None)
  }

  test("currentPlayerMarker: returns first player if board is empty") {
    assert(EvalGame.currentPlayerMarker(Board.newBoard(9)) === x)
  }

  test("currentPlayerMarker: returns first player if board has even number of moves") {
    val board = List(
      x, o, e,
      e, e, e,
      e, e, e
    )
    assert(EvalGame.currentPlayerMarker(board) === x)
  }

  test("currentPlayerMarker: returns second player if it has made less moves") {
    val board = List(
      x, o, x,
      e, e, e,
      e, e, e
    )
    assert(EvalGame.currentPlayerMarker(board) === o)
  }

  test("currentPlayerMarker: returns first player if it has made less moves") {
    val board = List(
      x, o, x,
      o, o, e,
      e, e, e
    )
    assert(EvalGame.currentPlayerMarker(board) === x)
  }

  test("getOpponentMarker: returns first player if current player is the second") {
    assert(EvalGame.opponentMarker(Board.secondPlayer) === Board.firstPlayer)
  }

  test("getOpponentMarker: returns second player if current player is the first") {
    assert(EvalGame.opponentMarker(Board.firstPlayer) === Board.secondPlayer)
  }
}
