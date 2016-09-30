
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/*required Jar files
stanford-post-tagger-3.6.0.jar
stanford-post-tagger-3.6.0-sources.jar
stanford-post-tagger-3.6.0-javadoc.jar
slf4j-api.jar
slf4j-simple.jar
lingpip-4.1.0.jar
*/


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dave
 */
public class StanfordTagger {
    
    public static void main(String[] args)throws IOException, ClassNotFoundException
    {             
        System.out.println("Starting Stanford Part of Speech Tagger");
        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to tag: ");
        String fileName = keyboard.nextLine();
        fileName = fileName.replace(".txt",".QuestionsRemoved.txt");
        
        String line = null;
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        
        String newFileName = fileName.replace(".QuestionsRemoved.txt", ".POSTagger.txt");
        //create a FileWriter using the new path and file
        FileWriter fileWriter = new FileWriter(newFileName);
        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        
        /* creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);*/
        
        
        // Initialize the tagger with a model
        MaxentTagger tagger = new MaxentTagger(
        "F:\\Natural Language Final Project\\taggers\\english-left3words-distsim.tagger");
        
        while((line = bufferedReader.readLine()) != null) {
            if(!line.isEmpty())
            {
                String newLine = line.substring(0,line.length()-1);
                System.out.println(tagger.tagString(newLine));
                bufferedWriter.write(tagger.tagString(newLine)+"\n");
            }
           
        }      
               
        // Always close files.
        bufferedReader.close(); 
        bufferedWriter.close();
        
    }
}
