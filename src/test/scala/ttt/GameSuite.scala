package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class GameSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)
  val stream = new ByteArrayOutputStream()

  def mock(input: String) = {
    val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy Game.gameLoop(board, Board.firstPlayer, Board.secondPlayer)
      }
    }
  }

  test("gameLoop: does not throw exception when players enter only valid input") {
    mock("1\n2\n3\n4\n5\n6\n7\n")
  }

  test("gameLoop: does not throw exception when players enter invalid input") {
    mock("1\n1\n2\na\n3\n4\n5\n6\n7\n")
  }
}

