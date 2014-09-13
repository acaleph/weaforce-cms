package com.weaforce.cms.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class MediaParser {
	/**
	 * Parse string stream content into HTML/XHTML/TEXT and meta data
	 * 
	 * @param inputStream
	 *            Input string stream
	 * @param htmlWriter
	 *            HTML writer
	 * @param xhtmlWriter
	 *            XHTML writer
	 * @param textWriter
	 *            text writer
	 * @param metaData
	 *            meta data map
	 * @throws TransformerConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public void parse(InputStream inputStream, StringWriter htmlWriter,
			StringWriter xhtmlWriter, StringWriter textWriter,
			Map<String, String> metaData)
			throws TransformerConfigurationException, IOException,
			SAXException, TikaException {
		// Tika meta data
		org.apache.tika.metadata.Metadata tikaData = new org.apache.tika.metadata.Metadata();
		ContentHandler handler = new TeeContentHandler(
				getHtmlContentHandler(htmlWriter),
				getTextContentHandler(textWriter),
				getXmlContentHandler(xhtmlWriter));
		new AutoDetectParser().parse(inputStream, handler, tikaData);
		// Save the TIKA meta data in application meta
		for (String key : tikaData.names()) {
			String value = tikaData.get(key);
			if (key.toLowerCase().equals("content-type")
					&& value.indexOf(";") > 0) {
				key = "Content-Type";
				value = value.substring(0, value.indexOf(";"));
			}
			metaData.put(key, value);
		}

	}

	/**
	 * Get HTML content handler
	 * 
	 * @param writer
	 * @return
	 * @throws TransformerConfigurationException
	 */
	private ContentHandler getHtmlContentHandler(Writer writer)
			throws TransformerConfigurationException {
		javax.xml.transform.sax.SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		TransformerHandler handler = factory.newTransformerHandler();
		handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "html");
		handler.setResult(new StreamResult(writer));
		return new ContentHandlerDecorator(handler) {
			@Override
			public void startElement(String uri, String localName, String name,
					Attributes atts) throws SAXException {
				if (XHTMLContentHandler.XHTML.equals(uri)) {
					uri = null;
				}
				if (!"head".equals(localName)) {
					super.startElement(uri, localName, name, atts);
				}
			}

			@Override
			public void endElement(String uri, String localName, String name)
					throws SAXException {
				if (XHTMLContentHandler.XHTML.equals(uri)) {
					uri = null;
				}
				if (!"head".equals(localName)) {
					super.endElement(uri, localName, name);
				}
			}

			@Override
			public void startPrefixMapping(String prefix, String uri) {
			}

			@Override
			public void endPrefixMapping(String prefix) {
			}
		};
	}

	/**
	 * Get text content handler
	 * 
	 * @param writer
	 * @return
	 */
	private ContentHandler getTextContentHandler(Writer writer) {
		return new BodyContentHandler(writer);
	}

	/**
	 * Get XML content handler
	 * 
	 * @param writer
	 * @return
	 * @throws TransformerConfigurationException
	 */
	private ContentHandler getXmlContentHandler(Writer writer)
			throws TransformerConfigurationException {
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		TransformerHandler handler = factory.newTransformerHandler();
		handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "xml");
		handler.setResult(new StreamResult(writer));
		return handler;
	}

}
