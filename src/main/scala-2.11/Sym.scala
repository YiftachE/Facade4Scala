/**
  * Created by yiftach on 09/09/16.
  */

import treehugger.forest._, definitions._, treehuggerDSL._
import treehugger.forest._
import definitions._
import treehuggerDSL._

object Sym {
  val IntQueue = RootClass.newClass("IntQueue")
  val T_Type=RootClass.newAliasType("T")
  val B_Type = RootClass.newAliasType("B")
  val TreeHugger = RootClass.newClass("TreeHugger") TYPE_OF List(T_Type,B_Type)
}