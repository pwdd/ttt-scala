package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class GameSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)
  val firstHuman = new User(Board.firstPlayer)
  val secondHuman = new User(Board.secondPlayer)
  val computer = new Computer(Board.secondPlayer)
  val messenger = new ttt.Messenger.English
  val language = "1\n"
  val againstHuman = "1\n"
  val againstComputer = "2\n"
  val threeByTree = "3\n"
  val fourByFour = "4\n"

  def mock(input: String, secondPlayer: Player, method: Symbol) = {
    lazy val stream = new ByteArrayOutputStream()
    lazy val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy methodRun()
      }
    }

    def methodRun() = {
      if (method == 'loop) Game.gameLoop(board, firstHuman, secondPlayer, messenger)
      else Game.play()
    }
  }

  test("gameLoop: does not throw exception when players enter only valid input") {
    mock(language + "1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when there is a winner") {
    mock(language + "1\n4\n2\n5\n3\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when there is no winner") {
    mock(language + "1\n2\n3\n5\n8\n4\n6\n9\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when players enter invalid input") {
    mock(language + "1\n1\n2\na\n\n3\n4\n5\n6\n7\n", secondHuman, 'loop)
  }

  test("gameLoop: does not throw exception when game is against computer") {
    mock(language + "1\n2\n4\n", computer, 'loop)
  }

  test("play: does not throw exception when human vs human") {
    mock(language + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'play)
  }

  test("play: does not throw exception when human vs computer in a 3x3 board") {
    mock(language + againstComputer + threeByTree + "1\n2\n4\n", computer, 'play)
  }

  test("play: does not throw exception when human vs computer in a 4x4 board") {
    mock(language + againstComputer + fourByFour + "1\n3\n5\n7\n9\n11\n12\n16", computer, 'play)
  }

  test("play: does not throw exception when user enters invalid board size") {
    mock(language + againstHuman + "0\n" + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'play)
    mock(language + againstComputer + "a\n" + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16", computer, 'play)
  }

  test("play: does not throw exception when user enters invalid language") {
    mock("0\n" + language + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman, 'play)
    mock("a\n" + language + againstComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16", computer, 'play)
  }
}




