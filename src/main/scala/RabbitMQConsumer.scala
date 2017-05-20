import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.{Channel, ConnectionFactory, DefaultConsumer, Envelope}

/**
  * Created by ranand on 5/3/2017 AD.
  */
object RabbitMQConsumer {
  def main(args: Array[String]): Unit = {

    val queueName = "test-queue2"
    val host = "localhost"
    val factory = new ConnectionFactory()
    factory.setHost(host)
    val connection = factory.newConnection()
    val channel: Channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)

    channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]): Unit = {
       val string = new String(body.toSeq.toArray ,"UTF-8")
        println(string)
      }
    })

  }
}
