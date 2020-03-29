/**
 * Fast writes, lookups not so much.
 *
 * Writes - T: O(1) / S: O(N)
 * Reads - T: O(N)
 */
case class WriteOptimizedRunningAverage(underlying: Double*) extends MovingAverage {

  /**
   * @return insert element
   */
  override def insert(values: Double*): MovingAverage =
    WriteOptimizedRunningAverage(underlying ++ values: _*)

  /**
   * @return average of last N element
   */
  override def average(n: Int): Double = {
    require(n > 0 && n <= underlying.size)

    ((underlying.size - n) until underlying.size)
      .foldLeft(0d) {
        case (acc, idx) if idx + 1 == underlying.size =>
          (acc + underlying(idx)) / n

        case (acc, idx) =>
          acc + underlying(idx)
      }
  }

}

object WriteOptimizedRunningAverage {

  def empty: WriteOptimizedRunningAverage =
    new WriteOptimizedRunningAverage

}
