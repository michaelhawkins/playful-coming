package controllers

import play.api._
import play.api.mvc._
import com.sun.xml.internal.bind.v2.TODO
import models._
import play.api.data._
import play.api.data.Forms._

object Marketing extends Controller {


  val newContactForm: Form[NewContact] = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "phone" -> nonEmptyText,
      "email" -> nonEmptyText(minLength = 6), //shortest domain is k.st add @ + 1 letter and the min email length is 6
      "comments" -> nonEmptyText(maxLength = 512)
    )(NewContact.apply)(NewContact.unapply)
  )
  /** Add actions here */

  def about = Action {
    Ok(views.html.marketing.about())
  }

  def newContact = Action {
    Ok(views.html.marketing.newContact(newContactForm))
  }

  def createContact = Action { implicit request =>
    newContactForm.bindFromRequest.fold(
      errors => BadRequest(views.html.marketing.newContact(errors)),
      contact => {
        //Ok(person.firstName)
        val newContact = NewContact.create(contact.firstName, contact.lastName, contact.email, contact.phone, contact.comments)
        Ok(views.html.marketing.contactSuccess(newContact.firstName, newContact.lastName))
      }
    )
  }

  def how = Action {
    Ok(views.html.marketing.how())
  }

  def team = Action {
    Ok(views.html.marketing.team())
  }

  def contactList = Action {
    Ok("the list of contacts")
  }



}