package controllers

import play.api._
import play.api.mvc._
import com.sun.xml.internal.bind.v2.TODO
import models._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import play.api.libs.ws._
import scala.concurrent.Future
import play.api.libs.json._
import play.api.i18n._


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
        val newContact = NewContact.create(contact.firstName, contact.lastName, contact.phone, contact.email, contact.comments)

        Ok(views.html.marketing.contactSuccess(newContact.firstName, newContact.lastName))
      }

    )
    /**
    val holder: WSRequestHolder = WS.url(Messages("global.MandrillURL"))
    //    This returns a WSRequestHolder that you can use to specify various HTTP options, such as setting headers. You can chain calls together to construct complex requests.

    val complexHolder: WSRequestHolder =
      holder.withHeaders("Accept" -> "application/json")
        .withRequestTimeout(10000)
        .withQueryString("search" -> "play")

    val data = Json.obj(
      "key1" -> "value1",
      "key2" -> "value2"
    )
    val futureResponse: Future[WSResponse] = WS.url(Messages("global.MandrillURL")).post(data)
*/
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