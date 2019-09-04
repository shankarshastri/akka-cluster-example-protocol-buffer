package sample.cluster.router.echo.pool

import akka.actor.{ ActorSystem }
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory

object EchoNode1 extends App {
  val commonConfig = ConfigFactory.load("application-cluster-router.conf")
  implicit val system1 = ActorSystem("cluster", ConfigFactory.parseString(
    """
      |akka.remote.netty.tcp.port = 2552
    """.stripMargin).withFallback(commonConfig))
  val cluster = Cluster(system1)
}
