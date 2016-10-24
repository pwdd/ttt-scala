package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class RunnerSuite extends FunSuite with Matchers {
  val secondHuman = new ttt.player.User(Board.secondPlayer)
  val computer = new ttt.player.computer.Computer(Board.secondPlayer)
  val english = "1\n"
  val spanish = "2\n"
  val portuguese = "3\n"
  val againstHuman = "1\n"
  val againstComputer = "2\n"
  val computerXComputer = "3\n"
  val threeByTree = "3\n"
  val fourByFour = "4\n"

  def mock(input: String) = {
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
    mock(english + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
  }

  test("play: does not throw exception when human vs computer in a 3x3 board") {
    mock(english + againstComputer + threeByTree + "1\n2\n4\n")
  }

  test("play: does not throw exception when human vs computer in a 4x4 board") {
    mock(english + againstComputer + fourByFour + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters invalid board size") {
    mock(english + againstHuman + "0\n" + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock(english + againstComputer + "a\n" + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters invalid language") {
    mock("0\n" + english + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock("a\n" + english + againstComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters spanish or portuguese") {
    mock(spanish + againstHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock(portuguese + againstComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user chooses computer x computer game") {
    mock(english + computerXComputer + threeByTree)
  }
}
