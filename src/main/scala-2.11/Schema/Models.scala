package Schema

/**
  * Created by yiftach on 09/09/16.
  */
case class Models(models: Array[Model])

case class Model(fullName: NameDescriptor, properties: Array[Parameter])

case class NameDescriptor(packageName: String, name: String)

case class Parameter(name: String, parameterType: String)
