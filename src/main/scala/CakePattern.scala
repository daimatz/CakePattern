case class User(username: String, password: String)

trait UserRepositoryComponent {
  val userRepository: UserRepository
  // ordinal class
  class UserRepository {
    def authenticate(user: User): User = {
      println("authenticating user: " + user)
      user
    }
    def create(user: User) = println("creating user: " + user)
    def delete(user: User) = println("deleting user: " + user)
  }
}

trait UserServiceComponent {
  // specify dependencies by self-type annotation
  self: UserRepositoryComponent =>
  val userService: UserService
  class UserService {
    def authenticate(username: String, password: String): User =
      userRepository.authenticate(new User(username, password))
    def create(username: String, password: String) =
      userRepository.create(new User(username, password))
    def delete(user: User) =
      userRepository.delete(user)
  }
}
