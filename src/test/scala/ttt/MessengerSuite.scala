package ttt

import org.scalatest.FunSuite

class MessengerSuite extends FunSuite {
  val x = Board.firstPlayer
  val o = Board.secondPlayer
  val e = Board.emptySpot

  test("printBoard: returns a string version of the board") {
    val separator = "\n---|---|---\n"
    assert(Messenger.strBoard(List(x, o, x, o, x, o, x, o, x))  == "\n" +
                                                                   " x | o | x " +
                                                                     separator +
                                                                   " o | x | o " +
                                                                     separator +
                                                                   " x | o | x " +
                                                                   "\n")
  }
}
