package edu.spbu.classes;

/**
 * Created by Nicolas Karasov on 24.02.2017.
 */
public class ModernNewton {

    static double f(double x) {
        return Math.pow(x, 2.0) - 1.0;
    }

    static double fprime(double x) {
        return 2 * Math.pow(x, 1.0);
    }

    public static void main(String argv[]) {

        double tolerance = .000000001; // Our approximation of zero
        int max_count = 200; // Maximum number of Newton's method iterations

/* x is our current guess. If no command line guess is given,
   we take 0 as our starting point. */

        double x = 1.0;
        double x0 = 1.0;
        if (argv.length == 1) {
            x = Double.valueOf(argv[0]).doubleValue();
        }

        for (int count = 1;
             (Math.abs(f(x)) > tolerance) && (count < max_count);
             count++) {
            x = x - f(x) / fprime(x0);
            System.out.println("Step: " + count + " x:" + x + " Value:" + f(x));
        }

        if (Math.abs(f(x)) <= tolerance) {
            System.out.println("Zero found at x=" + x);
        } else {
            System.out.println("Failed to find a zero");
        }
    }

}