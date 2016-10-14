package ttt

import scala.io.StdIn._

object Prompt {
  def prompt(message: String): String = readLine(message).trim

  def getGameType(message: String): String = {
    val userChoice = prompt(message)
    if (Validation.isValidGameType(userChoice)) userChoice
    else getGameType(Messenger.invalidGameType)
  }
}
