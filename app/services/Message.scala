package services
import play.api.libs.json._
import scala.concurrent.Future
import play.api.Play.current
import play.api.libs.ws._
import play.api.i18n._

object Message {


  def emailNewContactWithHtml(firstName: String, lastName: String, email: String) {
        val jsonClass: JsValue = JsObject(Seq(
  //      "key" -> JsString("5t1Q3spLNNtrBesf3gxK7A"),*/
        "key" -> JsString(play.Play.application.configuration.getString("mandrillKey")),
        "message" -> JsObject(
          Seq("html" -> JsString("<p>Test message from emailNewContact " + email + " after email</p>"), 
            "text" -> JsString("Test message from emailNewContact " + email + "after email"),
            "subject" -> JsString("Test subject from emailNewContact"),
            "from_email" -> JsString(Messages("global.fromEmail")),
            "from_name" -> JsString(Messages("global.companyName")),
            "to" -> JsArray(Seq(
                JsObject(Seq("email" -> JsString(email), //this email var isn't working
                "name" -> JsString(firstName + " " + lastName),
                "type" -> JsString("to")
              )))),
        "headers" -> JsObject(Seq(
          "Reply-To" -> JsString(Messages("global.replyToEmail")))),
        "important" -> JsBoolean(false),
        "track_opens" -> JsBoolean(true)))))

      val apiUrl = "https://mandrillapp.com/api/1.0/messages/send.json"
      val futureResponse: Future[Response] = WS.url(apiUrl).post(jsonClass)
  }

  def emailNewContactwithTemplate(firstName: String, lastName: String, email: String) {
        val jsonClass: JsValue = JsObject(Seq(
  //      "key" -> JsString("5t1Q3spLNNtrBesf3gxK7A"),*/
        "key" -> JsString(play.Play.application.configuration.getString("mandrillKey")),
        "template_name" -> JsString("Playful-Prelaunch-Contact-Us"),
        "template_content" -> JsArray(Seq(
          JsObject(Seq("name" -> JsString("firstname"), "content" -> JsString(firstName))))),
        "message" -> JsObject(
          Seq("subject" -> JsString("Thank you for contacting us"),
            "from_email" -> JsString(Messages("global.fromEmail")),
            "from_name" -> JsString(Messages("global.companyName")),
            "to" -> JsArray(Seq(
                JsObject(Seq("email" -> JsString(email), //this email var isn't working
                "name" -> JsString(firstName + " " + lastName),
                "type" -> JsString("to")
              )))),
        "headers" -> JsObject(Seq(
          "Reply-To" -> JsString(Messages("global.replyToEmail")))),
        "important" -> JsBoolean(false),
        "track_opens" -> JsBoolean(true)))))

      val apiUrl = "https://mandrillapp.com/api/1.0/messages/send-template.json"
      val futureResponse: Future[Response] = WS.url(apiUrl).post(jsonClass)
  }

  /* The Mandrill template for above
Hello <div mc:edit="firstname">
  Firstname
</div>

Thank you so much for contacting Playful Prelaunch! An associate will contact you via email soon.

Best regards,

The Playful Prelaunch team

  */

}