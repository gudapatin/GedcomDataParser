package com.gedcom.io;

import java.util.HashMap;
import java.util.Map;

import com.gedcom.util.GedcomConstants;

/**
 * The factory class to process the input.
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomInputProcessorFactory {

	private static Map <String,IGedcomInputProcessor> inputprocessors = new HashMap<>();
	static {
		inputprocessors.put(GedcomConstants.TEXT_PROCESSOR,
	    new GedcomTextInputProcessor());
	}

	/**
	 * @param outputFormat
	 * @return GedcomInputProcessor
	 */
	public static IGedcomInputProcessor getInputProcessor(String outputFormat) {
	return inputprocessors.get(outputFormat);
	}
}
