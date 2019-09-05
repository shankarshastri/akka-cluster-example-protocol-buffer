package sample.cluster.router.echo.group

import akka.actor.{ActorSystem, Props}
import akka.cluster.Cluster
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import sample.cluster.EchoActor
import sample.cluster.router.echo.cluster.Ping

object EchoActorMaster2 extends App {
  val commonConfig = ConfigFactory.load("application-cluster-router-group.conf")
  implicit val system1 = ActorSystem("cluster", ConfigFactory.parseString(
    """
      |akka.remote.netty.tcp.port = 2554
    """.stripMargin).withFallback(commonConfig))
  val cluster = Cluster(system1)
  val pool = system1.actorOf(FromConfig.props(Props[EchoActor]), "echo")
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._
  cluster.registerOnMemberUp({
    println("Invoking On RegisterMemberUp")
    system1.scheduler.schedule(1.second, 1.second, pool,
      Ping())
  })
}
