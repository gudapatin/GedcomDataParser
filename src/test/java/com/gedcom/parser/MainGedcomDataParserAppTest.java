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
public class MainGedcomDataParserAppTest {

	@Test
	public void testClientWithInputOutputArguments() {
		String[] args = new String[]{GedcomConstants.DEFAULT_INPUT_FILE,GedcomConstants.DEFAULT_OUTPUT_FILE};
		MainGedcomDataParserApp.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}
	
	@Test
	public void testClientWithOutputArguments() {
		String[] args = new String[]{};
		MainGedcomDataParserApp.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}
	
	@Test
	public void testClientWithOnlyInputArguments() {
		String[] args = new String[]{GedcomConstants.DEFAULT_INPUT_FILE};
		MainGedcomDataParserApp.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}

}
