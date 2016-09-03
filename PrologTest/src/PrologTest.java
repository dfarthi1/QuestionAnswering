


import java.util.Hashtable;
import jpl.*;
import java.io.*;
import jpl.Query;
import java.util.Scanner;


public class PrologTest {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(java.lang.String[] args) throws IOException {
        
        //include the prolog file with clauses to test
        File clauseFile = new File ("clauses_qa2_1.pl");
        File ruleFile = new File ("rules.pl");
        java.lang.String clausePath = clauseFile.getAbsolutePath();
        java.lang.String rulePath = ruleFile.getAbsolutePath();
        System.out.println("Clause file path: " + clausePath);
        System.out.println("Rule file path: " + rulePath);
        //String t1 = "consult('clauses_qa2.pl').";
        //String t2 = "consult('rules.pl').";
        
        Query q1 = new Query( 
        "consult", 
        new Term[] {new Atom("clauses_qa2.pl")});
        
        System.out.println( "consult " + (q1.query() ? "succeeded" : "failed"));
        
        Query q2 = new Query( 
        "consult", 
        new Term[] {new Atom("rules.pl")});
        
        System.out.println( "consult " + (q2.query() ? "succeeded" : "failed"));
        
        Scanner scan = new Scanner(ruleFile);
        while (scan.hasNextLine()){
            System.out.println(scan.nextLine());
        }
        
        
        jpl.JPL.init();
        

        Variable X = new Variable();
        Variable Y = new Variable();
        Query q = new Query("isAt",new Term[]{new Atom("john"),Y});
        
        while (q.hasMoreElements()) {
           Hashtable binding = (Hashtable) q.nextElement();
            Term t = (Term) binding.get(Y);
            System.out.println(t); 
        }
        System.out.println(q.toString());
        
    }


    
}
