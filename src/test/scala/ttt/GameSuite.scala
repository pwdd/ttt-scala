package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class GameSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)
  val stream = new ByteArrayOutputStream()
  val firstHuman = new User(Board.firstPlayer)
  val secondHuman = new User(Board.secondPlayer)
  val computer = new Computer(Board.secondPlayer)


  def mock(input: String, secondPlayer: Player, method: Symbol) = {
    val in = new ByteArrayInputStream(input.getBytes())

    lazy val methodRun = if (method == 'loop) Game.gameLoop(board, firstHuman, secondPlayer)
                         else Game.play()

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy methodRun
      }
    }
  }

  test("gameLoop: does not throw exception when players enter only valid input") {
    mock("1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when there is a winner") {
    mock("1\n4\n2\n5\n3\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when there is no winner") {
    mock("1\n2\n3\n5\n8\n4\n6\n9\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when players enter invalid input") {
    mock("1\n1\n2\na\n3\n4\n5\n6\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when game is against computer") {
    mock("1\n2\n4\n", computer, 'loop)
  }

  test("play: does not throw exception when human vs human") {
    mock("1\n3\n1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'play)
  }

  test("play: does not throw exception when human vs computer") {
    mock("2\n3\n1\n2\n4\n", computer, 'play)
  }
}

