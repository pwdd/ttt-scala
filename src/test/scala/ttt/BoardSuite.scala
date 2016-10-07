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
    assert(emptyBoard.filterNot(_ == e) == List())
  }

  test("newBoard: creates an List with that has length equal to length parameter") {
    assert(emptyBoard.length == 9)
  }

  test("move: places a marker on a board position") {
    assert(move(emptyBoard, x, 1) == List(e, x, e, e, e, e, e, e, e))
  }

  test("move: places a marker on a board that has some spots taken") {
    assert(move(List(x, o, e, e, e, x, e, e, e), o, 3) == List(x, o, e, o, e, x, e, e, e))
  }

  test("isFull: returns false if board has only emptySpots") {
    assert(!isFull(emptyBoard))
  }

  test("isFull: returns false if there is any emptySpot") {
    assert(!isFull(List(x, x, o, e, x, o, x, o, x)))
  }

  test("isSpotAvailable: returns true if spot has an emptySpot") {
    assert(isSpotAvailable(emptyBoard, 1))
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

  test("winCombo: returns the indexes of the second row if it has repeated markers") {
    val board = List(x, o, e,
                     x, x, x,
                     o, o, e)
    assert(winCombo(board) == List(3, 4, 5))
  }

  test("winCombo: returns the indexes of the third column if it has repeated markers") {
    val board = List(x, o, o,
                     x, e, o,
                     e, x, o)
    assert(winCombo(board) == List(2, 5, 8))
  }

  test("winCombo: returns the indexes of a diagonal if it has repeated markers") {
    val board = List(e, o, x,
                     e, x, o,
                     x, e, o)
  }

  test("isValidMove: returns false if arg is smaller than 0") {
    assert(!isValidMove(emptyBoard, -1))
  }

  test("isValidMove: returns false if arg is equal or bigger than board length") {
    assert(!isValidMove(emptyBoard, 9))
  }

  test("isValidMove: returns false if arg is a spot that has a marker") {
    val board = List(e, x, e, e, e, e, e, e, e)
    assert(!isValidMove(board, 1))
  }

  test("isValidMove: returns true if arg is a spot with an emptySpot") {
    assert(isValidMove(emptyBoard, 0))
  }
}
