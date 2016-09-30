import java.util.*;
import java.io.*;
public class RemoveInt {
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename to clean: ");
        String fileName = keyboard.nextLine();
        
        

        // This will reference one line at a time
        String line = null;

        
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            String newFileName = fileName.replace(".txt", ".IntRemoved.txt");
            //create a FileWriter using the new path and file
            
                FileWriter fileWriter = new FileWriter(newFileName);
                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                   
            
            // revome integers from line of text and write new line to
            //output file
            int lineNum = 1;
            line = bufferedReader.readLine();
            while(line != null) {
                System.out.print(lineNum + ": " + line+" -> ");
                String newLine = line.replaceAll("\\d","");
                System.out.println(newLine); 
                bufferedWriter.write(newLine);
                bufferedWriter.write("\n");
                line = bufferedReader.readLine();
                lineNum++;
                }
               

            // Always close files.
            bufferedReader.close();  
            bufferedWriter.close();
        
    }
        
        
        
        
}
    
    
