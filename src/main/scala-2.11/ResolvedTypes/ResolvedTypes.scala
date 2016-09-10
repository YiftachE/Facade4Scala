package ResolvedTypes


/**
  * Created by yiftach on 10/09/16.
  */

sealed trait TypeData {
  def isPrimitive: Boolean

  def isComplex: Boolean

  def isArray: Boolean

  def isDictionary: Boolean

  def ToScalaType: String = {
    this match {
      case Primitive(name)=>name
      case Complex(name)=>name
      case Dictionary(val1,val2)=>s"Map[${val1.ToScalaType},${val2.ToScalaType}]"
      case Collection(val1)=>s"Array[${val1.ToScalaType}]"
    }
  }
}

final case class Primitive(typeName: String) extends TypeData {
  override def isPrimitive: Boolean = true

  override def isComplex: Boolean = true

  override def isArray: Boolean = false

  override def isDictionary: Boolean = false
}

final case class Complex(typeName: String) extends TypeData {
  override def isPrimitive: Boolean = false

  override def isComplex: Boolean = true

  override def isArray: Boolean = false

  override def isDictionary: Boolean = false
}

final case class Collection[E <: TypeData](innerTypeData: E) extends TypeData {
  override def isPrimitive: Boolean = false

  override def isArray: Boolean = true

  override def isDictionary: Boolean = false

  override def isComplex: Boolean = false
}

final case class Dictionary[K <: TypeData, V <: TypeData](keyTypeData: K, valueType: V) extends TypeData {

  override def isPrimitive: Boolean = false

  override def isArray: Boolean = false

  override def isDictionary: Boolean = true

  override def isComplex: Boolean = false
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

  val basicTypes = Map("Integer" -> "Int",
    "Long" -> "Long",
    "String" -> "String",
    "Boolean" -> "Boolean",
    "Character" -> "Char",
    "Byte" -> "Byte")

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
      case innerType if basicTypes.keySet.contains(name) => Primitive(basicTypes(name))
      case _ => Complex(name)
    }
  }


}

