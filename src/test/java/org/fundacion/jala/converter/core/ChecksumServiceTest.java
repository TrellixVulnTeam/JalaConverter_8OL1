package org.fundacion.jala.converter.core;

import org.fundacion.jala.converter.core.exceptions.ChecksumException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import static org.fundacion.jala.converter.core.ChecksumService.getFileChecksum;
import static org.fundacion.jala.converter.core.ChecksumService.repeatedChecksum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChecksumServiceTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    @Test
    public void shouldReturnFileChecksum() throws IOException, ChecksumException {
        File tempFile = folder.newFile("myFile.txt");
        String actual = getFileChecksum(tempFile.getPath());
        String expected = "d41d8cd98f00b204e9800998ecf8427e";
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowChecksumExceptionWithNullInput() throws ChecksumException {
        String actual = getFileChecksum(null);
    }

    @Test(expected = ChecksumException.class)
    public void shouldThrowChecksumExceptionWithEmptyFilePath() throws ChecksumException {
        String actual = getFileChecksum("");
    }

    @Test(expected = ChecksumException.class)
    public void shouldThrowChecksumExceptionWithInvalidFilePath() throws ChecksumException {
        String actual = getFileChecksum("invalid file path");
    }

    @Test
    public void shouldReturnTrueWithCorrectChecksum() throws IOException, ChecksumException {
        File tempFile = folder.newFile("myFile.txt");
        Boolean actual = repeatedChecksum(tempFile.getPath(), "d41d8cd98f00b204e9800998ecf8427e");
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWithIncorrectChecksum() throws IOException, ChecksumException {
        File tempFile = folder.newFile("myFile.txt");
        Boolean actual = repeatedChecksum(tempFile.getPath(), "fail");
        assertFalse(actual);
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnChecksumExceptionWithNullFilepath() throws ChecksumException {
        Boolean actual = repeatedChecksum(null, "d41d8cd98f00b204e9800998ecf8427e");
    }

    @Test(expected = ChecksumException.class)
    public void shouldReturnChecksumExceptionWithEmptyFilepath() throws ChecksumException {
        Boolean actual = repeatedChecksum("", "d41d8cd98f00b204e9800998ecf8427e");
    }

    @Test(expected = ChecksumException.class)
    public void shouldReturnChecksumExceptionWithInvalidFilepath() throws ChecksumException {
        Boolean actual = repeatedChecksum("invalid file path", "d41d8cd98f00b204e9800998ecf8427e");
    }
}
