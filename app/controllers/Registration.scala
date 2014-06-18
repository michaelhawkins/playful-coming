// Copyright 2014 Jump Start Consulting, LLC.

package controllers

import play.api._
import play.api.mvc._
import play.api.db._
import play.api.Play.current
import com.sun.xml.internal.bind.v2.TODO
import models._
import play.api.data._
import play.api.data.Forms._

object Registration extends Controller {

  /** Sign Up Form definition */

  val personForm: Form[Person] = Form(
    mapping(
      "id" -> longNumber,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> nonEmptyText(minLength = 6), //shortest domain is k.st add @ + 1 letter and the min email length is 6
      "zipCode" -> nonEmptyText(minLength = 5, maxLength = 5)
    )(Person.apply)(Person.unapply)
  )

  val newPersonForm: Form[NewPerson] = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> nonEmptyText(minLength = 6), //shortest domain is k.st add @ + 1 letter and the min email length is 6
      "zipCode" -> nonEmptyText(minLength = 5, maxLength = 5)
    )(NewPerson.apply)(NewPerson.unapply)
  )


  def people = Action {
    Ok(views.html.listSignup(Person.all(), personForm))
  }

  def newSignup = Action {
    Ok(views.html.newSignup(newPersonForm))
  }

  def createSignup = Action { implicit request =>
    newPersonForm.bindFromRequest.fold(
      errors => BadRequest(views.html.newSignup(errors)),
      person => {
        //Ok(person.firstName)
        val newPerson = NewPerson.create(person.firstName, person.lastName, person.email, person.zipCode)
        Ok(views.html.signupSuccess(newPerson.id, newPerson.firstName, newPerson.lastName, newPerson.email, newPerson.zipCode))
//        Redirect(routes.Registration.signupSuccess(newPerson))
      }
    )
  }

  def deleteSignup(id: Long) = Action {
    Person.delete(id)
    Redirect(routes.Registration.people)
  }

  def show(id: Long) = Action {
    val x =  Person.find(id)
    Ok(views.html.signupSuccess(x.id, x.firstName, x.lastName, x.email, x.zipCode))
  }

  /*def signupSuccess = Action {
//    Ok(views.html.signupSuccess(person))
    Ok()
  }*/

}