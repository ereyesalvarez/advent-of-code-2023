package utils
import java.io.File

fun getResourceAsText(path: String): String =
    object {}.javaClass.getResource(path)?.readText() ?: throw Exception("error at read $path")

fun getFileAsText(path: String): String =
    File(path).readText()