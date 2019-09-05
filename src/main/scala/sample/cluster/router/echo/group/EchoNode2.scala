package sample.cluster.router.echo.group

import akka.actor.ActorSystem
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory
import sample.cluster.EchoActor

object EchoNode2 extends App {
  val commonConfig = ConfigFactory.load("application-cluster-router-group.conf")
  implicit val system1 = ActorSystem("cluster", ConfigFactory.parseString(
    """
      |akka.remote.netty.tcp.port = 2553
    """.stripMargin).withFallback(commonConfig))
  val cluster = Cluster(system1)
  system1.actorOf(EchoActor.props, "echoActorNode2")
}
