import org.scalatest.Outcome

class WriteOptimizedMovingAverageTest extends MovingAverageTest {

  override protected def withFixture(test: OneArgTest): Outcome =
    test(WriteOptimizedRunningAverage.empty)

}
