trait RealEnvironment
  extends UserServiceComponent
  with UserRepositoryComponent
{
  val userRepository = new UserRepository
  val userService = new UserService
}

object Main extends RealEnvironment {
  def main(args: Array[String]) {
    val user = userService.authenticate("foo", "bar")
    println("authenticated user is " + user)
  }
}
