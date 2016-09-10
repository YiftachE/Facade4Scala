import java.io.{File, FileInputStream, PrintWriter}

import ResolvedTypes.TypeData
import JsonSchema.{ApiDescription, Models}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.io.Source
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}
import scalaj.http.Http
import treehugger.forest._
import treehuggerDSL._
import definitions._

/**
  * Created by yiftach on 09/09/16.
  */
object App {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def main(args: Array[String]): Unit = {

    val t = readJson[Models](Http("http://localhost:8080/Example.json").asString.body)
    val models = t.models.map(ModelParser.ParseModelInformation)
    val d = ModelCodeGenerator.ToCodeTree(t.packageName, models)
    val f = Some(new PrintWriter(new File("src/main/scala-2.11/Models.scala"))).foreach { p => p.write(treeToString(d)); p.close }

    // pretty print the tree

  }

  def readJson[T: ClassTag](json: String) = {
    val classTag = implicitly[ClassTag[T]]
    Try(mapper.readValue(json, classTag.runtimeClass).asInstanceOf[T]) match {
      case Success(v) => v
      case Failure(throwable) => throw throwable
    }

  }
}
