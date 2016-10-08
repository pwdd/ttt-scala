package ttt

import org.scalatest.FunSuite
import Messenger._
import Board._

class MessengerSuite extends FunSuite {
  val x = firstPlayer
  val o = secondPlayer
  val e = emptySpot

  test("printBoard: returns a string version of the board") {
    val separator = "\n---|---|---\n"
    assert(strBoard(List(x, o, x, o, x, o, x, o, x)) == " x | o | x " +
                                                          separator +
                                                        " o | x | o " +
                                                          separator +
                                                        " x | o | x ")
  }
}
