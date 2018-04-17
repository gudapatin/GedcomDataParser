package com.gedcom;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gedcom.entity.NodeTest;
import com.gedcom.parser.GedcomParserTreeStrategyTest;
import com.gedcom.parser.GedcomTextInputProcessorTest;
import com.gedcom.entity.GedcomTreeNodeTest;
import com.gedcom.parser.GedcomXMLTransformerTest;
import com.gedcom.parser.MainGedcomDataParserAppTest;


/**
 * A junit test suite for all the classes.
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({
	GedcomTreeNodeTest.class,
	NodeTest.class, 
	MainGedcomDataParserAppTest.class,
	GedcomParserTreeStrategyTest.class,
	GedcomTextInputProcessorTest.class,
	MainGedcomDataParserAppTest.class,
    GedcomXMLTransformerTest.class })
public class GedcomParserTestSuite {
	
	public static void main(String[] args) {

        JUnitCore.runClasses(new Class[] { GedcomParserTestSuite.class });
    }
}

