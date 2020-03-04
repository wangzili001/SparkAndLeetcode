import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

object SparkStreaming_updateState {
  def main(args: Array[String]): Unit = {
    //spark的配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_updateState")
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

    val kafkaDStream: DStream[(String, String)] = stream.map(record => (record.key, record.value))

    val mapDStream: DStream[(String, Int)] = kafkaDStream.flatMap(x=>x._2.split(" ")).map((_,1))

    val stateDStream: DStream[(String, Int)] = mapDStream.updateStateByKey {
      case (seq, buffer) => {
        Option(buffer.getOrElse(0) + seq.sum)
      }
    }
    stateDStream.print()
//    kafkaDStream.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
