package ttt

import org.scalatest.FunSuite

class ValidationSuite extends  FunSuite {
  val emptyBoard = Board.newBoard(9)
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  test("isValidMove: returns false if arg is smaller than 0") {
    assert(!Validation.isValidMove(emptyBoard, -1))
  }

  test("isValidMove: returns false if arg is equal or bigger than board length") {
    assert(!Validation.isValidMove(emptyBoard, 9))
  }

  test("isValidMove: returns false if arg is a spot that has a marker") {
    val board = List(
      e, x, e,
      e, e, e,
      e, e, e)
    assert(!Validation.isValidMove(board, 1))
  }

  test("isValidMove: returns true if arg is a spot with an emptySpot") {
    assert(Validation.isValidMove(emptyBoard, 0))
  }
}
