package com.gedcom.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.gedcom.entity.Node;
import com.gedcom.exception.GedcomDataParserException;
import com.gedcom.util.GedcomConstants;
import com.gedcom.util.GedcomXmlUtility;

 
/**
 * The class produces XML for the tree data.
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomXMLTransformer implements IGedcomTransformer {

	private static final Logger logger = Logger.getLogger(GedcomXMLTransformer.class.getSimpleName());

	/* (non-Javadoc)
	 * @see com.gedcom.io.GedcomTransformer#convert(java.lang.String, com.gedcom.entity.Node)
	 */
	@Override
	public void convert(String outputFilePath, Node rootNode) throws GedcomDataParserException {

		logger.log(Level.INFO, "begin of the xml transformer class.");
		XMLStreamWriter xmlWriter = null;

		try {
			File outputFile = GedcomXmlUtility.createFile(outputFilePath);
			xmlWriter = XMLOutputFactory.newInstance()
					.createXMLStreamWriter(new BufferedOutputStream(new FileOutputStream(outputFile)), "UTF-8");
			xmlWriter.writeStartDocument();

			GedcomXmlUtility.convertToXML(xmlWriter, rootNode);

			xmlWriter.writeEndDocument();
			xmlWriter.flush();

			logger.log(Level.INFO, "XML conversion process completed.");
		} catch (IOException e) {
			throw new GedcomDataParserException(GedcomConstants.OUTPUT_FILE_CONVERSION_ERROR);
		} catch (XMLStreamException e) {
			throw new GedcomDataParserException(GedcomConstants.INPUT_FILE_CONVERSION_ERROR);
		} finally {
			try {
				if (xmlWriter != null) {
					xmlWriter.close();
				}
			} catch (XMLStreamException e) {
				throw new GedcomDataParserException(GedcomConstants.INPUT_FILE_CONVERSION_ERROR);
			}
		}
	}

}
