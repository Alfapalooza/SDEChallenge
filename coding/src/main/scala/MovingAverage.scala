trait MovingAverage {

  /**
   * @return all elements
   */
  def underlying: Seq[Double]

  /**
   * @return insert element
   */
  def insert(values: Double*): MovingAverage

  /**
   * @return average of last N element
   */
  def average(n: Int): Double

}
