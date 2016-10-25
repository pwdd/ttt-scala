package ttt

import org.scalatest.{FunSuite, Matchers}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class RunnerSuite extends FunSuite with Matchers {
  def getLanguage(language: Symbol) = Validation.validLanguages(language) + "\n"

  val english = getLanguage('english)
  val spanish = getLanguage('spanish)
  val portuguese = getLanguage('portuguese)

  def getGameType(gameType: Symbol) = Validation.validGameTypes(gameType) + "\n"

  val humanXHuman = getGameType('humanXHuman)
  val againstComputer = getGameType('humanXComputer)
  val computerXComputer = getGameType('computerXComputer)

  def getComputerType(computerType: Symbol) = Validation.validComputerTypes(computerType) + "\n"

  val easyComputer = getComputerType('easy)
  val hardComputer = getComputerType('hard)

  def getBoardDimension(dimension: Symbol) = Validation.validBoardDimensions(dimension) + "\n"

  val threeByTree = getBoardDimension('threeByThree)
  val fourByFour = getBoardDimension('fourByFour)

  def mock(input: String) = {
    val stream = new ByteArrayOutputStream()
    val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        noException should be thrownBy Runner.play()
      }
    }
  }

  test("play: does not throw exception when human vs human") {
    mock(english + humanXHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
  }

  test("play: does not throw exception when human vs hard computer in a 3x3 board") {
    mock(english + againstComputer + hardComputer + threeByTree + "1\n2\n4\n")
  }

  test("play: does not throw exception when human vs hard Computer in a 4x4 board") {
    mock(english + againstComputer + hardComputer + fourByFour + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters invalid board size") {
    mock(english + humanXHuman + "0\n" + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock(english + againstComputer + hardComputer + "a\n" + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters invalid language") {
    mock("0\n" + english + humanXHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock("a\n" + english + againstComputer + hardComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user enters spanish or portuguese") {
    mock(spanish + humanXHuman + threeByTree + "1\n2\n3\n4\n5\n6\n7\n")
    mock(portuguese + againstComputer + hardComputer + threeByTree + "1\n3\n5\n7\n9\n11\n12\n16")
  }

  test("play: does not throw exception when user chooses hard computer x hard computer game") {
    mock(english + computerXComputer + hardComputer + hardComputer + threeByTree)
    mock(english + computerXComputer + hardComputer + hardComputer + fourByFour)
  }

  test("play: does not throw exception when user chooses easy computer x hard computer game") {
    mock(english + computerXComputer + easyComputer + hardComputer + threeByTree)
    mock(english + computerXComputer + easyComputer + hardComputer + fourByFour)
  }

  test("play: does not throw exception when user chooses hard computer x easy computer game") {
    mock(english + computerXComputer + hardComputer + easyComputer + threeByTree)
    mock(english + computerXComputer + hardComputer + easyComputer + fourByFour)
  }

  test("play: does not throw exception when user chooses easy computer x easy computer game") {
    mock(english + computerXComputer + easyComputer + easyComputer + threeByTree)
    mock(english + computerXComputer + easyComputer + easyComputer + fourByFour)
  }
}
