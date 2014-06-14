// Copyright 2014 Jump Start Consulting, LLC.
package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

trait PersonProfile {

  def id: Long
  def firstName: String
  def lastName: String
}

trait interestedPersonProfile extends PersonProfile {
  def email: String
}

/*
object SignupResult extends Enumeration("USER_EXISTS", "USER_CREATED_UNVERIFIED", "USER_CREATED",
  "USER_EXISTS_UNVERIFIED")
{
  type SignupResult = Value
  val USER_EXISTS, USER_CREATED_UNVERIFIED, USER_CREATED, USER_EXISTS_UNVERIFIED = Value
}
*/

case class Person(
  id: Long,
  firstName: String,
  lastName: String,
  email: String,
  signupResult: Int
) extends interestedPersonProfile

object Person {

  val person = {
    get[Long]("id") ~ get[String]("firstName") ~ get[String]("lastName") ~ get[String]("email") ~
      get[Int]("signupResult") map {
      case id~firstName~lastName~email~signupResult => Person(id, firstName, lastName, email, signupResult)
    }
  }

  def all(): List[Person] = DB.withConnection { implicit c =>
    SQL("select * from person").as(person *)
  }

  def create(firstName: String, lastName: String, email: String, signupResult: Int) {
    DB.withConnection { implicit c =>
      SQL("INSERT INTO person(firstName, lastName, email, signupResult) values({firstName}, {lastName}, {email}, " +
        "{signupResult}").on(
        'firstName -> firstName, 'lastName -> lastName, 'email -> email, 'signupResult -> signupResult).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection{ implicit c =>
      SQL("delete from person where id = {id}").on('id -> id).executeUpdate()
    }
  }
}



