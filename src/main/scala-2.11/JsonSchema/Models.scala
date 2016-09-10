package JsonSchema

/**
  * Created by yiftach on 09/09/16.
  */
case class Models(models: Array[Model],packageName:String)

case class Model(name: String, properties: Array[ParameterSchema])

case class ParameterSchema(name: String, parameterType: String)
