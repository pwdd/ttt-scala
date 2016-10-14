package ttt

import org.scalatest.FunSuite
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

class PromptSuite extends FunSuite {

  def mock(input: String, expected: String) = {
    val stream = new ByteArrayOutputStream()
    val in = new ByteArrayInputStream(input.getBytes())

    Console.withOut(stream) {
      Console.withIn(in) {
        assert(Prompt.getGameType("foo") === expected)
      }
    }
  }

  test("getGameType: returns the user choice if input is valid") {
    mock("1\n", "1")
    mock("  2  \n", "2")
  }

  test("getGameType: recurs if input is invalid") {
    mock("11\n\na\n1", "1")
  }
}
