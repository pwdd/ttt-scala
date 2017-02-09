package ttt

import scala.io.StdIn.readLine

object Prompt {
  def prompt(message: String): String = readLine(message).trim

  def getUserChoice(messageAsk: String, messageInvalid: String, validation: (String) => Boolean): String = {

    val userChoice = prompt(messageAsk)

    if (validation(userChoice)) {
      userChoice
    }
    else {
      View.printMessage(messageInvalid)
      getUserChoice(messageAsk, messageInvalid, validation)
    }
  }
}
