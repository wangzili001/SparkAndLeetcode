package Action

import org.apache.spark.{SparkConf, SparkContext}

/**
 * saveAsTextFile(path)
 * 作用：将数据集的元素以textfile的形式保存到HDFS文件系统或者其他支持的文件系统，对于每个元素，Spark将会调用toString方法，将它装换为文件中的文本
 * saveAsSequenceFile(path)
 * 作用：将数据集中的元素以Hadoop sequencefile的格式保存到指定的目录下，可以使HDFS或者其他Hadoop支持的文件系统。
 * saveAsObjectFile(path)
 * 作用：用于将RDD中的元素序列化成对象，存储到文件中
 */
object Spark_save {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_save").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

  }
}
