package ttt

import org.scalatest.FunSuite
import Board._

class BoardSuite extends FunSuite{
  val length = 9
  val emptyBoard = newBoard(length)
  val e = emptySpot
  val x = firstPlayer
  val o = secondPlayer

  test("newBoard: creates an List that contains only emptySpots") {
    assert(emptyBoard.filterNot(_ === e) === List())
  }

  test("newBoard: creates an List with that has length equal to length parameter") {
    assert(emptyBoard.length === 9)
  }

  test("move: places a marker on a board position") {
    assert(move(emptyBoard, x, 1) === List(e, x, e, e, e, e, e, e, e))
  }

  test("move: places a marker on a board that has some spots taken") {
    assert(move(List(x, o, e, e, e, x, e, e, e), o, 3) === List(x, o, e, o, e, x, e, e, e))
  }

  test("isFull: returns false if board has only emptySpots") {
    assert(isFull(emptyBoard) === false)
  }

  test("isFull: returns false if there is any emptySpot") {
    assert(isFull(List(x, x, o, e, x, o, x, o, x)) === false)
  }

  test("isSpotAvailable: returns true if spot has an emptySpot") {
    assert(isSpotAvailable(emptyBoard, 1) === true)
  }

  test("isSpotAvailable: returns false if spot has a marker") {
    assert(!isSpotAvailable(List(x, e, e, e, e, e, e, e, e), 0))
  }

  test("availableSpots: returns the all the indexes of board if board is empty") {
    assert(availableSpots(emptyBoard) == List.range(0, length))
  }

  test("availableSpots: returns a List with the indexes that have emptySpots") {
    assert(availableSpots(List(x, x, e, o, o, e, e, x, o)) == List(2, 5, 6))
  }

  test("hasRepeatedMarkers: returns false if board has only emptySpots") {
    assert(!hasRepeatedMarkers(emptyBoard, List(0, 1, 2)))
  }

  test("hasRepeatedMarkers: returns false if board is full and there is no winner") {
    val board = List(x, e, x,
                     e, e, x,
                     x, x, e)
    assert(!hasRepeatedMarkers(board, List(0, 1, 2)))
  }

  test("hasRepeatedMarkers: returns false if there is no repeated markers on winningPositions") {
    val board = List(x, x, e,
                     o, o, e,
                     e, x, o)
    assert(!hasRepeatedMarkers(board, List(0, 1, 2)))
  }

  test("hasRepeatedMarkers: returns true if there are repeated markers on a given combo") {
    val board = List(x, x, e,
                     o, o, o,
                     e, x, e)
    assert(hasRepeatedMarkers(board, List(3, 4, 5)))
  }
}
