package ttt.messenger

import ttt.Board

abstract class Messenger {
  val chooseANumber: String
  val invalidMove: String
  val chooseGameType: String
  val invalidGameType: String
  val chooseBoardDimension: String
  val invalidBoardDimension: String

  val languageChoice = "\nEnter 1 for English | Entra 2 para Español: "
  val invalidLanguageChoice = "Not valid | No es válido"

  def currentPlayerIs(player: Symbol): String

  def draw(board: List[Symbol]): String

  def win(winner: Option[Symbol], position: List[Int]): String

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val dashes = "---"
    val separator = "\n" + List.fill(Board.size(board))(dashes).mkString(pipe) + "\n"

    def symbolToStr(marker: Symbol): String = {
      if (marker == Board.emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(Board.size(board)).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    "\n" + buildStrBoard(board.map(symbolToStr)).toString + "\n"
  }
}
