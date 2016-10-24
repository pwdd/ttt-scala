package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class RunnerSuite extends FunSuite with Matchers {
  val board = Board.newBoard(9)
  val firstHuman = new ttt.player.User(Board.firstPlayer)
  val secondHuman = new ttt.player.User(Board.secondPlayer)
  val computer = new ttt.player.computer.Computer(Board.secondPlayer)
  val messenger = new ttt.messenger.English
  val language = "1\n"
  val againstHuman = "1\n"
  val againstComputer = "2\n"
  val threeByTree = "3\n"
  val fourByFour = "4\n"

  def mock(input: String, secondPlayer: ttt.player.Player) = {
    lazy val stream = new ByteArrayOutputStream()
    lazy val in = new ByteArrayInputStream(input.getBytes())

    def methodRun() = Runner.play()

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy methodRun()
      }
    }
  }

  test("play: does not throw exception when human vs human") {
    mock(language + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman)
  }

  test("play: does not throw exception when human vs computer in a 3x3 board") {
    mock(language + againstComputer + threeByTree + "1\n2\n4\n", computer)
  }

  test("play: does not throw exception when human vs computer in a 4x4 board") {
    mock(language + againstComputer + fourByFour + "1\n3\n5\n7\n9\n11\n12\n16", computer)
  }

  test("play: does not throw exception when user enters invalid board size") {
    mock(language + againstHuman + "0\n" + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman)
    mock(language + againstComputer + "a\n" + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16", computer)
  }

  test("play: does not throw exception when user enters invalid language") {
    mock("0\n" + language + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n", secondHuman)
    mock("a\n" + language + againstComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16", computer)
  }
}