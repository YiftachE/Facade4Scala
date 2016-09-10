import java.io.FileInputStream

import ResolvedTypes.ResolvedTypes.TypeData
import Schema.{ApiDescription, Models}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.io.Source
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}
import scalaj.http.Http
/**
  * Created by yiftach on 09/09/16.
  */
object App {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def main(args: Array[String]): Unit = {

    val t = readJson[Models](Http("http://localhost:8080/Example.json").asString.body)
    val d = TypeData(t.models.head.properties.head.parameterType)


  }

  def readJson[T:ClassTag](json:String)={
    val classTag = implicitly[ClassTag[T]]
    Try(mapper.readValue(json,classTag.runtimeClass).asInstanceOf[T]) match {
      case Success(v)=> v
      case Failure(throwable)=>throw throwable
    }

  }
}
