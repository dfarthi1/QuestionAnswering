import java.util.*;
import java.io.*;

public class RemoveQuestions {
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to remove questions from: ");
        String fileName = keyboard.nextLine();
        fileName = fileName.replace(".txt",".IntRemoved.txt");
        

        // This will reference one line at a time
        String line = null;

        
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            String newFileName = fileName.replace(".IntRemoved.txt", ".QuestionsRemoved.txt");
            //create a FileWriter using the new path and file
            
                FileWriter fileWriter = new FileWriter(newFileName);
                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                   
            
            // revome integers from line of text and write new line to
            //output file
            int lineNum = 1;
            while((line = bufferedReader.readLine()) != null) {
                System.out.print(lineNum+": "+line+" -> ");
                if (line.contains("?")){
                        System.out.println();
                        lineNum++;
                }
                else {
                    bufferedWriter.write(line+"\n");
                    System.out.println(line);
                    lineNum++;
                }
            }
               

            // Always close files.
            bufferedReader.close();         
            bufferedWriter.close(); 
            /*
            //create an ArrayList of text lines
            ArrayList<String> text = new ArrayList<>();
            
            //remove blank lines from output
            FileReader reader = 
                new FileReader(newFileName);
            BufferedReader buffRead = 
                new BufferedReader(reader);
            FileWriter writer = new FileWriter(newFileName);
                BufferedWriter buffWriter = new BufferedWriter(writer);
                
            while((line = bufferedReader.readLine()) != null) {
                if(!line.isEmpty()){
                    text.add(line);
                    System.out.println(line);
                }
            }
            
            buffRead.close();
            
            for(String s:text){
                buffWriter.write(s+"\n");
            }
            
            buffWriter.close();
            */
    }     
}
    
    

