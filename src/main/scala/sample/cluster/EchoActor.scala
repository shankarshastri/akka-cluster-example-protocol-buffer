package sample.cluster

import akka.actor.{Actor, ActorLogging, Props}
import sample.cluster.router.echo.cluster.{Ping, Pong}

object EchoActor {
  def props = Props(new EchoActor)
}

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

