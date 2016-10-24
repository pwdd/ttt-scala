package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import ttt.player.computer.Computer
import ttt.player.{Player, User}

class GameSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)
  val firstHuman = new User(Board.firstPlayer)
  val secondHuman = new User(Board.secondPlayer)
  val computer = new Computer(Board.secondPlayer)
  val messenger = new ttt.messenger.English
  val language = "1\n"

  def mock(input: String, secondPlayer: Player) = {
    lazy val game = new Game(new ttt.messenger.English)
    lazy val stream = new ByteArrayOutputStream()
    lazy val in = new ByteArrayInputStream(input.getBytes())

    def methodRun() = game.gameLoop(board, firstHuman, secondPlayer, messenger)

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy methodRun()
      }
    }
  }

  test("gameLoop: does not throw exception when players enter only valid input") {
    mock("1\n2\n3\n4\n5\n6\n7\n", secondHuman)
  }

  test("gameLoop: does not throw exception when there is a winner") {
    mock("1\n4\n2\n5\n3\n", secondHuman)
  }

  test("gameLoop: does not throw exception when there is no winner") {
    mock("1\n2\n3\n5\n8\n4\n6\n9\n7\n", secondHuman)
  }

  test("gameLoop: does not throw exception when players enter invalid input") {
    mock("1\n1\n2\na\n\n3\n4\n5\n6\n7\n", secondHuman)
  }

  test("gameLoop: does not throw exception when game is against computer") {
    mock("1\n2\n4\n", computer)
  }
}
