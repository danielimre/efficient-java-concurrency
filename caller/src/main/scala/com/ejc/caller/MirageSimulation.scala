package com.ejc.caller

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.apache.http.client.methods.HttpDelete
import org.apache.http.impl.client.HttpClientBuilder

import scala.concurrent.duration._

case class Employee(id: Int, name: String, address: String, email: String, age: Int)

class MirageSimulation extends Simulation {

  var id = 0;

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("*/*")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")


  val addEmployeeScn = scenario("Add Employee")
    .exec(http("add")
      .post("/employee/add")
      .header("Content-Type", "application/json")
      .body(StringBody(write(getRandomEmployee())(DefaultFormats))).asJson)

  val getAllScn = scenario("GetAllEmployees")
    .exec(http("getAll")
      .get("/employee/list")
      .check(status.is(200)))
      .pause(5)


  before {
    deleteAllEmployees()
  }

  setUp {
    //getAllScn.inject(constantUsersPerSec(10) during (5 seconds)).protocols(httpProtocol)
    //addEmployeeScn.inject(atOnceUsers(1)).protocols(httpProtocol)
    getAllScn.inject(atOnceUsers(1)).protocols(httpProtocol)
  }

  def getRandomEmployee() : Employee = {
    id += 1
    Employee(id, "name", "address", "email@email.com", 37)
  }

  def deleteAllEmployees(): Unit = {
    HttpClientBuilder.create().build().execute(new HttpDelete("http://localhost:8080/employee/deleteAll"))
  }
}
