// Copyright 2014 Jump Start Consulting, LLC.
package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Person(id: Long, email: String)

object Person {

  val person = {
    get[Long]("id") ~ get[String]("email") map {
      case id~email => Person(id, email)
    }
  }

  def all(): List[Person] = DB.withConnection { implicit c =>
    SQL("select * from person").as(person *)
  }

  def create(email: String) {
    DB.withConnection { implicit c =>
      SQL("INSERT INTO person(email) values({email})").on('email -> email).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection{ implicit c =>
      SQL("delete from person where id = {id}").on('id -> id).executeUpdate()
    }
  }
}



