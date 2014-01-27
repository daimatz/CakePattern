package user

import org.mockito._
import org.specs2.mutable._

trait TestEnvironment
  extends UserServiceComponent
  with UserRepositoryComponent
{
  // all dependant objects are mocked
  val userRepository = Mockito.mock(classOf[UserRepository])
  val userService = Mockito.mock(classOf[UserService])
}

class UserServiceSpec extends Specification with TestEnvironment {
  // create a non-Mock object that you want to test
  override val userService = new UserService
  "authenticate" should {
    "return user" in {
      val user = User("test", "test")
      // note that denpendant object `userRepository` is mocked
      Mockito.when(userRepository.authenticate(user)).thenReturn(user)
      userService.authenticate("test", "test") must_== user
    }
  }
}
