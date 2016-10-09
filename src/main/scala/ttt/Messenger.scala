package ttt

object Messenger {

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val separator = "\n---|---|---\n"

    val chooseANumber = "Please enter a number from 1 to 9"

    def symbolToStr(marker: Symbol): String = {
      if (marker == Board.emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(Board.boardSize).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    buildStrBoard(board.map(symbolToStr)).toString
  }
}
