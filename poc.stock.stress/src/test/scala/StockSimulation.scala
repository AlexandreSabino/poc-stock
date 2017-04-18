
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class StockSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:8080")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json");

  val fileAFeeder = csv("movements.csv").random

  val scn = scenario("StockSimulation")
    .feed(fileAFeeder)
    .during(3 minutes) {
      exec(http("Movement")
        .post("/api/stock")
        .body(ElFileBody("bodies/movement.json"))
      )
    };

//  setUp(
//    scn.inject(
//      nothingFor(4 seconds),
//      atOnceUsers(600),
//      rampUsers(700) over (30 seconds),
//      atOnceUsers(800),
//      constantUsersPerSec(900) during (20 seconds),
//      atOnceUsers(1000),
//      rampUsers(1100) over (30 seconds),
//      atOnceUsers(1200)
//    )
//  )
  setUp(
    scn.inject(
      nothingFor(4 seconds),
      atOnceUsers(2500)
    )
  )
    .protocols(httpConf)
    .assertions(
      global.successfulRequests.percent.gte(98)
    )
}
