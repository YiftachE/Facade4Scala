package Schema

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration

/**
  * Created by yiftach on 09/09/16.
  */
case class ApiDescription(actions: Array[Action])

case class Action(actionUrl: String,
                  returnType:NameDescriptor,
                  parameters:Array[Parameter],
                  @JsonScalaEnumeration(classOf[HttpRequestType])requestType:HttpRequest.HttpRequestType)

object HttpRequest extends Enumeration {
  type HttpRequestType = Value
  val GET, POST, DELETE, HEAD, OPTIONS= Value
}
class HttpRequestType extends TypeReference[HttpRequest.type]