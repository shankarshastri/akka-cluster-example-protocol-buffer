package sample.cluster

import akka.actor.{Actor, ActorLogging}
import sample.cluster.router.echo.cluster.{Ping, Pong}

class EchoActor extends Actor with ActorLogging {
  log.info("Starting")

  override def receive: Receive = {
    case _:Ping =>
      log.info("ping-pong")
      sender() ! Pong()

    case x =>
      log.info(s"Recieving ${x}")
  }
}

