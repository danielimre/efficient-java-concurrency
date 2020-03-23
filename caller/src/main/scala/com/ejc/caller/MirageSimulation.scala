package com.ejc.caller

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class MirageSimulation extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")

  val scn = scenario("GetAllEmployees")
    .exec(http("getAll")
      .get("/employee/list"))
    .pause(1)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
