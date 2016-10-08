package ttt

import Board._

object Messenger {

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val separator = "\n---|---|---\n"

    def symbolToStr(marker: Symbol): String = {
      if (marker == emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(boardSize).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    buildStrBoard(board.map(symbolToStr)).toString
  }
}
