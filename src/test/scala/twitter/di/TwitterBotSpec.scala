package twitter.di

import org.mockito._
import org.specs2.mutable._

class TwitterBotSpec extends Specification {
  val twitterService = Mockito.mock(classOf[TwitterService])
  val twitterBot = new TwitterBot(twitterService)
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
