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

  test("isValidGameType: returns true if arg is a value in 'Validation.validGameTypes' map") {
    assert(Validation.isValidGameType("1"))
    assert(Validation.isValidGameType("2"))
    assert(Validation.isValidGameType("3"))
  }

  test("isValidGameType: returns false if arg is not a value in 'Validation.validGameTypes' map") {
    assert(!Validation.isValidBoardDimension("0"))
    assert(!Validation.isValidGameType("a"))
  }

  test("isValidBoardDimension: returns true if arg is a value in 'Validation.validBoardDimension' map") {
    assert(Validation.isValidBoardDimension("3"))
    assert(Validation.isValidBoardDimension("4"))
  }

  test("isValidBoardDimension: returns false if arg is not a value in 'Validation.validBoardDimension' map") {
    assert(!Validation.isValidBoardDimension("a"))
    assert(!Validation.isValidBoardDimension("0"))
  }

  test("isValidLanguage: returns true if arg is a value in 'Validation.validLanguages' map") {
    assert(Validation.isValidLanguage("1"))
    assert(Validation.isValidLanguage("2"))
    assert(Validation.isValidLanguage("3"))
  }

  test("isValidLanguage: returns false if arg is not a value in 'Validation.validLanguages' map") {
    assert(!Validation.isValidLanguage("0"))
    assert(!Validation.isValidLanguage("a"))
  }

  test("isValidComputerType: returns true if arg is a value in 'Validation.validComputerTypes' map") {
    assert(Validation.isValidComputerType("1"))
    assert(Validation.isValidComputerType("2"))
  }

  test("isValidComputerType: returns false if arg is not a value in 'Validation.validComputerTypes' map") {
    assert(!Validation.isValidComputerType("a"))
    assert(!Validation.isValidComputerType("0"))
  }
}
