package ttt

object View {
  def printMessage(message: String): Unit = {
    println(message)
  }

  def wait(duration: Int) = Thread.sleep(duration)
}
