import ResolvedTypes.TypeData

/**
  * Created by yiftach on 10/09/16.
  */
object ModelParser {
  def ParseModelInformation(model: JsonSchema.Model): ModelInformation = {
    ModelInformation(model.name,
      model.properties map (prop => Parameter(prop.name, TypeData(prop.parameterType))))
  }
}
