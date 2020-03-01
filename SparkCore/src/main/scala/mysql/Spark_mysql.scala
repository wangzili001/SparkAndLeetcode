package mysql

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.{SparkConf, SparkContext}

object Spark_mysql {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("Spark_mysql").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://hadoop:3306/rdd?useUnicode=true&characterEncoding=utf8"
    val userName = "root"
    val passWd = "123456"
    val sql = "select * from user where id>=? and id <=?"

    /*
        //读取数据
        val mysqlRDD = new JdbcRDD(
          sc,
          () => {
            //获取数据库的链接对象
            Class.forName(driver)
            DriverManager.getConnection(url, userName, passWd)
          },
          sql,
          1,
          3,
          2,
          (rs)=>{
            println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3)+"-"+rs.getString(4))
          }
        )
        mysqlRDD.collect()*/
    //保存数据
    val dataRDD: RDD[(Int, String, Int, String)] = sc.makeRDD(List((4, "李四", 10, "男"), (5, "王五", 10, "男")))
    //jdbc链接一次循环一个  建立mysql链接太多
    //    dataRDD.foreach{
    //      case (id, name, age, sex) => {
    //        Class.forName(driver)
    //        val connection: Connection = DriverManager.getConnection(url, userName, passWd)
    //        val sql = "insert into user(id, name, age, sex) values(?,?,?,?)"
    //        val statement: PreparedStatement = connection.prepareStatement(sql)
    //        statement.setInt(1,id)
    //        statement.setString(2,name)
    //        statement.setInt(3,age)
    //        statement.setString(4,sex)
    //        statement.executeUpdate()
    //        statement.close()
    //        connection.close()
    //      }
    //    }

    dataRDD.foreachPartition(datas => {
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(url, userName, passWd)
      datas.foreach {
        case (id, name, age, sex) => {
          val sql = "insert into user(id, name, age, sex) values(?,?,?,?)"
          val statement: PreparedStatement = connection.prepareStatement(sql)
          statement.setInt(1, id)
          statement.setString(2, name)
          statement.setInt(3, age)
          statement.setString(4, sex)
          statement.executeUpdate()
          statement.close()
        }
          connection.close()
      }
    }
    )
    sc.stop()
  }

}
