import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_MyReceiver {
  def main(args: Array[String]): Unit = {
    import org.apache.spark
    //使用sparkstreaming来完成wordcount

    //spark的配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming_MyReceiver")
    val streamingContext: StreamingContext = new StreamingContext(sparkConf, Seconds(3))
    //实时数据分析环境对象
    //采集周期 以指定的时间为周期
    val lineStream: ReceiverInputDStream[String] = streamingContext.receiverStream(new MyReceiver("hadoop",9999))
    //将采集的数据进行分解（扁平化）
    val wordDStream: DStream[String] = lineStream.flatMap(_.split(" "))
    //将扁平化的数据进行结构的转变
    val mapDStream: DStream[(String, Int)] = wordDStream.map((_, 1))
    val sumWordDStream: DStream[(String, Int)] = mapDStream.reduceByKey(_ + _)
    sumWordDStream.print()
    //启动采集器
    streamingContext.start()
    //driver 等待采集器的执行
    streamingContext.awaitTermination()
    //不能停止采集程序
    //    streamingContext.stop()

  }
}

//声明采集器
//继承Receiver
class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {

  var socket: Socket = null

  def receiver(): Unit = {
    socket = new Socket(host, port)
    val bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream, "UTF-8"))
    var line: String = null
    while ((line = bufferedReader.readLine()) != null) {
      if("END".equals(line)){
        return
      }else{
        //将采集的数据采集到内部进行转换
        this.store(line)
      }
    }
  }

  override def onStart(): Unit = {
    new Thread(
      new Runnable {
        override def run(): Unit = {
          receiver()
        }
      }
    ).start()
  }

  override def onStop(): Unit = {
    if(socket != null){
      socket.close()
      socket = null
    }
  }
}
