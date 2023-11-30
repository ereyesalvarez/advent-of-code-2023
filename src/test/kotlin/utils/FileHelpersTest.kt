package utils

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
}