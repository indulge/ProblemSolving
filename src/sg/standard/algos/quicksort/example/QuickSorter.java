package sg.standard.algos.quicksort.example;

public class QuickSorter
{
    //private int[] a;
   // private int n;

    public static void sort(int[] a)
    {
//        this.a=a;
//        n=a.length;
        quicksort(a, 0, a.length - 1);
    }

    //  lo is the lower index, hi is the upper index
    //  of the region of array a that is to be sorted
    private static void quicksort (int[] a, int lo, int hi)
    {
    	int part = partition(a, lo, hi);
        // recursion
    	if (part < 0) return;
        quicksort(a, lo, part);
        quicksort(a, part, hi);

    }
    
    public static int partition(int[] a, int lo, int hi)
    {
        if (lo>=hi) // less than two elements
            return -1;

        // comparison element x
        int x=a[lo+hi/2];

        //  partition
        int i=lo, j=hi;
        while (i<=j)
        {
            while (a[i]<x) i++;
            while (a[j]>x) j--;
            if (i<=j)
                exchange(a, i++, j--);
        }
        return i;
    }
    
    public static void part2(int[] a, int left, int right) {
        int l = left;
        int r = right - 1;

        if (l >= r) {
            return;
        }

        int pivot = a[l];
        l++;
        for (;;) {
            while (l <= r && a[l] <= pivot) l++;
            while (a[r] > pivot  && l < r) r--;

            if (l < r) {
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
            } else {
                break;
            }
        }
        l--;
        a[left] = a[l];
        a[l] = pivot;

//        qsort(a, left, l);
//        qsort(a, r, right);
    }

    private static void exchange(int[] a, int i, int j)
    {
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

}    // end class QuickSorter