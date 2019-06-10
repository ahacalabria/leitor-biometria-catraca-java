package com.mkyong.rest;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Indexer {

	private IndexWriter writer;
	

//	@SuppressWarnings("deprecation")
	public Indexer(String indexDirectoryPath) throws IOException {
		// this directory will contain the indexes
		Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath));

		// create the indexer
		writer = new IndexWriter(indexDirectory, new BrazilianAnalyzer(Version.LUCENE_36), true,
				IndexWriter.MaxFieldLength.UNLIMITED);
	}

	public void close() throws CorruptIndexException, IOException {
		writer.close();
	}

	private Document getDocument(File file) throws IOException {
		Document document = new Document();
		LuceneConstants.loadingFileWithPaths();

		HashMap<String, Float> map = LuceneConstants.getMapa();
		ArrayList<String> temp = new ArrayList<String>();

		for (String pathfield : map.keySet()) {
			ArrayList<String> aux = search(file.getParent(), file.getName(), pathfield);
			if (!aux.isEmpty()) {
				temp.addAll(aux);
				temp = new ArrayList<String>();

				// index new file fields
				Field newField = new Field(pathfield, aux.toString(), Field.Store.YES, Field.Index.ANALYZED);
                                newField.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
				newField.setBoost(Float.valueOf(map.get(pathfield).toString()));
				document.add(newField);
			}
		}
                ArrayList<String> aux = search(file.getParent(), file.getName(), LuceneConstants.VALUES);
                //index file contents
                Field contentField = new Field("content", aux.toString(), Field.Store.NO, Field.Index.ANALYZED);
		// index file name
		Field fileNameField = new Field(LuceneConstants.FILE_NAME, file.getName(), Field.Store.YES,
				Field.Index.NOT_ANALYZED);
		// index file path
		Field filePathField = new Field(LuceneConstants.FILE_PATH, file.getCanonicalPath(), Field.Store.YES,
				Field.Index.NOT_ANALYZED);


                document.add(contentField);
		document.add(fileNameField);
		document.add(filePathField);

		return document;
	}

	private void indexFile(File file) throws IOException {
		System.out.println("Indexing " + file.getCanonicalPath());
		Document document = getDocument(file);
		writer.addDocument(document);
	}

	public int createIndex(String dataDirPath, FileFilter filter) throws IOException {
		// get all files in the data directory
		File[] files = new File(dataDirPath).listFiles();
		for (File file : files) {
			if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && filter.accept(file)) {
				indexFile(file);
			}
		}
		return writer.numDocs();
	}
	
	public ArrayList<String> search(String filedir, String filename, String targetpath) {
		ArrayList<String> temp = new ArrayList<String>();
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			String aux2 = filedir + "/" + filename;
			org.w3c.dom.Document document = builder.parse(new FileInputStream(aux2));
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = targetpath;
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
//                        System.out.println("Expression:"+expression);
			for (int i = 0; i < nodeList.getLength(); i++) {
				String aux = nodeList.item(i).getFirstChild().getNodeValue();
//                                System.out.println(aux);
				if (!aux.isEmpty())
					temp.add(aux);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (XPathExpressionException e3) {
			e3.printStackTrace();
		}
		return temp;

	}
}
