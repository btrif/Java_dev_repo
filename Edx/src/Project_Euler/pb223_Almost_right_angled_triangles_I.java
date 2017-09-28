package Project_Euler;

//  Created by Bogdan Trif on 23-09-2017 , 7:53 PM.


import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

public class pb223_Almost_right_angled_triangles_I {

    public static boolean is_square(double nr){
        double sq = Math.sqrt(nr);
        long x = (long) sq;
        if(Math.pow(sq,2) == Math.pow(x,2)) {
            return true ;
        }
        return false;
    }


    public static int almost_brute_force(int lim) {

    long[] Sq1 = new long[ (int) (lim/2)] ;
    long[] Sq = new long[ (int) (lim/2)] ;

    for (int k=0 ; k< lim/2 ; k++){
        Sq1[k] = (k*k)+1 ;
        Sq[k] = (k*k) ;
    }

    int cnt = 0 ;
//    for (int i =4 ; i< Sq1.length ; i++  ) {
    for (int i =4 ; i< lim/2 ; i++  ) {
        long high = (i-1)*(i-1) ;
        long c = i*i+1 ;
//        int low = (int) floor( sqrt(  (Sq1[i]-1)/2 ) )  ;
        long low = (long) floor( sqrt(  high/2 ) )  ;
        if (i%100000 == 0) {
//            System.out.println(i+".   high   =  "+ i +" " + high +  "  Sq1 = " +Sq1[i] + "    c_sq+1 = "+ Sq1[i] +"  high = " +Sq[high] +"    low="+ low  ) ;
            System.out.println(i+".   high   =  "+ i +" " + high +  "  c = " + c + "  high = " +high +"    low="+ low  ) ;
        }

//        for (int j=high ; j>low ; j-- ) {
        for (long j=high ; j>low ; j-- ) {
//            long b_sq = Sq[j] ;
            long b_sq = j*j ;
//            if ( is_square( (Sq1[i] - b_sq) ) ) {
            if ( is_square( (c - b_sq) ) ) {
//                int a = (int) (sqrt(Sq1[i] - b_sq)) ;
                int a = (int) (sqrt(c - b_sq)) ;
                int b = (int) (sqrt(b_sq)) ;
//                int c = (int) (sqrt(Sq1[i]-1 )) ;
                if ( (a+b+c) <= lim) {
                    cnt += 1 ;
                    System.out.println(cnt + ".      a = "+  a + "    b=" +  b + "     c="+c +"      perim= "+ (a+b+c) + "         c^2+1 = " + c ) ;
                    }
                }
            }
        }
    return cnt + (int) (lim/2) - 1 ;
    }

    public static void main(String[] args) {

        double t1 = System.nanoTime() ;

        System.out.println("\nAlmost-right angled triangles : " + almost_brute_force(25000) );

        System.out.println("\nCompleted in  " + (System.nanoTime() - t1)/1e6 + "  ms" );

    }
//    Almost-rght angled triangles : 37347
//    Completed in  360.858535  ms


//    low =  math.floor( sqrt( (Sq1[i]-1)/2) )
//            if i%10000 == 0 :
//            # print(str(i)+'.       high   =  ', i, high ,  '  Sq1 = ' ,Sq1[i] , '    c_sq+1=', Sq1[i] ,'  high=' ,Sq[high] ,'    low=', low , '   time = ', round((time.time()-t1 ),2),' s' )

//            for j in range(high, low, -1) :
//            # print( Sq[j]  )
//    b_sq = Sq[j]
//            if is_square( (Sq1[i] - b_sq) ) :
//    a = int(sqrt(Sq1[i] - b_sq))
//    b = int(sqrt(b_sq))
//    c = int(sqrt(Sq1[i]-1 ))
//            if a+b+c <= lim :
//    cnt+=1
//    print(str(cnt)+'.      a = ', a, '    b=' ,  b, '     c=',c,'      perim=', a+b+c , '         c^2+1 = ', c )
//
//            return print('\nAnswer : \t', cnt +lim//2 - 1)



}
