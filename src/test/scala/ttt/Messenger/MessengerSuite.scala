package ttt.Messenger

import org.scalatest.FunSuite
import ttt.Board

class MessengerSuite extends FunSuite {
  val x = Board.firstPlayer
  val o = Board.secondPlayer
  val e = Board.emptySpot
  val messenger = new ttt.Messenger.English

  test("printBoard: returns a string version of a 3x3 board") {
    val separator = "\n---|---|---\n"
    assert(messenger.strBoard(List(
      x, o, x,
      o, x, o,
      x, o, x))  ===

      "\n" +
        " x | o | x " +
        separator +
        " o | x | o " +
        separator +
        " x | o | x " +
        "\n")
  }

  test("printBoard: returns a string version of a 4x4 board") {
    val separator = "\n---|---|---|---\n"
    assert(messenger.strBoard(List(
      x, o, x, o,
      x, o, x, o,
      o, x, o, x,
      o, x, o, x
    )) ===

    "\n" +
      " x | o | x | o " +
      separator +
      " x | o | x | o " +
      separator +
      " o | x | o | x " +
      separator +
      " o | x | o | x " +
      "\n"
    )
  }
}
