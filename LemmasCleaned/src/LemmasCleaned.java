
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dave
 */
public class LemmasCleaned {
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to clean: ");
        String fileName = keyboard.nextLine();
        fileName = fileName.replace(".txt",".Lemmas.txt");
        
        System.out.print("Reading stopword list... ");
        String stopFile = "G:\\Natural Language Final Project\\stopwords.txt";
        
        

        // This will reference one line at a time
        String line = null;
        
        //temporary ArrayList of words on each line
        ArrayList<String> temp = new ArrayList<>();

        //get array list of stopwords
        ArrayList<String> stop = new ArrayList<>();
        
        FileReader read = new FileReader(stopFile);
        BufferedReader buf = new BufferedReader(read);
        
        while((line=buf.readLine()) != null){
            stop.add(line);
            //System.out.println(line);
        }
        
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            String newFileName = fileName.replace(".Lemmas.txt", ".LemmasCleaned.txt");
            //create a FileWriter using the new path and file
            
                FileWriter fileWriter = new FileWriter(newFileName);
                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                   
            
            // remove square brackets from each line
            int lineNum = 1;
            while((line = bufferedReader.readLine()) != null) {
                System.out.print(lineNum+": "+line+" -> ");
                String newLine = "";
                //remove square brackets, period, spaces and final comma from each line
                newLine = line.replace('[',' ');
                newLine = newLine.replace(']',' ');
                newLine = newLine.replace('.',' ');
                newLine = newLine.substring(0, newLine.length()-1);
                newLine = newLine.replaceAll("\\s",""); 

                //create temporary string array of words in each line
                String[] str = newLine.split(",");
                
                //clear temporary arraylist
                temp.clear();
                
                //add each word to the temp arraylist if it is not on the list of stop words
                for(String s:str){
                    if(!stop.contains(s)) 
                        temp.add(s);
                }
                                
                //make a single string out of temp array
                String newString = "";
                for(int i = 0; i < temp.size()-1;i++){
                    newString += (temp.get(i)+" ");
                }
                newString += temp.get(temp.size()-1);
                System.out.println(newString);
                bufferedWriter.write(newString+"\n");
            }
            
               

            // Always close files.
            bufferedReader.close();         
            bufferedWriter.close(); 

    }
}
