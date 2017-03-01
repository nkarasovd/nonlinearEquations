package edu.spbu.classes;

import static java.lang.Math.pow;

/**
 * Created by Nicolas Karasov on 27.02.2017.
 */
public class Main {
    public static void main (String args[]){
        System.out.println("Программа находит приближенные решения нелинейных алгебраических и трасцедентных уравнений,");
        System.out.println("с помощью следующих методов: сначала находятся отрезки, на которых есть корни (табуляция),");
        System.out.println("затем ищутся решения при помощи метода бисекций, метода ньютона и модифицированного метода ньютона, и метода хорд.");
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        Equation[] equations = new Equation[3];
        equations[0] = new Equation(10e-6,50,"Задание 1: найти корни уравнения y^4-16y^3+500y^2-8000y+32000 = 0" +
                " на отрезке [8,20] с точностью E = 10^-5, N=50",
                x -> pow(x,4) - 16*pow(x,3) + 500*pow(x,2) - 8000*x + 32000,
                x -> 4*(x*x*x - 12*x*x + 250*x - 2000), new Interval(8.0,20.0));

        equations[1] = new Equation(10e-7,20,"Задание 2: найти корни уравнения d^3-3r*d^2+4r^3*q=0, где r=10,q=0.90" +
                " на отрезке [0,2r] с точностью E=10^-6,N=20",
                x->pow(x,3) - 3*10*pow(x,2) + 4*pow(10,3)*0.90,
                x->3*pow(x,2) - 6*10*x, new Interval(0.0,20.0));

        equations[2] = new Equation(10e-8,100,"Задание 3: найти корни уравнения ln(x)+sin(x)+1/x=1 " +
                "на отрезке [0,10] с точностью E=10^-7,N=100",
                x->Math.log(x)+Math.sin(x)+1/x-1,
                x->(x-1)/(x*x) + Math.cos(x),new Interval(0.0,10.0));


        for(int i=0;i<equations.length;i++){
            equations[i].solute();
            System.out.println("\n\n\n\n\n");
        }

    }

    }
