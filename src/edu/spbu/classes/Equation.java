package edu.spbu.classes;

import java.util.HashMap;
import java.util.function.DoubleUnaryOperator;

/**
 * Created by Nicolas Karasov on 27.02.2017.
 */
public class Equation {
    private final Double eps;
    private final int N;
    private final String task;
    private final DoubleUnaryOperator f;
    private final DoubleUnaryOperator ff;
    private final Interval sec;
    private HashMap<Double, Double> list;

    public Equation(Double eps, int N, String task, DoubleUnaryOperator f, DoubleUnaryOperator curriedFunction, Interval sec) {
        this.eps = eps;
        this.N = N;
        this.task = task;
        this.f = f;
        // this.list = list;
        this.ff = curriedFunction;
        this.sec = sec;
    }

    public void solute() {
        System.out.println(task + "\n");
        list = sec.Tabulation(f, N);
        System.out.println("\n");

        System.out.println("                                   ~~~ Метод бисекций ~~~\n");
        for (int i = 0; i < list.size(); i++) {
            sec.bisectionMethod(f, eps);
        }

        System.out.println("                                   ~~~ Метод Ньютона ~~~\n");
        for (int i = 0; i < list.size(); i++) {
            sec.newtonMethod(f, ff, eps);
        }

        System.out.println("                                   ~~~ модифицированный Метод Ньютона ~~~\n");
        for (int i = 0; i < list.size(); i++) {
            sec.modernNewton(f, ff, eps);
        }

        System.out.println("                                   ~~~ Метод хорд ~~~\n");
        for (int i = 0; i < list.size(); i++) {
            sec.hordMethod(f, eps);
        }


    }
}
