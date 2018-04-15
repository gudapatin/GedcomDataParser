package com.gedcom.exception;

/**
 * A user defined exception class to handle gedcom parser errors .
 * @author NGUDAPAT
 * @version 1.0
 * 
 */
public class GedcomDataParserException extends Exception {

    private static final long serialVersionUID = 1L;

        public GedcomDataParserException()
        {
            super();
        }

        /**
         * @param message
         */
        public GedcomDataParserException(String message)
        {
            super(message);
        }

        /**
         * @param object
         */
        public GedcomDataParserException(Throwable object)
        {
            super(object);
        }

        /**
         * @param message
         * @param object
         */
        public GedcomDataParserException(String message, Throwable object)
        {
            super(message, object);
        }
}
