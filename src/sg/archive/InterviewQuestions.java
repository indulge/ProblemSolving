/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.archive;

import java.util.List;

import sg.ds.bst.BinarySearchTree;

/**
 *
 * @author sachin
 */
public class InterviewQuestions {

    BinarySearchTree bst=null;
    int treesize = 5;

    public InterviewQuestions() {
         bst = new BinarySearchTree();
         bst.buildTree(treesize);
         //bst.buildTreeFromOutputString("8 5 3 2 5");
    }

    //1.      Given a Binary Search Tree, write a program to print the kth smallest 
    //  element without using any static/global variable.
    //  You can’t pass the value k to any function also.
    public int getKthSmallest1(int k) {

        List <Integer> inord = null;
        bst.printTree();
        inord = bst.getInorder();
        int ret=inord.get(k-1);
        System.out.println("k="+k+" smallest element= "+ret);
        return ret;
    }

    public int getKthSmallest2(int k) {

        bst.printTree();
        int ret=bst.getKthSmallest(k);
        System.out.println("k="+k+" smallest element= "+ret);
        return ret;
    }

//Given an array of size n. It contains numbers in the range 1 to n.
//Each number is present at least once except for 1 number. Find the missing number.

//Given an array of size n. It contains numbers in the range 1 to n.
//Each number is present at least once except for 2 numbers. Find the missing numbers.

//Given an array of size n. It contains numbers in the range 1 to n.
//Find the numbers which aren’t present.

    //if number is simply missing and it's place is occupied by 0, then add numbers, sbbrtct from sum of ..n
    //if number is replaced with any other number from 1 to n.
    //num count:
    //get an array b[1..n], mark b[a[i]] = 1, then in the end, b[i]==0 gives missing number
public void findMissingnumber() {

    int arr_size = 10;
    int[] arr = new int[arr_size];
    
    for (int i=1;i<=arr_size;i++) {
        arr[i-1]=i;
    }
    arr[4-1] = 0;
    arr[5-1]=0;

    long s=0;
    long p=1;

    long tot_prod = 1;

    for (int i=1;i<=arr_size;i++) {
        tot_prod = tot_prod*i;
        if (arr[i-1]<=0) {

        } else {
            s = s+arr[i-1];
            p = p*+arr[i-1];
        }
        
    }
    System.out.println("s:"+s);
    System.out.println("p:"+p);
    
    s = (arr_size*(arr_size+1))/2 - s;
    p = tot_prod/p;

    System.out.println("s:"+s);
    System.out.println("p:"+p);

    double d = s*s - 4*p;
    double num1 = ( ( s + Math.pow(d, 1/2) )/2 );
    double num2 = ( ( s - Math.pow(d, 1/2) ) /2);
    System.out.println("num1:"+num1);
    System.out.println("num2:"+num2);


    }

//Given a string,find the first un-repeated character in it? Give some test cases
    //use a set to eep keys, in order, and a map to store occurences
    //parse the map in key order, first key with val == 1 is the unrepeated character


     public static void main(String[] args) {
        InterviewQuestions ivq=new InterviewQuestions();
        int k=5;
        ivq.getKthSmallest1(k);
        ivq.getKthSmallest2(k);
        ivq.findMissingnumber();

        
        
     }

}
