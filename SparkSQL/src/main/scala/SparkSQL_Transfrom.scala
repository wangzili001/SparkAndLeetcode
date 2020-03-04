import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object SparkSQL_Transfrom {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL_Transfrom")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    //创建RDD
    val rdd: RDD[(Int, String, Int)] = spark.sparkContext.makeRDD(List((1,"刘备",20),(2,"关羽",21),(3,"张飞",22)))
    //转换为DF
    //就行转换之前 需要引入隐式转换规则  这里的spark不是包名的含义 是spark包名的含义
    import spark.implicits._
    val df: DataFrame = rdd.toDF("id","name","age")
    //转换为DS
    val ds: Dataset[Sanguo] = df.as[Sanguo]
    //转换为DF
    val frame = ds.toDF()
    //转换为RDD
    val rdd1: RDD[Row] = frame.rdd
    //rdd获取数据时可以通过索引访问数据
    rdd1.foreach(row => {println(row.getString(1))})
    //释放资源
    spark.stop()
  }
}

case class Sanguo( id:Int,name:String,age:Int)
