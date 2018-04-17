package com.gedcom.parser;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.gedcom.util.GedcomConstants;

/**
 * The class helps the user .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomParserTreeStrategyTest {
   
    @Test
    public void test_parse() {
		IGedcomParserStrategy gedcomParserTreeStrategy = GedcomParserStrategyFactory.getParserStrategy(GedcomConstants.PARSER_TYPE);

		gedcomParserTreeStrategy.parseDocument(GedcomConstants.DEFAULT_INPUT_FILE_VALID_TEST, GedcomConstants.DEFAULT_OUTPUT_FILE_TEST);
        File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE_TEST);
        Assert.assertTrue(file.exists());
     
    }
}
