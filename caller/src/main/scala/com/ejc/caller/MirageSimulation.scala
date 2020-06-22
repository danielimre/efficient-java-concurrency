package com.ejc.caller

import com.github.javafaker.Faker

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.apache.http.client.methods.HttpDelete
import org.apache.http.impl.client.HttpClientBuilder

import scala.concurrent.duration._
import java.util.Random

class MirageSimulation extends Simulation {

  val BASE_URL = "http://localhost:8080"
  val EMPLOYEE_DELETE_ALL = "/employee/deleteAll"
  val EMPLOYEE_ADD = "/employee/add"
  val EMPLOYEE_LIST = "/employee/list"

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

  def usersPerSec: Int = getProperty("USERS_PER_SEC", "5").toInt
  def duration: Int = getProperty("DURATION", "2").toInt

  var id = 0;

  val httpProtocol = http
    .baseUrl(BASE_URL)
    .acceptHeader("*/*")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")

  val customFeeder = Iterator.continually(Map(
    "id" -> getId(),
    "name" -> getName(),
    "address" -> getAddress(),
    "email" -> getEmail(),
    "age" -> new Random().nextInt(100)
  ))

  val addEmployeeScn = scenario("Add Employee")
    .exec(
      feed(customFeeder)
          .exec(
            http("add")
              .post(EMPLOYEE_ADD)
              .header("Content-Type", "application/json")
              .body(ElFileBody("Employee.json")).asJson
          )
    )


  val getAllScn = scenario("GetAllEmployees")
    .exec(http("getAll")
      .get(EMPLOYEE_LIST)
      .check(status.is(200)))
      .pause(5)


  before {
    println(s"Running test with ${usersPerSec} users per sec")
    println(s"Total test duration: ${duration} seconds")
    deleteAllEmployees()
  }

  setUp {
    //getAllScn.inject(atOnceUsers(1)).protocols(httpProtocol)
    addEmployeeScn.inject(constantUsersPerSec(usersPerSec) during (duration seconds)).protocols(httpProtocol)
  }

  def getId(): Int = {
    id += 1
    id
  }

  def getName(): String = {
    new Faker(new Random(id)).name().name()
  }

  def getAddress(): String = {
    val faker = new Faker(new Random(id))
    val address = faker.address()
    String.format("%s %s %s %s", address.buildingNumber(), address.streetName(), address.city(), address.country())
  }

  def getEmail(): String = {
    new Faker(new Random(id)).internet().emailAddress()
  }

  def deleteAllEmployees(): Unit = {
    HttpClientBuilder.create().build().execute(new HttpDelete(BASE_URL + EMPLOYEE_DELETE_ALL))
  }
}
