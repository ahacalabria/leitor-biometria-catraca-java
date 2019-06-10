package com.mkyong.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneTester {

    public static String indexDir = "/home/afonso/RESTfulExample/src/main/resources/forms.index";
    public static final String dataDir = "/home/afonso/RESTfulExample/src/main/resources/forms.preenchidos";
    public Indexer indexer;
    public Searcher searcher;

    public LuceneTester(String dataDir, String indexDir) {
        indexDir = indexDir;
        dataDir = dataDir;
    }

    public static void main(String[] args) throws ParseException {
        LuceneTester tester;
        try {
            tester = new LuceneTester(dataDir, indexDir);
            tester.createIndex();
            Scanner sc;
            int ok = 1;
            do {
                System.out.print("Pesquise: ");
                sc = new Scanner(System.in);
                String query = sc.nextLine();
                if (!query.equals("exit")) {
                    tester.search(query);
                } else {
                    ok = 0;
                }
            } while (ok == 1);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
//      } catch (ParseException e) {
//         e.printStackTrace();
        }
    }

    public void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed + " File indexed, time taken: "
                + (endTime - startTime) + " ms");
    }

    public void search(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits
                + " documents found. Time: " + (endTime - startTime) + " ms");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.print("Score: " + scoreDoc.score + " - ");
            System.out.println("File: "
                    + doc.get(LuceneConstants.FILE_NAME));
        }
        searcher.close();
    }

    public HashMap<Integer, HashMap> pesquisar(String query) throws IOException, ParseException {

        HashMap<Integer,HashMap> response = new HashMap<Integer,HashMap>();
        HashMap<String, String> temp = new HashMap<String, String>();

        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

           
      String [] fields= LuceneConstants.getMapa().keySet().toArray(new String[LuceneConstants.getMapa().size()]);
        
        String queryExpression = query;
        Directory directory = FSDirectory.open(new File(indexDir));
//        String[] fields= new_mapa.keySet().toArray(new String[new_mapa.size()]);
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_36, fields, new BrazilianAnalyzer(Version.LUCENE_36), LuceneConstants.getMapa());
//        QueryParser queryParser = new QueryParser(Version.LUCENE_36, LuceneConstants.CONTENTS, new BrazilianAnalyzer(Version.LUCENE_36));
        Query query2 = queryParser.parse(query);
        IndexSearcher searcher2 = new IndexSearcher(directory);
        TopDocs topDocs = searcher.search(query);

        for (int i = 0; i < topDocs.totalHits; i++) {
           ScoreDoc match = topDocs.scoreDocs[i];
            Explanation explanation = searcher.indexSearcher.explain(query2, match.doc);   
           System.out.println("----------");
           Document doc = searcher2.doc(match.doc);
           System.out.println(doc.get(LuceneConstants.FILE_NAME));
           System.out.println(explanation.toString());
        }
        String infoSearch = new String(topDocs.totalHits
                + " documentos encontrados. Tempo: " + (endTime - startTime) + " ms");
        temp.put("info", infoSearch);
        response.put(0, temp);
        int i=0;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            i++;
            temp = new HashMap<String, String>();
            Document doc = searcher.getDocument(scoreDoc);   
            temp.put("score", "" + scoreDoc.score);
            temp.put("name", doc.get(LuceneConstants.FILE_NAME));
            temp.put("path", doc.get(LuceneConstants.FILE_PATH));
            response.put(i,temp);
        }
        searcher.close();
        return response;
    }
}
