/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dave
 */

import java.util.Hashtable;
import jpl.*;
import java.io.*;
import jpl.Query;
import java.util.Scanner;

public class Test1 {
    public static void main (String[] args){
        JPL.init();
        
        Term consult_arg[] = { 
			new Atom( "G:\\Natural Language Final Project\\PrologTest\\src\\test.pl")
		};
        Query consult_query = 
			new Query( 
				"consult", 
				consult_arg );
    
    boolean consulted = consult_query.query();
    
        Term argv1[] = { 
        new Atom( "joe" ), 
        new Atom( "ralf" ) 
        }; 
        Query query1 = 
            new Query( 
                "child_of", 
                argv1);

        System.out.println( "child_of(joe,ralf) = " + query1.query() );
        
        
        Variable X = new Variable(); 
        Term argv[] = { 
        X, 
        new Atom( "ralf" ) 
        }; 
        Query query = 
        new Query( 
            "descendent_of", 
            argv );
        System.out.println( "querying descendent_of( X, ralf )" ); 
        java.util.Hashtable solution = 
        query.oneSolution(); 
        System.out.println( "X = " + solution.get( X ) );
    }
}
