import sbt._

class KnockoffProject( info : ProjectInfo )
extends DefaultProject( info )
with    knockoff.Literable {
  
  override def crossScalaVersions = Set("2.7.2", "2.7.3", "2.7.4", "2.7.5", "2.7.6")
  
  override def compileOptions = {
    List( MaxCompileErrors( 5 ), CompileOption("-unchecked") ) :::
    super.compileOptions.toList
  }
  
  lazy val literateCompile = task { None } dependsOn( literate, compile )
  
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)

  val nexus = "tristanhunt" at "http://tristanhunt.com:8081/content/groups/public/"

  val ScalaTest = "org.scala-tools.testing" % "scalatest" % "0.9.5"
  
  override def managedStyle = ManagedStyle.Maven
  val publishTo = "tristanhunt releases" at
    "http://tristanhunt.com:8081/content/repositories/releases/"
}