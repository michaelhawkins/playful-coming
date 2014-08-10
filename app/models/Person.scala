// Copyright 2014 Jump Start Consulting, LLC.
package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import play.api.libs.ws._
import play.api.Play.current

case class NewPerson(firstName: String, lastName: String, email: String, zipCode: String)
case class Person(id: Long, firstName: String, lastName: String, email: String, zipCode:String)

object NewPerson {

  def create(firstName: String, lastName: String, email: String, zipCode: String): Person = {
    DB.withConnection { implicit c =>
      val id: Long = SQL("INSERT INTO person(firstname, lastname, email, zipcode) VALUES({firstname}, {lastname}, " +
        "{email}, {zipcode})").on('firstname -> firstName, 'lastname -> lastName, 'email -> email, 'zipcode -> zipCode)
        .executeInsert(scalar[Long] single)

      return Person.find(id)
    }
  }

}

object Person {

  val parser = {
    get[Long]("id") ~
      get[String]("firstname") ~
      get[String]("lastname") ~
      get[String]("email") ~
      get[String]("zipcode") map {
      case id ~ firstname ~ lastname ~ email ~ zipcode => Person(id, firstname, lastname, email, zipcode)
    }
  }

  val person = {
    get[Long]("id") ~ get[String]("firstName") ~ get[String]("lastName") ~ get[String]("email") ~ get[String]("zipcode") map {
      case id~firstName~lastName~email~zipCode => Person(id, firstName, lastName, email, zipCode)
    }
  }

  def all(): List[Person] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM person").as(Person.person *)
  }

  def delete(id: Long) {
    DB.withConnection{ implicit c =>
      SQL("DELETE FROM person WHERE id = {id}").on('id -> id).executeUpdate()
    }
  }

  def find(id: Long): Person = {
    DB.withConnection{ implicit c =>
      SQL("SELECT id, firstname, lastname, email, zipcode FROM person WHERE id = {id}").on('id -> id).using(Person.parser).single()
    }
  }
}
