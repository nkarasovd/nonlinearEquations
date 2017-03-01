package edu.spbu.classes;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

/**
 * Created by Nicolas Karasov on 26.02.2017.
 * Класс интервал - нахождение наших отрезков,
 * на которых возможны корни и методы поиска решений.
 * Храню полученные отрезки в HashMap.
 * Левый конец - ключ, правый - значение.
 */
public class Interval {
    /**
     * Концы отрезка и счетчик
     */
    private final Double a;
    private final Double b;
    private int rootCounter = 0;

    /**
     * Конструктор
     *
     * @param a
     * @param b
     */
    public Interval(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Выводит наш отрезок
     *
     * @param id
     * @return
     */
    public String getInterval(int id) {
        String s = "Отрезок " + id + " = " + "[" + a + "," + b + "]";
        return s;
    }

    /**
     * Ниже написана реализация процесса табуляции нашего промежутка
     *
     * @param f
     * @param m
     * @return
     */
    public HashMap<Double, Double> Tabulation(DoubleUnaryOperator f, int m) {
        System.out.println("Процесс отделения корней");
        HashMap<Double, Double> res = new HashMap<Double, Double>();
        int N = m;
        Double h = (b - a) / N;
        Double x1 = a, x2 = x1 + h, y1 = f.applyAsDouble(x1), y2;

        while (x2 <= b) {
            y2 = f.applyAsDouble(x2);

            if (y1 * y2 <= 0) {
                Interval tmp = new Interval(x1, x2);
                tmp.rootCounter++;
                rootCounter++;
                res.put(x1, x2);
            }
            x1 = x2;
            x2 = x1 + h;
            y1 = f.applyAsDouble(x1);
        }

        printInterval(res);
        return res;
    }

    /**
     * Метод, который выводит наши отрезки
     *
     * @param res
     */
    public void printInterval(HashMap<Double, Double> res) {
        // Получаем набор элементов
        Set<Map.Entry<Double, Double>> set = res.entrySet();

        // Отобразим набор
        for (Map.Entry<Double, Double> me : set) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
    }

    /**
     * Метод деления отрезка пополам
     *
     * @param f
     * @param eps
     * @return
     */
    public Double bisectionMethod(DoubleUnaryOperator f, Double eps) {
        Double res;
        Double e = eps;
        System.out.println("Уточнение корня,с точностью " + eps + " для отрезка [" + a + "," + b + "]");
        Double q = a;
        Double w = b;
        Double c;
        int count = 0;
        System.out.println("Начальное приближение корня: " + q);

        while (w - q > 2 * e) {
            c = (q + w) / 2;
            if (f.applyAsDouble(q) * f.applyAsDouble(c) <= 0) {
                w = c;
            } else {
                q = c;
            }
            count++;
        }

        res = (q + w) / 2;
        Double r = Math.abs(f.applyAsDouble(res));
        System.out.println("Приближенное решение: " + res
                + "\n" + "Количество шагов: " + count + "\n"
                + "Абсолютная величина невязки: " + r + "\n");


        return res;

    }

    /**
     * Метод Ньютона
     *
     * @param f
     * @param curriedFunction
     * @param eps
     * @return
     */
    public Double newtonMethod(DoubleUnaryOperator f, DoubleUnaryOperator curriedFunction, Double eps) {
        Double res; // наш корень
        Double e = eps; // точность, с которой мы считаем
        System.out.println("Уточнение корня,с точностью " + eps + " для отрезка [" + a + "," + b + "]");
        Double prev = a;
        Double next;
        int count = 0;
        System.out.println("Начальное приближение корня: " + a);

        if (curriedFunction.applyAsDouble(a) != 0) {
            next = prev - f.applyAsDouble(prev) / curriedFunction.applyAsDouble(prev);

            while (Math.abs(prev - next) > e) {
                prev = next;
                next = prev - f.applyAsDouble(prev) / curriedFunction.applyAsDouble(prev);
                count++;
            }

            res = next;

        } else res = a;

        Double r = Math.abs(f.applyAsDouble(res));
        System.out.println("Приближенное решение: " + res
                + "\n" + "Количество шагов: " + count + "\n"
                + "Абсолютная величина невязки: " + r + "\n");

        return res;
    }

    /**
     * Усовершенствованный метод Ньютона
     * Всегда делим на производную в начальной точке
     *
     * @param f
     * @param curriedFunction
     * @param eps
     * @return
     */
    public Double modernNewton(DoubleUnaryOperator f, DoubleUnaryOperator curriedFunction, Double eps) {
        Double res, e = eps;
        System.out.println("Уточнение корня,с точностью " + eps + " для отрезка [" + a + "," + b + "]");
        Double prev = a, next;
        int count = 0;
        System.out.println("Начальное приближение корня: " + a);

        if (curriedFunction.applyAsDouble(a) != 0.0) {
            next = prev - f.applyAsDouble(prev) / curriedFunction.applyAsDouble(a);

            while (Math.abs(next - prev) > e) {
                prev = next;
                next = prev - f.applyAsDouble(prev) / curriedFunction.applyAsDouble(a);
                count++;
            }

            res = next;

        } else res = a;

        Double r = Math.abs(f.applyAsDouble(res));
        System.out.println("Приближенное решение: " + res
                + "\n" + "Количество шагов: " + count + "\n"
                + "Абсолютная величина невязки: " + r + "\n");

        return res;
    }

    /**
     * Метод хорд
     *
     * @param f
     * @param eps
     * @return
     */
    public Double hordMethod(DoubleUnaryOperator f, Double eps) {
        Double res;
        Double e = eps;
        System.out.println("Уточнение корня,с точностью " + eps + " для отрезка [" + a + "," + b + "]");
        Double prev = a, middle = b, next;
        int count = 0;
        System.out.println("Начальное приближение корня: " + a);

        if (f.applyAsDouble(a) * f.applyAsDouble(b) < 0) {
            next = middle - f.applyAsDouble(middle) * (middle - prev) / (f.applyAsDouble(middle) - f.applyAsDouble(prev));
            while (Math.abs(next - middle) > e) {
                prev = middle;
                middle = next;
                next = middle - f.applyAsDouble(middle) * (middle - prev) / (f.applyAsDouble(middle) - f.applyAsDouble(prev));
                count++;
            }

            res = next;
        } else {
            System.out.println("На промежутке нет корня!");
            return null;
        }

        Double r = Math.abs(f.applyAsDouble(res));
        System.out.println("Приближенное решение: " + res
                + "\n" + "Количество шагов: " + count + "\n"
                + "Абсолютная величина невязки: " + r + "\n");

        return res;
    }

}