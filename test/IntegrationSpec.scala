import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.i18n.Messages

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Be the first to hear about our launch")
    }

    "take you to signupSuccess page" in new WithBrowser {
      browser.goTo("http://localhost:" + port)

      val email: String = "jackson@playfultutorials.org"

      browser.$("#firstName").text("Jackson")
      browser.$("#lastName").text("Smith")
      browser.$("#email").text(email)
      browser.$("#zipCode").text("45202")
      browser.$(".btn-primary").click()

      //browser.pageSource must contain("Thank you for signing up to hear more")
      browser.pageSource must contain("Once we launch we will email you at")

    }

    "take you to contactSuccess page" in new WithBrowser {
      browser.goTo("http://localhost" + port + "/contact")

      browser.$("#firstName").text("Christine")
      browser.$("#lastName").text("Smith")
      val phone: String = "216-555-1234"
      browser.$("#phone").text(phone)
      val email: String = "christine@playfultutorials.org"
      browser.$("#email").text(email)
      browser.$("#comments").text("Your web design needs work")
      browser.$(".btn-primary").click()

      browser.pageSource must contain("Thank you for contacting us. One of our team members will contact you at "
        + email + " or at " + phone)

      browser.pageSource must contain("The " + Messages("global.appName") + " team")

    }
  }
}
