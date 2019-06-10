package com.mkyong.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class LuceneConstants {
	public static final String CONTENTS="content";
	public static final String VALUES = "/XFDL/page/label/value | /XFDL/page/field/label | /XFDL/page/field/help | /XFDL/page/popup/label | /XFDL/page/field/value | /XFDL/page/button/value | /XFDL/page/cell[@sid=../popup/value]/value | /XFDL/page/cell[not(@sid=../popup/value)]/value | /XFDL/page/check[value='on']/label | /XFDL/page/check[value='off']/label | /XFDL/page/radio[value='on']/label | /XFDL/page/radio[value='off']/label";
	public static final String FILE_NAME = "filename";
	public static final String FILE_PATH = "filepath";
	public static final int MAX_SEARCH = 100;
	
	private static Path file;
	private static HashMap<String, Float> mapa = null; 
	
        //carrega o arquivo com os caminhos e os respectivos pesos em um MAPA CHAVE-VALOR
	public static boolean loadingFileWithPaths() {
		Charset charset = Charset.forName("ISO-8859-1");
		file = FileSystems.getDefault().getPath("/home/afonso/RESTfulExample/src/main/resources/file.txt");
//                file = getClass().getResourceAsStream("/file");
		try { 
                    BufferedReader reader = Files.newBufferedReader(file, charset);
		    String line = null;
		    while ((line = reader.readLine()) != null) {
                        String temp[] = line.split("#");
		        getMapa().put(temp[0], Float.valueOf(temp[1]));
		    }
		    return true;
		} catch (IOException x) {
		    System.err.format("FAILED!\nIOException: %s%n", x);
		    return false;
		}		
	}

	public static HashMap<String, Float> getMapa() {
		if(mapa==null) {
			mapa = new HashMap<String,Float>();
		}
		return mapa;
	}
}