package com.gedcom.parser;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.internal.WhiteboxImpl;

import com.gedcom.entity.Node;
import com.gedcom.parser.GedcomTreeNode;
import com.gedcom.exception.GedcomDataParserException;
import com.gedcom.io.GedcomTextInputProcessor;
import com.gedcom.util.GedcomConstants;

/**
 * Class to test the textinputprocessor
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GedcomTreeNode.class)
public class GedcomTextInputProcessorTest {
     GedcomTreeNode mockTeeNode = Mockito.mock(GedcomTreeNode.class);
    private GedcomTextInputProcessor input = new GedcomTextInputProcessor();

    @Test
    public void test_GedcomInputFileNotAvailable() throws Exception {
        Throwable exception = null;
        try {
        	input.parseInput("Gedcom_TestSampleMissing.txt");
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.INPUT_FILE_READING_ERROR);
    }

    @Test
    public void test_GedcomValidInputFile() throws Exception {
        String filePath = GedcomConstants.DEFAULT_INPUT_FILE_VALID_TEST;
        PowerMockito.mockStatic(GedcomTreeNode.class);
        GedcomTreeNode mockTeeNode = Mockito.mock(GedcomTreeNode.class);
        Mockito.when(GedcomTreeNode.getTreeNode()).thenReturn(mockTeeNode);

        Throwable exception = null;
        try {
            input.parseInput(filePath);
        } catch (Throwable ex) {
            exception = ex;
        }
        // Verification starts
        long totalNumOfEntriesExpected = 0;
        try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
            totalNumOfEntriesExpected = lines.count();
        }
        Assert.assertFalse(exception instanceof GedcomDataParserException);
        Mockito.verify(mockTeeNode, Mockito.times((int) totalNumOfEntriesExpected))
                .extractNode(Mockito.any(Node.class));
    }

      @Test
    public void test_GedcomValidIdElement() throws Exception {
        Node node = WhiteboxImpl.invokeMethod(input, "getInputNode", new String("0 @I0214@ INDI"));

        Assert.assertEquals(node.getId(), "@I0214@");
        Assert.assertEquals(node.getLevel(), 0);
        Assert.assertEquals(node.getTag(), "INDI");
    }

    @Test
    public void test_GedcomValiTagElement() throws Exception {
        Node node = WhiteboxImpl.invokeMethod(input, "getInputNode", new String("1 NAME DEAT Charles /Kwit/"));

        Assert.assertEquals(node.getId(), null);
        Assert.assertEquals(node.getLevel(), 1);
        Assert.assertEquals(node.getTag(), "NAME");
        Assert.assertEquals(node.getData(), "DEAT Charles /Kwit/");
    }

    @Test
    public void test_GedcomTrimTagElement() throws Exception {
        Node node = WhiteboxImpl.invokeMethod(input, "getInputNode", new String("2 DEAT CHARLES"));

        Assert.assertEquals(node.getId(), null);
        Assert.assertEquals(node.getLevel(), 2);
        Assert.assertEquals(node.getTag(), "DEAT");
        Assert.assertEquals(node.getData(), "CHARLES");
    }

    @Test
    public void test_GedcomMissingTagAndId() throws Exception {
        Throwable exception = null;
        try {
            WhiteboxImpl.invokeMethod(input, "getInputNode", new String("0 MISSING_TAG_ID"));
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.TAG_OR_ID_NOT_PRESENT);
    }
    @Test
    public void test_GedcomInvalidInputFile() throws Exception {
        String filePath = GedcomConstants.DEFAULT_INPUT_FILE_INVALID_TEST;
        PowerMockito.mockStatic(GedcomTreeNode.class);
        GedcomTreeNode mockTeeNode = Mockito.mock(GedcomTreeNode.class);
        Mockito.when(GedcomTreeNode.getTreeNode()).thenReturn(mockTeeNode);

        Throwable exception = null;
        try {
            input.parseInput(filePath);
        } catch (Throwable ex) {
            exception = ex;
        }

        // Verification starts
        long totalNumOfEntriesExpected = 0;
        try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
            totalNumOfEntriesExpected = lines.filter(input -> input != null && !input.isEmpty()).count();
        }
        Assert.assertFalse(exception instanceof GedcomDataParserException);
        Mockito.verify(mockTeeNode, Mockito.times((int) totalNumOfEntriesExpected))
        .extractNode(Mockito.any(Node.class));
    }
    @Test
    public void test_GedcomMissingLevel() throws Exception {
        Throwable exception = null;
        try {
            WhiteboxImpl.invokeMethod(input, "getInputNode", new String("NAME Jamis Gordon /Buck/"));
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.LEVEL_INFO_NOT_FOUND);
    }

    @Test
    public void test_TagCaseConversion() throws Exception {
        Throwable exception = null;
        try {
            WhiteboxImpl.invokeMethod(input, "getInputNode", new String("1 name Jamis Gordon /Buck/"));
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.TAG_OR_ID_NOT_PRESENT);
    }

    /**
     * Test to very regular expression matching of tag entry. If @ symbol is not
     * present at the end, it wont be treated as tag
     * 
     * @throws Exception
     */
    @Test
    public void test_InvalidTag() throws Exception {
            Throwable exception = null;
        try {
            WhiteboxImpl.invokeMethod(input, "getInputNode", new String("1 @123 Tag without @ at en"));
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.TAG_OR_ID_NOT_PRESENT);
    }

    /**
     * Test to very regular expression matching of tag entry.
     * 
     * @throws Exception
     */
    @Test
    public void test_IDInvalidTagMissingSymbol() throws Exception {
        Throwable exception = null;
        try {
            WhiteboxImpl.invokeMethod(input, "getInputNode", new String("7 4543"));
        } catch (Throwable ex) {
            exception = ex;
        }
        Assert.assertTrue(exception instanceof GedcomDataParserException);
        Assert.assertEquals(((GedcomDataParserException) exception).getMessage(),
                GedcomConstants.TAG_OR_ID_NOT_PRESENT);
    }

}
