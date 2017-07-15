import com.rabbitmq.client.{Channel, ConnectionFactory}

/**
  * Created by ranand on 5/3/2017 AD.
  */
object RabbitMQProducer {
  def main(args: Array[String]): Unit = {

    val queueName = "test"
    val host = "localhost"
    val factory = new ConnectionFactory()

    factory.setHost(host)
    //factory.setUsername("exp")
    //factory.setPassword("expadmin")
    val connection = factory.newConnection()
    val channel: Channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)
    for (i <- 1 to 100) {
      val json =
        s"""
     {
        "status" : 0,
        "message" : "OK",
        "id" : $i

     }
     """
      channel.basicPublish("", queueName, null, json.getBytes)
    }

  }
}
