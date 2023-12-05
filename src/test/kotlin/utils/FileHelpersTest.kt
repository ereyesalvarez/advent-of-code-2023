package utils

import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.test.Test
import kotlin.test.assertNotNull

class FileHelpersTest {
    @Test
    fun readSampleFile(){
        val fileContent = getFileAsText("data/util_text_edit_sample.txt")
        assertNotNull(fileContent)
        assert(fileContent.lines().size == 10)
    }
    @Test
    fun readSampleFileWithSpaces(){
        val fileContent = getFileAsText("data/util_text_edit_sample_with_spaces.txt")
        assertNotNull(fileContent)
        assert(fileContent.lines().size == 12)
    }

    @Test
    fun testReges(){
        val line = "Card 1 Value A, ValueB, Value C"
        val pattern = "Card (\\d) ((Value .)(\\s|))*"
        val r: Pattern = Pattern.compile(pattern)
        val m: Matcher = r.matcher(line)
        if (m.find()){
            val id = m.group(1)
            println()
        }
    }
}