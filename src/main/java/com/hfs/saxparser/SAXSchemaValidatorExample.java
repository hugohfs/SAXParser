package com.hfs.saxparser;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;

/**
 * This class is used to demonstrate validation of XML with provided XSD (Schema)
 * 
 * @author hflorez
 *
 */
public class SAXSchemaValidatorExample {

	public static void main(String[] a) {

		String schemaName = "src/main/resources/xsd/myCustomer.xsd";
		//String xmlName = "src/main/resources/xml/myCustomer.xml";
		String xmlName = "src/main/resources/xml/myCustomerInvalid.xml";
		Schema schema = loadSchema(schemaName);

		// call validate method to validate the xml with schema
		validateXml(schema, xmlName);
	}

	public static void validateXml(Schema schema, String xmlName) {
		try {
			// creating a Validator instance
			Validator validator = schema.newValidator();
			System.out.println();
			System.out.println("Validator Class: " + validator.getClass().getName());

			// preparing the XML file as a SAX source
			SAXSource source = new SAXSource(new InputSource(new java.io.FileInputStream(xmlName)));

			// validating the SAX source against the schema
			validator.validate(source);
			System.out.println("Validation passed.");

		} catch (Exception e) {
			// catching all validation exceptions
			System.out.println(e.toString());
		}
	}

	/**
	 * This method is used to load the schema name provide to the method.
	 * 
	 * @param name
	 * @return
	 */
	public static Schema loadSchema(String name) {
		Schema schema = null;
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(new File(name));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return schema;
	}

}
