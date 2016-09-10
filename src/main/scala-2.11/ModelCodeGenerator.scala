import treehugger.forest._
import treehuggerDSL._
import definitions._

/**
  * Created by yiftach on 10/09/16.
  */
object ModelCodeGenerator {
  def ToCodeTree(rawPackageName: String, models: Array[ModelInformation]): PackageDef = {

    val splitPackage = rawPackageName.split('.')
    val packageName = splitPackage take splitPackage.length - 1 mkString "."

    def MakeModel(model: ModelInformation): ClassDef = {
      val classSymbol = RootClass.newClass(model.name)
      val params = model.parameters.map(p => PARAM(p.paramName, p.typeData.ToScalaType): ValDef)
      CASECLASSDEF(classSymbol).withParams(params)
    }
    (OBJECTDEF(splitPackage.last) := BLOCK(
      models.map(MakeModel)
    )).inPackage(packageName)
  }
}