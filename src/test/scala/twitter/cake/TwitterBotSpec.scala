package twitter.cake

import org.mockito._
import org.specs2.mutable._

trait TestEnvironment
  extends TwitterServiceComponent
  with TwitterBotComponent {
  val twitterService = Mockito.mock(classOf[TwitterService])
  val twitterBot = Mockito.mock(classOf[TwitterBot])
}

class TwitterBotSpec extends Specification with TestEnvironment {
  override val twitterBot = new TwitterBot
  "action" should {
    "return status ids" in {
      val status1 = Status(id = 1, userId = "foo", text = "Hello")
      val status2 = Status(id = 2, userId = "bar", text = "Scala")
      val statuses = Seq(status1, status2)
      Mockito.when(twitterService.getTimeline(2)).thenReturn(statuses)
      val id1 = 100
      val id2 = 102
      Mockito.when(twitterService.tweet("@foo おやすみ〜", Some(1))).thenReturn(id1)
      Mockito.when(twitterService.tweet("@bar おやすみ〜", Some(2))).thenReturn(id2)
      twitterBot.action(2) must_== Seq(id1, id2)
    }
  }
}
