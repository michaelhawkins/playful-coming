// Copyright 2014 Jump Start Consulting, LLC.

package controllers

import play.api._
import play.api.mvc._
import play.api.db._
import play.api.Play.current
import com.sun.xml.internal.bind.v2.TODO
import models.Person
import play.api.data._
import play.api.data.Forms._

object Registration extends Controller {

  /** Add actions here */

  val personForm = Form(
    mapping(
    "id" -> longNumber(),
    "firstName" -> nonEmptyText,
    "lastName" -> nonEmptyText,
    "email" -> nonEmptyText,
    "signupResult" -> number
    )(Person.apply)(Person.unapply)
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

  def newPerson = Action { implicit request =>
    personForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Person.all(), errors)),
      personData => {
        Person.create(personData.firstName, personData.lastName, personData.email, personData.signupResult)
        Redirect(routes.Registration.people)
      }
    )
  }

  def deletePerson(id: Long) = Action {
    Person.delete(id)
    Redirect(routes.Registration.people)
  }

}