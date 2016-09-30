
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;





/**
 *
 * @author Dave
 */
public class CreateSceneFiles {
    
    
    public static void main(String[] args)throws IOException, ClassNotFoundException
    {             

        //get path of files to read
        File clauseFile = new File ("clauses_qa2.pl");
        File indexFile = new File ("clauses_index_qa2.pl");
        String clausePath = clauseFile.getAbsolutePath();
        String indexPath = indexFile.getAbsolutePath();
        System.out.println("Clause file path: " + clausePath);
        System.out.println("Rule file path: " + indexPath);
        
        
        //FileReader for the clause file without indices
        FileReader clauseFileReader = new FileReader("G:\\Natural_Language_Final_Project\\SceneFiles\\src\\clauses_qa2.pl");
        BufferedReader clauseBuf = new BufferedReader(clauseFileReader);
        
        // FileReader for the clause file with indices
        FileReader indexFileReader = new FileReader("G:\\Natural_Language_Final_Project\\SceneFiles\\src\\clauses_index_qa2.pl");
        BufferedReader indexBuf = new BufferedReader(indexFileReader);
            
        String line = "";
        String clause = "";  
        String fileName = "";
        BufferedWriter bufferedWriter = null;
        
        //Create Array of Strings to hold scenes by index
        //first index of array is sceneIndex number 1-956
        //second index is for the clauses associated with the scene
        String clauses[] = new String[10000];

        System.out.print("Enter scene index between 1 and 956: ");
        Scanner key = new Scanner(System.in);
        int scene = key.nextInt(); key.nextLine();
        int currIndex = 0;
        //go through indexed file and add original clauses for each scene
        int currScene = 1;
        while((line = indexBuf.readLine()) != null && currScene <= scene) {  
            //get substring between last comma and right parenthesis and convert to integer           
            String[] argv = line.split(",");
            int rightParenIndex = argv[3].indexOf(")");
            Integer lineIndex = new Integer(Integer.valueOf(argv[3].substring(0,rightParenIndex)));
            currScene = lineIndex;
            clause = clauseBuf.readLine();
            System.out.println("lineIndex = " + lineIndex + " clause => " + clause);
            //add clause from indexed file to clause array
            clauses[currIndex++]=clause;
            }
        
        for(int k = 1;k<currIndex;k++){
            System.out.print("Clause #"+k+" =>");
            
            System.out.println("\t"+clauses[k]);
            
        }
        
        int sceneIndex = 1;
        fileName = "G:\\Natural_Language_Final_Project\\Prolog\\qa2_" + (Integer.toString(scene) + ".pl");
        FileWriter fileWriter = new FileWriter(fileName);
        // Always wrap FileWriter in BufferedWriter.
        bufferedWriter = new BufferedWriter(fileWriter);
        //write clause to new file
        System.out.println("sceneIndex="+sceneIndex);
            
        for(int i = 1; i < currIndex; i++){
            bufferedWriter.write(clauses[i] + "\n");
            System.out.println("Writing " + clauses[i] + " to qa2_" + (Integer.toString(scene) + ".pl"));
            }
        bufferedWriter.close();
        //close the buffered readers
        clauseBuf.close();
        indexBuf.close();
    }//end main
}

        
       
        
         
        



