package Opera

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 1. 数据结构：时间戳，省份，城市，用户，广告，中间字段使用空格分割  见demo1  样本如下
 * 1516609143867 6 7 64 16
 * 1516609143869 9 4 75 18
 * 1516609143869 1 7 87 12
 * 2. 需求：统计出每一个省份广告被点击次数的TOP3
 */
object Demo1 {
  def main(args: Array[String]): Unit = {
    //1.初始化spark配置信息并建立与spark的连接
    val sparkConf = new SparkConf().setAppName("Demo1").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    //2.读取数据生成RDD：TS，Province，City，User，AD
    val list: RDD[String] = sc.textFile("demo1")
    //3.按照最小粒度聚合：((Province-AD),1)
    val tranList: RDD[(String, Int)] = list.map(line => {
      val strings = line.split(" ")
      (strings(2) + "-" + strings(4), 1)
    }
    )
    //4.计算每个省中每个广告被点击的总数：((Province,AD),sum)
    val everyProvinceList = tranList.reduceByKey(_+_)
    //5.将省份作为key，广告加点击数为value：(Province,(AD,sum))
    val tranTwoList: RDD[(String, (String, Int))] = everyProvinceList.map(x => {
      val strings = x._1.split("-")
      (strings(0), (strings(1), x._2))
    })
    //6.将同一个省份的所有广告进行聚合(Province,List((AD1,sum1),(AD2,sum2)...))
    val provinceGroup: RDD[(String, Iterable[(String, Int)])] = tranTwoList.groupByKey()
    //7.对同一个省份所有广告的集合进行排序并取前3条，排序规则为广告点击总数
    val provinceAdTop3  = provinceGroup.mapValues(x => {
      x.toList.sortWith((x, y) => x._2 > y._2).take(3)
    })
    //8.将数据拉取到Driver端并打印
    provinceAdTop3.collect().foreach(println)
    //9.关闭与spark的连接
    sc.stop()
  }
}
