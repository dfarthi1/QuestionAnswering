import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.io.*;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.Scanner;

import java.io.IOException;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


/* required jar files:
    slf4j.jar
    slf4j-simple-1.7.5.jar
    slf4j-simple-1.7.5-sources.jar
    stanford.postagger-3.6.0-javadoc.jar
    stanford-postagger.jar
    stanford-postagger-3.6.0.jar
    stanford-corenlp-3.6.0.jar
    stanford-corenlp-3.6.0-models.jar
*/
public class StanfordLemmatizer {

    protected StanfordCoreNLP pipeline;

    public StanfordLemmatizer() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        /*
         * This is a pipeline that takes in a string and returns various analyzed linguistic forms. 
         * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator), 
         * and then other sequence model style annotation can be used to add things like lemmas, 
         * POS tags, and named entities. These are returned as a list of CoreLabels. 
         * Other analysis components build and store parse trees, dependency graphs, etc. 
         * 
         * This class is designed to apply multiple Annotators to an Annotation. 
         * The idea is that you first build up the pipeline by adding Annotators, 
         * and then you take the objects you wish to annotate and pass them in and 
         * get in return a fully annotated object.
         * 
         *  StanfordCoreNLP loads a lot of models, so you probably
         *  only want to do this once per execution
         */
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> lemmatize(String documentText)
    {
        List<String> lemmas = new LinkedList<String>();
        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                lemmas.add(token.get(LemmaAnnotation.class));
            }
        }
        return lemmas;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        System.out.println("Starting Stanford Lemmatizer");
        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to lemmatize: ");
        String fileName = keyboard.nextLine();
        fileName = fileName.replace(".txt",".QuestionsRemoved.txt");
        String line = null;
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        
        String newFileName = fileName.replace(".QuestionsRemoved.txt", ".Lemmas.txt");
        //create a FileWriter using the new path and file
        FileWriter fileWriter = new FileWriter(newFileName);
        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        StanfordLemmatizer slem = new StanfordLemmatizer();
        line = bufferedReader.readLine();
        while(line != null) {
            System.out.println(slem.lemmatize(line));
            bufferedWriter.write(slem.lemmatize(line)+"\n");
            line = bufferedReader.readLine();
        }      
        /*
        String[] lemmasArray = (String[]) slem.lemmatize(line).toArray();

        // Initialize the tagger
        MaxentTagger tagger = new MaxentTagger(
        "G:/Natural Language Final Project/english-left3words-distsim.tagger");
        
        //output the tagged words
        for(String s: lemmasArray)
        {
            String tagged = tagger.tagString(s);
            System.out.println(tagged);
        }
        */
        // Always close files.
        bufferedReader.close(); 
        bufferedWriter.close();
        
        

        
    }

}
