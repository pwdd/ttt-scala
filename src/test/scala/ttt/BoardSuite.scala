package ttt

import org.scalatest.FunSuite

class BoardSuite extends FunSuite{
  import Board._

  test("newBoard: creates a list that contains only emptySpots") {
    val board = newBoard(9)
    assert(board.filterNot(_ === emptySpot) === List())
  }

  test("newBoard: creates a list with that has length equal to length paramenter") {
    val board = newBoard(9)
    assert(board.length === 9)
  }
}
