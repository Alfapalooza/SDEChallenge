import org.scalatest.Outcome

class ReadOptimizedMovingAverageTest extends MovingAverageTest {

  override protected def withFixture(test: OneArgTest): Outcome =
    test(ReadOptimizedMovingAverage.empty)

}
