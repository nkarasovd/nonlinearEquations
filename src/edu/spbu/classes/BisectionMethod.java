package edu.spbu.classes;


/**
 * Created by Nicolas Karasov on 24.02.2017.
 */
public class BisectionMethod {
    public static double f(double x){
        return Math.pow(x,2)-1;
    }

    public static void main(String[] args) {

        double epsilon=0.00001;
        double a=0;
        double b=1.1;
        double c = 0;
        int k=0;
        while ((b - a)> epsilon){
            c = (a + b) / 2;
            if(f(c)==0) break;
            if(f(c)*f(a) < 0)
                b = c;
            else
                a = c;
            k++;
        }
        System.out.println(" x* = "+(b+a)/2);
        System.out.println(" f(x) ="+f(c));
        System.out.println(k + " итераций");
    }
}
