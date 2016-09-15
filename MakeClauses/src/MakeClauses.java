
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;




/**
 *
 * @author Dave
 */
public class MakeClauses {
    
    
    public static void main(String[] args)throws IOException, ClassNotFoundException
    {             

        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to make clauses from: ");
        String fileName = keyboard.nextLine();
        
        String fileWriterName = fileName.replace(".txt", ".Clauses.txt");
        String fileReaderName = fileName.replace(".txt", "");
        
        String line = null;
        // FileReader for the parts of speech
        FileReader POSFileReader = 
            new FileReader(fileReaderName+".POSTagger.txt");
        // FileReader for the lemmas
        FileReader lemmasFileReader =
            new FileReader(fileReaderName+".LemmasCleaned.txt");
        // FileReader for the sets of synonyms
        FileReader synsetFileReader = 
            new FileReader("G:\\Natural Language Final Project\\synsets.txt");

        // Always wrap FileReader in BufferedReader.
        BufferedReader lemmaBuf = 
            new BufferedReader(lemmasFileReader);
        
        BufferedReader posBuf = 
            new BufferedReader(POSFileReader);
        
        BufferedReader synBuf = 
            new BufferedReader(synsetFileReader);
        
        //read the synonym sets into a hashmap
        String synLine = "";
        HashMap<String,String> synMap = new HashMap<>();
        
        while((synLine = synBuf.readLine()) != null){
            String[] synSet = synLine.split(" ");

            for(int i = 0; i<synSet.length; i++){
                synMap.put(synSet[i], synSet[0]);
            }  
        }
        /*
        //test map
        Set<String> keys = synMap.keySet();
        for(String k:keys){
            System.out.println(k+"<-"+synMap.get(k));
        }
        */
        //create a FileWriter using the new path and file
        FileWriter fileWriter = new FileWriter(fileWriterName);
        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        
        String posLine = "";
        String lemmaLine = "";
        //make an LinkedList for the new clauses
        LinkedList<String> clauses = new LinkedList<>();
        //get a set of keys(verb lemmas) from hashmap
        Set<String> synKeys = synMap.keySet();
        
        //read every line in the POSTagger file version of original document (the original statements with parts of speech tagged)
        //and its corresponding line in the LemmasCleaned version of the text (all but the bare bones of the sentence left so that
        //a prolog clause can easily be made from it,e.g. Daniel travel bathroom becomes (using the synset file) go(daniel, bathroom)
        while((posLine = posBuf.readLine()) != null) {
            posLine = posLine.toLowerCase();
            
            lemmaLine = lemmaBuf.readLine();
            lemmaLine = lemmaLine.toLowerCase();
            
            //make an LinkedList of the pos line
            String[] posArray = posLine.split(" ");
            LinkedList<String> pos = new LinkedList<>();
            for(String s: posArray)
            {
                pos.add(s);
            }
 
            
            //make a string array of the lemma line
            String[] lemmas = lemmaLine.split(" ");

            //compare the lemmas to the pos tags to determine clauses for subjects
            //if clause does not already exist, add it to the clause list
            for(String s:lemmas){
                if(pos.contains(s+"_nnp") && !clauses.contains("person("+s+").")) {
                    clauses.add("person("+s+").");
                    System.out.println("Adding person: " + s);
                }
            }
            
            //compare second lemma (verb) to set of keys in hashmap
            //depending on the synonym set of the verb, the object will either be a location or a thing
            switch (lemmas[1]){
                case "drop" : 
                    if(pos.contains(lemmas[2]+"_nn") && !clauses.contains("object("+lemmas[2]+").")) {
                        clauses.add("object("+lemmas[2]+").");
                        System.out.println("Adding object: " + lemmas[2]);
                    }
                    break;
                case "get" :
                    if(pos.contains(lemmas[2]+"_nn") && !clauses.contains("object("+lemmas[2]+").")) {
                        clauses.add("object("+lemmas[2]+").");
                        System.out.println("Adding object: " + lemmas[2]);
                    }
                    break;
                case "go" :
                    if(pos.contains(lemmas[2]+"_nn") && !clauses.contains("location("+lemmas[2]+").")) {
                        clauses.add("location("+lemmas[2]+").");
                        System.out.println("Adding location: " + lemmas[2]);
                    }
                    break;
                    
            }
        }//end "while((posLine = posBuf.readLine()) != null)"
        
        //close lemma buffer and part of speech buffer
        //on second read-through of lemma buffer get clauses from whole sentences
        lemmaBuf.close(); 
        posBuf.close();
        
        // FileReader and BufferedReader for the lemmas
        lemmasFileReader = new FileReader(fileReaderName+".LemmasCleaned.txt");
        lemmaBuf = new BufferedReader(lemmasFileReader);
        
        line = "";
        while((line = lemmaBuf.readLine()) != null){
            line = line.toLowerCase();
            String[] lemmas = line.split(" ");
            //System.out.println(line);
            switch (lemmas[1]){
                case "drop" : 
                    //if(!clauses.contains("drop("+lemmas[0]+","+lemmas[2]+").")){ // shouldn't check to see if clause already exists
                                                                                   //unlike the bare fact of existence person(john)
                                                                                   //which only needs to be stated once
                                                                                   //john could theoretically move around a lot
                        clauses.add("drop("+lemmas[0]+","+lemmas[2]+").");
                       
                    break;
                case "get" :
                    //if(!clauses.contains("get("+lemmas[0]+","+lemmas[2]+").")){
                        clauses.add("get("+lemmas[0]+","+lemmas[2]+").");
                       
                    break;
                case "go" :
                    //if(!clauses.contains("go("+lemmas[0]+","+lemmas[2]+").")){
                        clauses.add("go("+lemmas[0]+","+lemmas[2]+").");
                       
                    break;
            }
        }
        
        //for each clause in the list write it to the clause file
            for(String c: clauses){
                System.out.println(c);
                bufferedWriter.write(c+"\n");
            }
            
        // Always close files.
        lemmaBuf.close(); 
        synBuf.close();
        bufferedWriter.close();
        
    }
}

