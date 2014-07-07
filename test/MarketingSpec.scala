/**
Copyright 2014 Jump Start Consulting 201
*/
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import play.api.test._
import play.api.test.Helpers._
import play.api.i18n.Messages


@RunWith(classOf[JUnitRunner])
class MarketingSpec extends Specification {

  "should display certain elements on the marketing pages" in new WithBrowser {
    //  about page
    browser.goTo("http://localhost:" + port + "/about")
    browser.$("h1").first.getText.contains("About us")

    // team page
    browser.goTo("http://localhost:" + port + "/team")
    browser.$("h1").first.getText.contains("Our team")

    // how it works page
    browser.goTo("http://localhost:" + port + "/how")
    browser.$("h1").first.getText.contains("How it works")
  }

  "take you to contactSuccess page" in new WithBrowser {
    browser.goTo("http://localhost:" + port + "/contact")
    //browser.pageSource must contain("Contact us")

    val phone: String = "216-555-1234"
    val email: String = "christine@playfultutorials.org"

    browser.$("#firstName").text("Christine")
    browser.$("#lastName").text("Smith")
    browser.$("#phone").text(phone)
    browser.$("#email").text(email)
    browser.$("#comments").text("Your web design needs work")
    browser.$(".btn-primary").click()

    browser.pageSource must contain("Thank you for contacting us. One of our team members will contact you shortly")

    browser.pageSource must contain("The " + Messages("global.appName") + " team")

  }

}
