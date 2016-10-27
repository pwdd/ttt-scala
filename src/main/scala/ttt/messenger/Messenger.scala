package ttt.messenger

abstract class Messenger {
  val invalidMove: String
  val chooseGameType: String
  val invalidGameType: String
  val chooseBoardDimension: String
  val invalidBoardDimension: String
  val draw: String
  val invalidComputerLevel: String

  val validation = ttt.Validation

  val humanXHuman = validation.validGameTypes('humanXHuman)
  val humanXComputer = validation.validGameTypes('humanXComputer)
  val computerXComputer = validation.validGameTypes('computerXComputer)

  val threeByThree = validation.validBoardDimensions('threeByThree)
  val fourByFour = validation.validBoardDimensions('fourByFour)

  val english = validation.validLanguages('english)
  val spanish = validation.validLanguages('spanish)
  val portuguese = validation.validLanguages('portuguese)

  val easy = validation.validComputerTypes('easy)
  val hard = validation.validComputerTypes('hard)

  val boardSize = ttt.Board.size _

  def computerLevel(first: Boolean): String

  def chooseANumber(boardLength: Int): String

  def currentPlayerIs(player: Symbol): String

  def win(winner: Option[Symbol], position: List[Int]): String

  def strBoard(board: List[Symbol]): String = {
    val pipe = "|"
    val dashes = "---"
    val separator = "\n" + List.fill(boardSize(board))(dashes).mkString(pipe) + "\n"

    def symbolToStr(marker: Symbol): String = {
      if (marker == ttt.Board.emptySpot) "   "
      else " " + marker.name + " "
    }

    def buildStrBoard(board: List[String]): Any = {
      val breakLines = board.grouped(boardSize(board)).toList.map(_.mkString(pipe))
      breakLines.mkString(separator)
    }

    "\n" + buildStrBoard(board.map(symbolToStr)).toString + "\n"
  }
}
