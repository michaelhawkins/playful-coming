// Copyright 2014 Jump Start Consulting, LLC.
package person

import scala.Enumeration

trait PersonProfile {

  def id: String
  def firstName: String
  def middleName: Option[String]
  def lastName: String
  def dob: String
}

trait interestedPerson extends PersonProfile {
  def email: String
}

object SignupResult extends Enumeration("USER_EXISTS", "USER_CREATED_UNVERIFIED", "USER_CREATED",
  "USER_EXISTS_UNVERIFIED")
{
  type SignupResult = Value
  val USER_EXISTS, USER_CREATED_UNVERIFIED, USER_CREATED, USER_EXISTS_UNVERIFIED = Value
}

case class Person(
  id: String,
  firstName: String,
  middleName: String,
  lastName: String,
  dob: String,
  email: String,
  signupResult: person.SignupResult
) extends interestedPerson




