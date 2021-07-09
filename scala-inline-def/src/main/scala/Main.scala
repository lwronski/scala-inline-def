
object Main extends App:
  val yamlString = s"""hr:  65""".stripMargin

  case class Yaml(hr: Int) derives Decoder

  val yaml = summon[Decoder[Yaml]].from(yamlString)
