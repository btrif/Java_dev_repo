package Algorithms;

//  Created by Bogdan Trif on 04-09-2017 , 11:08 PM.


public class Largest_Prime_Factor {

    public static int largestPrimeFactor(long number) {
        int i;

        for (i = 2; i <= number; i++) {
            if (number % i == 0) {
                number /= i;
                i--;
            }
        }

        return i;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(largestPrimeFactor(13195));
        System.out.println(largestPrimeFactor(600851475143L));
    }
}
