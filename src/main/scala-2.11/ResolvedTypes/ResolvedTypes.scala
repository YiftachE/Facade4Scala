package ResolvedTypes


/**
  * Created by yiftach on 10/09/16.
  */

trait TypeData {
  def isPrimitive: Boolean

  def isArray: Boolean

  def isDictionary: Boolean
}

case class Basic(typeName: String) extends TypeData {
  override def isPrimitive: Boolean = true

  override def isArray: Boolean = false

  override def isDictionary: Boolean = false
}

case class Collection[E <: TypeData](innerTypeData: E) extends TypeData {
  override def isPrimitive: Boolean = false

  override def isArray: Boolean = true

  override def isDictionary: Boolean = false
}

case class Dictionary[K <: TypeData, V <: TypeData](keyTypeData: K, valueType: V) extends TypeData {

  override def isPrimitive: Boolean = false

  override def isArray: Boolean = false

  override def isDictionary: Boolean = true
}

object Dictionary {
  def apply(name: String): Dictionary[_, _] = {
    def parseKeyValue: (String, String) = {
      (TypeData.FindSliceIndex(name, "$key", name.indexOf("$value") - 1), TypeData.FindSliceIndex(name, "$value"))
    }
    val (key, value) = parseKeyValue
    Dictionary(TypeData(key), TypeData(value))
  }
}

object TypeData {

  def FindSliceIndex(name: String, slicer: String, endIndex: Int): String = {
    name.substring(name.indexOf(slicer) + slicer.length + 1, endIndex)
  }

  def FindSliceIndex(name: String, slicer: String): String = {
    name.substring(name.indexOf(slicer) + slicer.length + 1)
  }

  def apply(name: String): TypeData = {
    name match {
      case innerType if innerType.startsWith("Dictionary $of") =>
        Dictionary(FindSliceIndex(innerType, "$of"))
      case innerType if innerType.startsWith("Collection $of") =>
        Collection(TypeData(FindSliceIndex(innerType, "$of")))
      case _ => Basic(name)
    }
  }


}

