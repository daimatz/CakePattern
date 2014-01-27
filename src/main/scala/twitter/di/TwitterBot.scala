package twitter.di

case class Status(id: Int, userId: String, text: String)

trait TwitterService {
  def tweet(text: String, inReplyTo: Option[Int]): Int
  def getTimeline(count: Int): Seq[Status]
}

class TwitterBot(twitterService: TwitterService) {
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
