package ttt

import scala.io.StdIn._

object Prompt {
  def prompt(message: String): String = readLine(message).trim

  def getUserChoice(messageAsk: String, messageInvalid: String, validation: (String) => Boolean): String = {
    val userChoice = prompt(messageAsk)
    if (validation(userChoice)) userChoice
    else getUserChoice(messageAsk, messageInvalid, validation)
  }
}
