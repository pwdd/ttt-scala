package ttt

import org.scalatest.FunSuite

class BoardSuite extends FunSuite {
  val length = 9
  val emptyBoard = Board.newBoard(length)
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  val rowsThree = List(List(0, 1, 2), List(3, 4, 5), List(6, 7, 8))
  val rowsFour = List(
    List(0, 1, 2, 3),
    List(4, 5, 6, 7),
    List(8, 9, 10, 11),
    List(12, 13, 14, 15))

  test("newBoard: creates a List that contains only emptySpots") {
    assert(emptyBoard.filterNot(_ == e) === List())
  }

  test("newBoard: creates a List that has length equal to parameter") {
    assert(emptyBoard.length == 9)
  }

  test("move: places a marker on a board position") {
    assert(Board.move(emptyBoard, x, 1) ===
      List(
        e, x, e,
        e, e, e,
        e, e, e))
  }

  test("move: places a marker on a board that has some spots taken") {
    assert(Board.move(List(
      x, o, e,
      e, e, x,
      e, e, e),
      o,
      3) ===
      List(
        x, o, e,
        o, e, x,
        e, e, e))
  }

  test("isFull: returns false if board has only emptySpots") {
    assert(!Board.isFull(emptyBoard))
  }

  test("isFull: returns false if there is any emptySpot") {
    assert(!Board.isFull(List(
      x, x, o,
      e, x, o,
      x, o, x)))
  }

  test("isEmpty: returns true if it is new board") {
    assert(Board.isEmpty(emptyBoard))
  }

  test("isEmpty: returns false if there is any marker on board") {
    assert(!Board.isEmpty(List(
      e, e, e,
      x, e, e,
      e, e, e)))
  }

  test("isSpotAvailable: returns true if spot has an emptySpot") {
    assert(Board.isSpotAvailable(emptyBoard, 1))
  }

  test("isSpotAvailable: returns false if spot has a marker") {
    assert(!Board.isSpotAvailable(List(
      x, e, e,
      e, e, e,
      e, e, e),
      0))
  }

  test("availableSpots: returns the all the indexes of board if board is empty") {
    assert(Board.availableSpots(emptyBoard) === List.range(0, length))
  }

  test("availableSpots: returns an empty List if board is full") {
    assert(Board.availableSpots(List(
      x, x, x,
      o, o, x,
      o, o, x)) ===
      List())
  }

  test("availableSpots: returns a List with the indexes that have emptySpots") {
    assert(Board.availableSpots(List(
      x, x, e,
      o, o, e,
      e, x, o)) ===
      List(2, 5, 6))
  }

  test("indexes: is a List from 0 until length of the board") {
    assert(Board.indexes(9).head === 0 && Board.indexes(9).last === 8)
    assert(Board.indexes(16).head === 0 && Board.indexes(16).last === 15)
  }

  test("rows: is a nested List with sequential indexes") {
    assert(Board.rows(Board.indexes(9), 3) === rowsThree)
    assert(Board.rows(Board.indexes(16), 4) === rowsFour)
  }

  test("columns: is a nested List with transposed indexes from rows") {
    assert(Board.columns(rowsThree) === List(List(0, 3, 6), List(1, 4, 7), List(2, 5, 8)))
    assert(Board.columns(rowsFour) === List(
      List(0, 4, 8, 12),
      List(1, 5, 9, 13),
      List(2, 6, 10, 14),
      List(3, 7, 11, 15)
    ))
  }

  test("diagonals") {
    assert(Board.diagonals(rowsThree, 3) === List(List(0, 4, 8), List(2, 4, 6)))
    assert(Board.diagonals(rowsFour, 4) === List(
      List(0, 5, 10, 15),
      List(3, 6, 9, 12)
    ))
  }
}
