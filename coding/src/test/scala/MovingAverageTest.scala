import org.scalatest.{Matchers, fixture}

abstract class MovingAverageTest extends fixture.FlatSpec with Matchers {

  override type FixtureParam = MovingAverage

  "MovingAverage.insert" should "insert elements at the tail of the collection" in { collection =>
    val newCollection = collection.insert(0d, 15d, 30d).insert(45d).insert(60d)
    newCollection.underlying shouldEqual Seq(0d, 15d, 30d, 45d, 60d)
  }

  "MovingAverage.average" should "return the correct moving average of a small collection" in { collection =>
    val newCollection = collection.insert(15d, 13d, 8d, 20d, 31d, 103d)
    newCollection.average(1) shouldBe 103d
    newCollection.average(2) shouldBe 67d
    newCollection.average(3) shouldBe 51.333333333333336d
    newCollection.average(4) shouldBe 40.5d
    newCollection.average(5) shouldBe 35d
    newCollection.average(6) shouldBe 31.666666666666668d
  }

}