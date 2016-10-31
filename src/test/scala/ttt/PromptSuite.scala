package ttt

import org.scalatest.FunSuite
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class PromptSuite extends FunSuite {

  def mock(input: String, expected: String, validation: (String) => Boolean) = {
    val stream = new ByteArrayOutputStream()
    val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        assert(Prompt.getUserChoice("foo", "invalid foo", validation) === expected)
      }
    }
  }

  test("getUserChoice: returns the user choice if input is valid") {
    mock("1\n", "1", Validation.isValidGameType)
    mock("  2  \n", "2", Validation.isValidGameType)
    mock("3    \n", "3", Validation.isValidBoardDimension)
    mock("4\n", "4", Validation.isValidBoardDimension)
  }

  test("getUserChoice: recurs if input is invalid") {
    mock("a\n33\n\n1\n", "1", Validation.isValidGameType)
    mock("33\n  2  \n", "2", Validation.isValidGameType)
    mock("1\n3    \n", "3", Validation.isValidBoardDimension)
    mock("abc\n4\n", "4", Validation.isValidBoardDimension)
  }

}
