package ttt

class Player(val marker: Symbol, val role: Symbol) {
  def getSpot(board: List[Symbol], message: String = ""): Int = role match {
    case 'human => User.getSpot(board, message)
    case 'computer => 2
    case _ => 0
  }
}
