package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class GameSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)

  val humanA = new ttt.player.User(Board.firstPlayer, new ttt.messenger.English)
  val humanB = new ttt.player.User(Board.secondPlayer, new ttt.messenger.English)

  val easyComputerA = new ttt.player.computer.EasyComputer(Board.firstPlayer)
  val easyComputerB = new ttt.player.computer.EasyComputer(Board.secondPlayer)

  val hardComputerA = new ttt.player.computer.HardComputer(Board.firstPlayer)
  val hardComputerB = new ttt.player.computer.HardComputer(Board.secondPlayer)

  val messenger = new ttt.messenger.English
  val language = "1\n"

  val game = new Game(new ttt.messenger.English)
  val stream = new ByteArrayOutputStream()

  def mock(input: String, firstPlayer: ttt.player.Player, secondPlayer: ttt.player.Player) = {
    val in = new ByteArrayInputStream(input.getBytes())

    def methodRun() = game.gameLoop(board, humanA, secondPlayer, messenger)

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy methodRun()
      }
    }
  }

  test("gameLoop: does not throw exception in human x human when players enter only valid input") {
    mock("1\n2\n3\n4\n5\n6\n7\n", humanA, humanB)
  }

  test("gameLoop: does not throw exception when there is a winner") {
    mock("1\n4\n2\n5\n3\n", humanA, humanB)
  }

  test("gameLoop: does not throw exception when there is no winner") {
    mock("1\n2\n3\n5\n8\n4\n6\n9\n7\n", humanA, humanB)
  }

  test("gameLoop: does not throw exception when players enter invalid input") {
    mock("1\n1\n2\na\n\n3\n4\n5\n6\n7\n", humanA, humanB)
  }

  test("gameLoop: does not throw exception when game is human x hard computer") {
    mock("1\n2\n4\n", humanA, hardComputerB)
  }

  test("gameLoop: does not throw exception when game is easy computer x hard computer") {
    Console.withOut(stream) {
      noException should be thrownBy game.gameLoop(board, easyComputerA, hardComputerB, messenger)
    }
  }

  test("gameLoop: does not throw exception when game is hard computer x easy computer") {
    Console.withOut(stream) {
      noException should be thrownBy game.gameLoop(board, hardComputerA, easyComputerB, messenger)
    }
  }

  test("gameLoop: does not throw exception when game is easy computer x easy computer") {
    Console.withOut(stream) {
      noException should be thrownBy game.gameLoop(board, easyComputerA, easyComputerB, messenger)
    }
  }

  test("gameLoop: does not throw exception when game is hard computer x hard computer") {
    Console.withOut(stream) {
      noException should be thrownBy game.gameLoop(board, hardComputerA, hardComputerB, messenger)
    }
  }
}
