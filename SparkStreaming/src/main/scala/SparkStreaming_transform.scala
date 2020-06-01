import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_transform {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SparkStreaming_kafka").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop1:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("spark")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    //    stream.map(_.value()).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).print()
    //转换
    val value: DStream[String] = stream.map(_.value())

    //todo driver
    value.map {
      case x => {
        // todo executor
        x
      }
    }
    //todo driver
    value.transform {
      case rdd => {
        //todo driver
        rdd.map {
          case x => {
            // todo executor
            x
          }
        }
      }
    }
    ssc.start()
    ssc.awaitTermination()


  }
}
