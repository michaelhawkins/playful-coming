import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Be the first to hear about our launch")
      contentAsString(home) must contain ("Register")
    }

    "render the about page" in new WithApplication{
      val about = route(FakeRequest(GET, "/about")).get

      status(about) must equalTo(OK)
      contentType(about) must beSome.which(_ == "text/html")
      contentAsString(about) must contain ("About")
    }

    "render the how it works page" in new WithApplication{
      val how = route(FakeRequest(GET, "/how")).get

      status(how) must equalTo(OK)
      contentType(how) must beSome.which(_ == "text/html")
      contentAsString(how) must contain ("How it works")
    }

    "render the team page" in new WithApplication{
      val team = route(FakeRequest(GET, "/team")).get

      status(team) must equalTo(OK)
      contentType(team) must beSome.which(_ == "text/html")
      contentAsString(team) must contain ("Our team")
    }

    "render the contact page" in new WithApplication{
      val contact = route(FakeRequest(GET, "/contact")).get

      status(contact) must equalTo(OK)
      contentType(contact) must beSome.which(_ == "text/html")
      contentAsString(contact) must contain ("Contact us")
    }


  }
}