/**
  * Created by yiftach on 10/09/16.
  */
case class ModelInformation(name: String, parameters: Array[Parameter])
{
  override def toString: String = s"ModelInformation(Name: $name,Parameters: ${parameters.mkString("[",",","]")})"
}
