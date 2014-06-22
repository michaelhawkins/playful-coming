// Copyright 2014 Jump Start Consulting, LLC.
package models
import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

/**
 * Created by mike on 6/21/14.
 */
trait ContactT {
  def firstName: String
  def lastName: String
  def phone: String
  def email: String
  def comments: String
}

case class NewContact (firstName: String, lastName: String, phone: String, email: String,
  comments: String)
case class Contact (id:Long, firstName: String, lastName: String, phone: String, email: String,
                    comments: String )

object NewContact {

  val newContact = {
    get[String]("firstName") ~ get[String]("lastName") ~ get[String]("phone") ~ get[String]("email") ~
      get[String]("comments") map {
      case firstName~lastName~phone~email~comments => NewContact(firstName, lastName, phone, email, comments)
    }
  }

  def create(firstName: String, lastName: String, phone: String, email: String, comments: String): Contact = {
    DB.withConnection { implicit c =>
      val id: Long = SQL("INSERT INTO contact(firstname, lastname, phone, email, comments) VALUES({firstname}, {lastname}, " +
        "{phone}, {email}, {comments})").on('firstname -> firstName, 'lastname -> lastName, 'phone -> phone,
        'email -> email, 'comments -> comments)
        .executeInsert(scalar[Long] single)

      return Contact.find(id)
    }
  }

}

object Contact {

  val parser = {
    get[Long]("id") ~
      get[String]("firstname") ~
      get[String]("lastname") ~
      get[String]("phone") ~
      get[String]("email") ~
      get[String]("comments") map {
      case id ~ firstname ~ lastname ~ phone ~ email ~ comments => Contact(id, firstname, lastname, phone, email, comments)
    }
  }

  val contact = {
    get[Long]("id") ~ get[String]("firstName") ~ get[String]("lastName") ~ get[String]("phone") ~ get[String]("email") ~
      get[String]("comments") map {
      case id~firstName~lastName~phone~email~comments => Contact(id, firstName, lastName, phone, email, comments)
    }
  }

  def all(): List[Contact] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM contact").as(contact *)
  }

  def find(id: Long): Contact = {
    DB.withConnection{ implicit c =>
      SQL("SELECT id, firstname, lastname, phone, email, comments FROM contact WHERE id = {id}").on('id -> id).using(Contact.parser).single()
    }
  }
}