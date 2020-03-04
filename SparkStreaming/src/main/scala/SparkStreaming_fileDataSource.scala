import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_fileDataSource {
  def main(args: Array[String]): Unit = {
    //使用sparkstreaming来完成wordcount

    //spark的配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_fileDataSource")
    val streamingContext: StreamingContext = new StreamingContext(sparkConf,Seconds(3))

    //实时数据分析环境对象
    //采集周期 以指定的时间为周期
     val fileDStream: DStream[String] = streamingContext.textFileStream("hadoop")
    //将采集的数据进行分解（扁平化）
      val wordDStream: DStream[String] = fileDStream.flatMap(_.split(" "))
    //将扁平化的数据进行结构的转变
    val mapDStream: DStream[(String, Int)] = wordDStream.map((_,1))
    val sumWordDStream: DStream[(String, Int)] = mapDStream.reduceByKey(_+_)
    sumWordDStream.print()
    //启动采集器
    streamingContext.start()
    //driver 等待采集器的执行
    streamingContext.awaitTermination()
    //不能停止采集程序
//    streamingContext.stop()

  }
}
