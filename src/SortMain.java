/**
 * Created by mhan on 4/12/2017.
 */
public class SortMain {

    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;

    private static void checkSort( Integer [ ] a )
    {
        for( int i = 0; i < a.length; i++ )
            if( a[ i ] != i )
                System.out.println( "Error at " + i );
        System.out.println( "Finished checksort" );
    }


    public static void main( String [ ] args ) {
        Integer [ ] a = new Integer[ NUM_ITEMS ];
        for( int i = 0; i < a.length; i++ )
            a[ i ] = i;

        for( theSeed = 0; theSeed < 20; theSeed++ )
        {
            Random.permute( a );
            Sort.insertionSort( a );
            checkSort( a );

            Random.permute( a );
            Sort.heapsort( a );
            checkSort( a );

            Random.permute( a );
            Sort.mergeSort( a );
            checkSort( a );

            Random.permute( a );
            Sort.quicksort( a );
            checkSort( a );

            Random.permute( a );
            Sort.quickSelect( a, NUM_ITEMS / 2 );
            System.out.println( a[ NUM_ITEMS / 2 - 1 ] + " " + NUM_ITEMS / 2 );
        }


        Integer [ ] b = new Integer[ 10_000_000 ];
        for( int i = 0; i < b.length; i++ )
            b[ i ] = i;

        Random.permute( b );
        long start = System.currentTimeMillis( );
        Sort.quickSelect( b, b.length  / 2 );
        long end = System.currentTimeMillis( );
        System.out.println( "Timing for Section 1.1 example: " );
        System.out.println( "Selection for N = " + b.length + " takes " +
                ( end - start ) + "ms." );
        System.out.println( b[ b.length / 2 - 1 ] + " " + b.length / 2 );
    }
}
