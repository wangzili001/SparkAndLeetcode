import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL_Transfrom1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_Transfrom1")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    //创建RDD
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1,"刘备",20),(2,"关羽",21),(3,"张飞",22)))
    //转换为DF
    //就行转换之前 需要引入隐式转换规则  这里的spark不是包名的含义 是spark包名的含义
    import spark.implicits._
    val sanguoRDD: RDD[Sanguo] = rdd.map {
      case (id, name, age) => {
        Sanguo(id, name, age)
      }
    }
    val frame: DataFrame = sanguoRDD.toDF()
    val ds: Dataset[Sanguo] = sanguoRDD.toDS()
    val rdd1: RDD[Sanguo] = ds.rdd
    sanguoRDD.foreach(println)
    rdd1.foreach(println)
    //释放资源
    spark.stop()
  }
}

