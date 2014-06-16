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
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> nonEmptyText(minLength = 6) //shortest domain is k.st add @ + 1 letter and the min email length is 6
    )
    {
      (firstName, lastName, email) => Person(firstName, lastName, email)
    }
    {
      //Unbinding: Create the mapping values from an existing Person value
      person => Some(person.firstName, person.lastName, person.email)
    }

  )

  val newPersonForm: Form[NewPerson] = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> nonEmptyText(minLength = 6) //shortest domain is k.st add @ + 1 letter and the min email length is 6
    )
    {
      (firstName, lastName, email) => Person(firstName, lastName, email)
    }
    {
      //Unbinding: Create the mapping values from an existing Person value
      newPerson => Some(person.firstName, person.lastName, person.email)
    }

  )

/*  def create = Action { implicit request =>

    Ok(views.html.linkResult(request.user))
  }*/

  def index = Action {
    Redirect(routes.Registration.people)
  }

  def people = Action {
    Ok(views.html.index(Person.all(), personForm))
  }

  def newSignup = Action {
    Ok(views.html.newSignup(personForm))
  }

  def createSignup = Action { implicit request =>
    personForm.bindFromRequest.fold(
      errors => BadRequest(views.html.newSignup(errors)),
      person => {
        Person.create(person.firstName, person.lastName, person.email)
        Redirect(routes.Registration.people)
      }
    )
  }

  def deleteSignup(id: Long) = Action {
    Person.delete(id)
    Redirect(routes.Registration.people)
  }

}