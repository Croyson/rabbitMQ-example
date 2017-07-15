import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.{Channel, ConnectionFactory, DefaultConsumer, Envelope}

/**
  * Created by ranand on 5/3/2017 AD.
  */
object RabbitMQConsumer {
  def main(args: Array[String]): Unit = {

    val queueName = "test"
    val host = "localhost"
    val factory = new ConnectionFactory()
    factory.setHost(host)
    // factory.setUsername("exp")
    // factory.setPassword("expadmin")
    val connection = factory.newConnection()
    val channel: Channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)

    channel.basicConsume(queueName, false, new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]): Unit = {
       val string = new String(body.toSeq.toArray ,"UTF-8")
        println(string)

        val routingKey = envelope.getRoutingKey
        println(routingKey)
        val deliveryTag = envelope.getDeliveryTag
        println(deliveryTag)
        // (process the message components here ...)
        if(string.contains("1")) {
          channel.basicNack(deliveryTag, false, true)
          //channel.basicRecover(false)

        }
        else {
          //here false is to acknowledge only a single message / true would mean acknowledging all messages upto delivery tag
          channel.basicAck(deliveryTag, false)
        }
      }
    })

  }
}
