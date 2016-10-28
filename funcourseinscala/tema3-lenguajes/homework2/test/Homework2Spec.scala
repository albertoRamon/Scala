package org.hablapps.fpinscala
package lenguajes
package test

import org.scalatest._
import Homework2._
import Monads._

class Homework2Spec extends FlatSpec with Matchers {

  import TerminalState.TState, FSS._, IOState._

  val terminal = Terminal[TState]

  "Help" should "mostrar la ayuda de la terminal" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.help.exec(ts) shouldBe TerminalState(
      io = IOState(Nil, """ |List of commands available:
                        |- help: show this help message
                        |- cp <orig> <dest>: copy a file from <orig> to <dest>
                        |- mv <orig> <dest>: move a file from <orig> to <dest>
                        |- rm <file>: remove <file> from the filesystem
                        |- touch <file>: creates a file with empty content
                        |- touch2 <file> [contents]: creates a file with [contents]
                        |- cat <file>: shows the contents of <file>
                        |- echo [contents]: prints [contents] to the stdout
                        |- exit: close terminal
                        |""".stripMargin :: Nil),
      fs = FSS())
  }

  "Copy" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.cp(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: cp <orig> <dest>\n" :: Nil),
      fs = FSS())
  }

  it should "copiar un archivo en un destino" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "content1"))

    terminal.cp("file1" :: "file2" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "content1",
        "file2" -> "content1"))
  }

  "Move" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.mv(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: mv <orig> <dest>\n" :: Nil),
      fs = FSS())
  }

  it should "mover un archivo de un origen a un destino" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "content1"))

    terminal.mv("file1" :: "file2" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file2" -> "content1"))
  }

  "Remove" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.rm(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: rm <file-name>\n" :: Nil),
      fs = FSS())
  }

  it should "borrar el archivo" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "content1"))

    terminal.rm("file1" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())
  }

  "Touch" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.touch(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: touch <file-name>\n" :: Nil),
      fs = FSS())
  }

  it should "crear el archivo con contenido vacío" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.touch("file1" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> ""))
  }

  "Touch2" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.touch2(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: touch2 <file-name> [content]\n" :: Nil),
      fs = FSS())
  }

  it should "crear el archivo con contenido" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.touch2("file1" :: "contenido1" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "contenido1"))
  }

  "Cat" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.cat(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: cat <file-name>\n" :: Nil),
      fs = FSS())
  }

  it should "mostrar el contenido del archivo" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS(
        "file1" -> "contenido1"))

    terminal.cat("file1" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "contenido1" :: Nil),
      fs = FSS(
        "file1" -> "contenido1"))
  }

  "Echo" should "mostrar la ayuda si el número de parámetros es incorrecto" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.echo(Seq.empty).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Usage: echo [content]\n" :: Nil),
      fs = FSS())
  }

  it should "devolver la entrada" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.echo("esta es la entrada" :: Nil).exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "esta es la entrada\n" :: Nil),
      fs = FSS())
  }

  "Exit" should "salir de la terminal" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.exit.exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Good bye!\n" :: Nil),
      fs = FSS())
  }

  "Other" should "mostrar un mensaje de comando no reconocido" in {
    val ts = TerminalState(
      io = IOState(Nil, Nil),
      fs = FSS())

    terminal.other("?").exec(ts) shouldBe TerminalState(
      io = IOState(Nil, "Command not found: '?'\n" :: Nil),
      fs = FSS())
  }

  "Process commands" should "ejecutar los comandos leídos" in {
    val ts = TerminalState(
      io = IOState("echo hi, friend" :: "exit" :: Nil, Nil),
      fs = FSS())

    terminal.processCommands.exec(ts) shouldBe TerminalState(
      io = IOState(Nil, List(
        "Good bye!\n",
        "$ ", 
        "hi, friend\n",
        "$ ")),
      fs = FSS())
  }

}
