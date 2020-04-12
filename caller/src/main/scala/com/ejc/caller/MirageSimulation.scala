package com.ejc.caller

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class MirageSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")

  val deleteAllScn = scenario("DeleteAll")
    .exec(http("deleteAll")
      .delete("/employee/deleteAll"))

  val scn = scenario("GetAllEmployees")
    .exec(http("getAll")
      .get("/employee/listStatic"))

  setUp {
    scn.inject(constantUsersPerSec(10) during (10 seconds)).protocols(httpProtocol)
    //scn.inject(atOnceUsers(1)).protocols(httpProtocol)
  }
}
