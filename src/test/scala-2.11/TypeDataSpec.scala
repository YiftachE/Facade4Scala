import org.scalatest._
import Matchers._
import ResolvedTypes.ResolvedTypes.{Basic, Collection, Dictionary, TypeData}
/**
  * Created by yiftach on 10/09/16.
  */
class TypeDataSpec extends FlatSpec {
  info("Starting..")

  "Dictionary $of $key-Integer $value-Details" should "parse into Dictionary $of $key Basic $value Basic" in {
    val typeData = TypeData("Dictionary $of $key-Integer $value-Details")
    typeData should equal(Dictionary(Basic("Integer"), Basic("Details")))
  }

  "Dictionary $of $key-Collection $of Integer $value-Collection $of Details" should "parse into Dictionary $of $key Collection $of Integer $value Collection $of Integer" in {
    val typeData = TypeData("Dictionary $of $key-Collection $of Integer $value-Collection $of Details")
    typeData should equal(Dictionary(Collection(Basic("Integer")), Collection(Basic("Details"))))
  }

  "Integer" should "parse into Basic(Integer)" in {
    val typeData = TypeData("Integer")
    typeData should equal(Basic("Integer"))
  }

  "Collection $of Integer" should "parse into Collection(Basic(Integer))" in {
    val typeData = TypeData("Collection $of Integer")
    typeData should equal(Collection(Basic("Integer")))
  }

  "Collection $of Collection $of Integer" should "parse into Collection(Collection(Basic(Integer)))" in {
    val typeData = TypeData("Collection $of Collection $of Integer")
    typeData should equal(Collection(Collection(Basic("Integer"))))
  }
}
