package twitter.cake

case class Status(id: Int, userId: String, text: String)

trait TwitterServiceComponent {
  val twitterService: TwitterService
  class TwitterService {
    def tweet(text: String, inReplyTo: Option[Int]): Int = {
      inReplyTo.getOrElse(0)
    }
    def getTimeline(count: Int): Seq[Status] = {
      List()
    }
  }
}

trait TwitterBotComponent {
  self: TwitterServiceComponent =>
  val twitterBot: TwitterBot
  class TwitterBot {
    def eventLoop() {
      val statusIds = action(10)
      Thread.sleep(60 * 1000)
    }
    def action(count: Int): Seq[Int] = {
      val tl = twitterService.getTimeline(count)
      tl.map { status: Status =>
        twitterService.tweet("@" + status.userId + " おやすみ〜", Some(status.id))
      }
    }
  }
}
