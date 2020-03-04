import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}


//窗口操作
object SparkStreaming_Window {
  def main(args: Array[String]): Unit = {
    //spark的配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_Window")
    val streamingContext: StreamingContext = new StreamingContext(sparkConf, Seconds(3))
    //保存数据的状态  设置检查点路径
    streamingContext.sparkContext.setCheckpointDir("SparkStreaming/sparkstreaming_checkpoint")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "wzl",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("spark")
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    val value= stream.map(x=>(x.key(),x.value()))
    //所有基于窗口的操作都需要两个参数，分别为窗口时长以及滑动步长，两者都必须是 StreamContext 的批次间隔的整数倍
    val value1 = value.window(Seconds(9),Seconds(3))
    val value2 = value1.flatMap(x=>x._2.split(" ")).map((_,1))reduceByKey(_+_)
    value2.print()
    //    kafkaDStream.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
