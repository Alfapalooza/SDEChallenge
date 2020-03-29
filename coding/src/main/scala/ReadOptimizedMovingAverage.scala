/**
 * Trades space and write times for fast average lookup.
 *
 * Writes - T: O(N) / S: O(N)
 * Reads - T: O(1)
 */
case class ReadOptimizedMovingAverage(underlying: Double*) extends MovingAverage {

  private val cache: Map[Int, Double] =
    primeCache()(underlying.view.reverse.zipWithIndex.toSeq)

  /**
   * @return insert element
   */
  override def insert(values: Double*): MovingAverage =
    ReadOptimizedMovingAverage(underlying ++ values: _*)

  /**
   * @return average of last N element
   */
  override def average(n: Int): Double = {
    require(n > 0 && n <= underlying.size)

    cache.getOrElse(n, 0d) / n
  }

  private def primeCache(acc: Map[Int, Double] = Map.empty): Seq[(Double, Int)] => Map[Int, Double] = {
    case Nil =>
      acc

    case (value, idx) :: tail if idx == 0 =>
      primeCache(acc = acc + (1 -> value))(tail)

    case (value, idx) :: tail =>
      primeCache(acc = acc + ((idx + 1) -> (value + acc(idx))))(tail)
  }

}

object ReadOptimizedMovingAverage {

  def empty: ReadOptimizedMovingAverage =
    new ReadOptimizedMovingAverage

}