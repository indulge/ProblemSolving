package sg.problems.misc;

import java.util.ArrayList;
import java.util.List;


public class UnionIntersection {

    private static int[] array_one = new int[]{2,3,4,5,12,18,32};
    //private static int[] array_one = new int[]{2,3,4,5};
    private static int[] array_two = new int[]{1,3,5,7,9};

    public static void main(String main[]){
        int[] union = getUnion(array_one, array_two);
        System.out.println("--- Union of sets ---");
        for(int i=0; i<union.length; i++){
            System.out.print(union[i] + " ");
        }

        System.out.println("");
        
        Integer[] union1 = getUnion2(array_one, array_two);
        System.out.println("--- Union of sets ---");
        for(int i=0; i<union1.length; i++){
            System.out.print(union1[i] + " ");
        }

        System.out.println("");

        Integer[] intersection = getIntersection(array_one, array_two);

        System.out.println("--- Intersection of sets ---");
        for(int i=0; i<intersection.length; i++){
            System.out.print(intersection[i] + " ");
        }
        System.out.println("");
    }

    private static Integer[] getUnion2(int[] arr1, int[] arr2 ) {
    	List<Integer> arrUnion = new ArrayList<Integer>();
    	
    	for (int i=0;i<arr1.length;i++) {
    		arrUnion.add(arr1[i]);
    	}
    	
    	for (int i=0;i<arr2.length;i++) {
    		if (!arrUnion.contains(arr2[i])) {
    			arrUnion.add(arr2[i]);
    		}
    	}
    	
    	Integer[] retArr  = new Integer[arrUnion.size()];
    	return arrUnion.toArray(retArr);
    	
    }
    
//    private static Integer[] getIntersection2(int[] arr1, int[] arr2) {
//    	
//    }
    private static int[] getUnion(int[] a_one, int[] a_two){
        int i=0, j=0;
        int value = -1;
        int MAX_ELEM = 0;
        int[] a_return = new int[a_one.length + a_two.length -1];
        try{
            while(i<a_one.length || j<a_two.length){
                if(a_one[i] < a_two[j]){
                    value = a_one[i];
                    i++;
                }else{
                    value = a_two[j];
                    j++;
                }
                if(!found(a_return, value, 0, MAX_ELEM)){
                    a_return[MAX_ELEM++] = value;
                }
            }
        }catch(IndexOutOfBoundsException ex){
            if(i == a_one.length){
                for(int k=j; k<a_two.length; k++){
                    if(!found(a_return, a_two[k], 0, MAX_ELEM)){
                        a_return[MAX_ELEM++] = a_two[k];
                    }
                }
            }else{
                for(int l=i; l<a_one.length; l++){
                    if(!found(a_return, a_one[l], 0, MAX_ELEM)){
                        a_return[MAX_ELEM++] = a_one[l];
                    }
                }
            }
        }
        return a_return;
    }

    private static Integer[] getIntersection(int[] a_one, int[] a_two){
        ArrayList<Integer> a_list = new ArrayList<Integer>();
        int i=0, j=0;
        while(i < a_one.length && j< a_two.length){
            if(a_one[i] == a_two[j]){
                a_list.add(a_one[i]);
                i++;
                j++;
            }else if(a_one[i] < a_two[j]){
                i++;
            }else if(a_one[i] > a_two[j]){
                j++;
            }
        }
        Integer[] a_return = new Integer[a_list.size()];
        a_list.toArray(a_return);
        return a_return;
    }

    /* -------- limear search --------- */
    private static boolean found1(int[] array, int value,int start, int end){
        for(int i=0; i<array.length; i++){
            if( array[i] == value){
                return true;
            }
        }
        return false;
    }
    /* ---------- binary search ------------- */
    private static boolean found(int[] array, int value, int start, int end){
        if(end < 0 || start < 0){
            return false;
        }
        if(array[start] == value || array[end] == value){
            return true;
        }
        if(end-start == 1){
            if(array[end] == value){
                return true;
            }
            if(array[start] == value){
                return true;
            }
        }else{
            int mid = (start + end)/2;
            if(array[mid] == value){
                return true;
            }else if(array[mid] < value){
                return found1(array, value, mid+1, end);
            }else if(array[mid] > value){
                return found1(array, value, start, mid-1);
            }
        }
       return false;
    }
}
