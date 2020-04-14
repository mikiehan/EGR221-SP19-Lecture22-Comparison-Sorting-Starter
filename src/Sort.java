/**
 * A class that contains several sorting routines,
 * implemented as static methods.
 * Arrays are rearranged with smallest item first,
 * using compareTo.
 *
 * @author Mark Allen Weiss
 */
public final class Sort {

    /**
     * A Utility method to swap to elements in an array.
     *
     * @param a      an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <E> void swap(E[] a, int index1, int index2) {
        E tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /**
     * Simple insertion sort.
     *
     * @param a an array of Comparable items.
     */
    public static <E extends Comparable<? super E>> void insertionSort(E[] a) {
        insertionSort(a, 0, a.length - 1);
    }

    /**
     * Also used by quicksort as internal insertion sort routine for subarrays
     *
     * @param a          an array of Comparable items.
     * @param startIndex the left-most index of the subarray (inclusive).
     * @param endIndex   the right-most index of the subarray (inclusive).
     * @post a is sorted from startIndex and endIndex
     */
    private static <E extends Comparable<? super E>> void insertionSort(E[] a, int startIndex, int endIndex) {

    }

    /**
     * Standard heapsort.
     *
     * @param a an array of Comparable items.
     */
    public static <E extends Comparable<? super E>> void heapsort(E[] a) {
        buildHeap(a);
        for (int lastIndex = a.length - 1; lastIndex > 0; lastIndex--) {
            deleteMax(a, lastIndex);
        }
    }

    //need to build max heap to do sorting in ascending order
    private static <E extends Comparable<? super E>> void buildHeap(E[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--)
            percolateDown(a, i, a.length);
    }

    private static <E extends Comparable<? super E>> void deleteMax(E[] a, int lastIndex) {
        swap(a, 0, lastIndex); //remove max element and send to last index
        percolateDown(a, 0, lastIndex); //lastIndex is the new size since we removed 1 just now
    }


    /**
     * Internal method for heapsort that is used in deleteMax and buildHeap.
     *
     * @param a an array of Comparable items.
     * @index i the position from which to percolate down.
     * @int n the logical size of the binary heap.
     */
    private static <E extends Comparable<? super E>> void percolateDown(E[] a, int hole, int n) {
        E data = a[hole];
        int child = leftChild(hole);
        while (child < n) { //as long as left child exists
            if (child != n - 1 && a[child + 1].compareTo(a[child]) > 0) //right child exists and is bigger than left child
                child++; //pick right child

            if (data.compareTo(a[child]) < 0) //data is smaller than child
                a[hole] = a[child]; //need to promote child up
            else
                break;

            hole = child;
            child = leftChild(hole);
        }
        a[hole] = data; //found the correct hole so insert data into hole
    }

    /**
     * Internal method for heapsort.
     *
     * @param i the index of an item in the heap.
     * @return the index of the left child.
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Quicksort algorithm.
     *
     * @param a an array of Comparable items.
     */
    public static <E extends Comparable<? super E>> void quicksort(E[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static final int CUTOFF = 10;

    /**
     * Return median of left, center, and right.
     * Order these and hide the pivot.
     */
    private static <E extends Comparable<? super E>> E median3(E[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) //center is smaller than left
            swap(a, left, center);
        if (a[right].compareTo(a[left]) < 0) //right is smaller than left
            swap(a, left, right);
        if (a[right].compareTo(a[center]) < 0) //right is smaller than center
            swap(a, center, right);

        // Now center is pivot
        // but place the pivot at position right - 1
        swap(a, center, right - 1);
        return a[right - 1]; //pivot is at the right - 1!
    }

    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static <E extends Comparable<? super E>> void quicksort(E[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            E pivot = median3(a, left, right);

        } else  // Do an insertion sort on the subarray (since insertion sort is faster with smaller array
            insertionSort(a, left, right);
    }

    /**
     * Mergesort algorithm.
     *
     * @param a an array of Comparable items.
     */
    public static <E extends Comparable<? super E>> void mergeSort(E[] a) {
        E[] tmpArray = (E[]) new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    /**
     * Internal method that makes recursive calls.
     *
     * @param a        an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left     the left-most index of the subarray.
     * @param right    the right-most index of the subarray.
     */
    private static <E extends Comparable<? super E>> void mergeSort(E[] a, E[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     *
     * @param a        an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos  the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static <E extends Comparable<? super E>> void merge(E[] a, E[] tmpArray,
                                                                int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd)
            if (a[leftPos].compareTo(a[rightPos]) <= 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];

        while (leftPos <= leftEnd)    // Copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd)  // Copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        // Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    /**
     * Quick selection algorithm.
     * Places the kth smallest item in a[k-1].
     *
     * @param a an array of Comparable items.
     * @param k the desired rank (1 is minimum) in the entire array.
     */
    public static <E extends Comparable<? super E>> void quickSelect(E[] a, int k) {
        quickSelect(a, 0, a.length - 1, k);
    }

    /**
     * Internal selection method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * Places the kth smallest item in a[k-1].
     *
     * @param a     an array of Comparable items.
     * @param left  the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     * @param k     the desired index (1 is minimum) in the entire array.
     */
    private static <E extends Comparable<? super E>> void quickSelect(E[] a, int left, int right, int k) {
        if (left + CUTOFF <= right) {
            E pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swap(a, i, j);
                else
                    break;
            }

            swap(a, i, right - 1);   // Restore pivot

            if (k <= i)
                quickSelect(a, left, i - 1, k);
            else if (k > i + 1)
                quickSelect(a, i + 1, right, k);
        } else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }
}
