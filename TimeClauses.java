
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;





/**
 *
 * @author Dave
 */
public class TimeClauses {
    
    public static class ObjTime{
        
        public Integer time;
        public String name;
        
        public ObjTime(String name){
            this.name = name;
            this.time = 0;
        }
        
        public Integer getTime(){
            return time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        public void incTime(){
            time = getTime() + 1;
        }
        
        public String toString(){
            return "["+name+","+time.toString()+"]";
        }

    }
    //method to find the ObjTime object associated with a specific name
    //this method only works with single argument Prolog facts such as person(John), object(football) and location(hallway).
    public static ObjTime findObject(LinkedList<ObjTime> list, String name){
        for(ObjTime o:list){
            if (o.getName().equals(name)) return o;
        }
        return null;
    }
    public static void main(String[] args)throws IOException, ClassNotFoundException
    {             

        //get file name with path from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path and filename of clauses to add timing to: ");
        String fileName = keyboard.nextLine();
        
        String fileWriterName = fileName.replace(".txt", ".ClausesTimes.txt");
        String fileReaderName = fileName.replace(".txt", ".Clauses.txt");
        
        // FileReader for the clauses
        FileReader clauseReader = 
            new FileReader(fileReaderName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader clauseBuf = 
            new BufferedReader(clauseReader);
            
        //create a FileWriter using the new path and file
        FileWriter fileWriter = new FileWriter(fileWriterName);
        // Always wrap FileWriter in BufferedWriter.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        //create arraylist of clauses to be written to the new file
        LinkedList<String> clauses = new LinkedList<>();
        
        //create arraylist of people/objects and times initially set to zero
        LinkedList<ObjTime> objTimes = new LinkedList<>();
        String clauseLine = "";
        int oneArgClauses = 0; //keep a count of the one argument clauses defining locations,persons,objects
        
        //go through file of clauses and create ObjTime objects for arraylist of
        //all objects and people
        while((clauseLine = clauseBuf.readLine()) != null) {
            clauseLine.toLowerCase();
            System.out.println("Clause line: " + clauseLine);
            //if the clause arguments do not have a comma, then it must
            //be defining a location, object or person
            if(!clauseLine.contains(",")){
                oneArgClauses++;
                int leftParenIndex = clauseLine.indexOf("(");
                int rightParenIndex = clauseLine.indexOf(")");
                String clause = clauseLine.substring(0,leftParenIndex);
                String obj = clauseLine.substring(leftParenIndex+1,rightParenIndex);
                System.out.println(clause+"=>"+obj); 
                if(clause.equals("person") || clause.equals("object"))
                    objTimes.add(new ObjTime(obj)); 
                clauses.add(clauseLine);
            }
        }
       
        /* print list of objects and people found in clauses */
        String output = "";
        for(ObjTime o:objTimes){
            output += o.toString();
        }
        System.out.println(output);
        
         
        //close the clauseReader buffer and reopen it to add times to two argument clauses
        clauseBuf.close();

        clauseReader = 
            new FileReader(fileReaderName);
        
        clauseBuf = 
            new BufferedReader(clauseReader);
        
        //read all two arg clauses and add a time
        int currentLine = 0;
        while((clauseLine = clauseBuf.readLine()) != null){
            currentLine++;
            if(currentLine > oneArgClauses){
                int leftParenIndex = clauseLine.indexOf("(");
                int rightParenIndex = clauseLine.indexOf(")");
                String clause = clauseLine.substring(0,leftParenIndex);
                String[] clauseArgs = clauseLine.substring(leftParenIndex+1,rightParenIndex).split(",");
                System.out.println(clauseLine+"=> 1:"+clauseArgs[0]+" 2:"+clauseArgs[1]);
                ObjTime ot = null;
                
                switch(clause){
                    case "go" :
                        ot = findObject(objTimes,clauseArgs[0]);
                        ot.incTime();
                        clause += "("+clauseArgs[0]+","+clauseArgs[1]+","+ot.getTime()+").";
                        clauses.add(clause);
                        System.out.println("added: " + clause);
                        break;
                    case "drop" :
                        ot = findObject(objTimes,clauseArgs[1]);
                        ot.incTime();
                        clause += "("+clauseArgs[0]+","+clauseArgs[1]+","+ot.getTime()+").";
                        clauses.add(clause);
                        System.out.println("added: " + clause); 
                        break;
                    case "get" :
                        ot = findObject(objTimes,clauseArgs[1]);
                        ot.incTime();
                        clause += "("+clauseArgs[0]+","+clauseArgs[1]+","+ot.getTime()+").";
                        clauses.add(clause);
                        System.out.println("added: " + clause); 
                        break;
                    default :
                        System.out.println("added: "+ clauseLine);
                        clauses.add(clauseLine);
                }
            }
        }
        
        
        //for each clause in the list write it to the clause file
            for(String c: clauses){
                System.out.println(c);
                bufferedWriter.write(c+"\n");
            }
          
        // Always close files.
        clauseBuf.close();
        bufferedWriter.close();
        
    }
}


