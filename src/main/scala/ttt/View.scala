package ttt

import scala.io.StdIn.readLine

object View {
  def prompt(message: String): String = {
    readLine(message)
  }

  def printMessage(message: String): Unit = {
    println(message)
  }
}
