package ttt

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest.FunSuite

class PromptSuite extends FunSuite {
  val stream = new ByteArrayOutputStream()
  val board = Board.newBoard(9)
  val e = Board.emptySpot
  val x = Board.firstPlayer
  val o = Board.secondPlayer

  def mock(board: List[Symbol], input: String, expected: Int) = {
    val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        assert(Prompt.getSpot(board, "foo") === expected)
      }
    }
  }

  test("getSpot: returns index if input - 1 is in range") {
    mock(board, "1", 0)
  }
  
  test("getSpot: returns index if input - 1 is index of emptySpot") {
    mock(board, "3", 2)
  }
  
  test("getSpot: recurs if input is invalid") {
    mock(board, "0\na\n1", 0)
  }

  test("getSpot: recurs if input - 1 is index of a non emptySpot") {
    val board = List(x, x, o,
                     e, e, e,
                     e, e, e)
    mock(board, "1\n4", 3)
  }
}

