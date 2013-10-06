/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.archive.linkedlist;

import java.util.Arrays;

/**
 *
 * @author sachin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Float f = new Float(3.1);
       // Integer i = new Integer(1);
        long l = 2;
       // System.out.println("Result: "+l+f+i);
        boolean b1 = true;
        
         Object [] myObjects = {
        		 new Integer(12),
        		 new Boolean(true),
        		 new String("foo"),
        		 new Integer(5)
        		 
        		 };
        		 Arrays.sort(myObjects);
        		 for( int i=0; i<myObjects.length; i++) {
        		 System.out.print(myObjects[i].toString());
        		 System.out.print("");
        		 }
        
        

    }

}

interface Status {
	int val = 10;
}
