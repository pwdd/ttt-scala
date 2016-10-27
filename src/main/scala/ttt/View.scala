package ttt

object View {
  def printMessage(message: String): Unit = {
    println(message)
  }

  def delay(duration: Int) = Thread.sleep(duration)
}
