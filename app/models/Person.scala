// Copyright 2014 Jump Start Consulting, LLC.
package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

//Use trait eventually. See https://stackoverflow.com/questions/13199198/using-auto-incrementing-fields-with-postgresql-and-slick
trait PersonT {
  def firstName: String
  def lastName: String
}

case class NewPerson(firstName: String, lastName: String, email: String)
case class Person(id: Long, firstName: String, lastName: String, email: String)

object NewPerson {

  val newPerson = {
    get[String]("firstName") ~ get[String]("lastName") ~ get[String]("email") map {
      case firstName~lastName~email => NewPerson(firstName, lastName, email)
    }
  }

  def create(firstName: String, lastName: String, email: String): Person = {
    DB.withConnection { implicit c =>
      val id: Long = SQL("INSERT INTO person(firstname, lastname, email) VALUES({firstname}, {lastname}, {email})").on(
        'firstname -> firstName, 'lastname -> lastName, 'email -> email).executeInsert(scalar[Long] single)

      return Person.find(id)
    }
  }

}

object Person {

  val parser = {
    get[Long]("id") ~
      get[String]("firstname") ~
      get[String]("lastname") ~
      get[String]("email") map {
      case id ~ firstname ~ lastname ~ email => Person(id, firstname, lastname, email)
    }
  }

  val person = {
    get[Long]("id") ~ get[String]("firstName") ~ get[String]("lastName") ~ get[String]("email") map {
      case id~firstName~lastName~email => Person(id, firstName, lastName, email)
    }
  }

  def all(): List[Person] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM person").as(person *)
  }

  def delete(id: Long) {
    DB.withConnection{ implicit c =>
      SQL("DELETE FROM person WHERE id = {id}").on('id -> id).executeUpdate()
    }
  }

  def find(id: Long): Person = {
    DB.withConnection{ implicit c =>
      SQL("SELECT id, firstname, lastname, email FROM person WHERE id = {id}").on('id -> id).using(Person.parser).single()
    }
  }
}



