/**
 * Created by hug.
 */

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

public class Experiments {
    public static void experiment1() {
        /*
            Insert 5000 random items into a BST. Make a plot of the avg depth of my BST.
         */
        BST<Double> tree = new BST();
        double randomNum;
        List<Double> yValues = new ArrayList<>();
        List<Double> xValues = new ArrayList<>();

        for(int i = 0; i < 5000; i++) {
            randomNum = Math.random() * 100000000;
            while(tree.contains(randomNum)) {
                randomNum = Math.random() * 100000000;
            }
            tree.add(randomNum);
            xValues.add((double)i);
            yValues.add(tree.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Average depth for random BST Tree by number of nodes", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
         /*
            Insert 5000 random items into a BST. Make a plot of the avg depth of my BST.
         */
        BST<Double> tree = new BST();
        double randomNum;
        List<Double> yValues = new ArrayList<>();
        List<Double> xValues = new ArrayList<>();

        List<Double> numbers = new ArrayList<>();
        //Initialize the tree with random numbers
        for(int i = 0; i < 5000; i++) {
            randomNum = Math.random() * 100000000;
            while(tree.contains(randomNum)) {
                randomNum = Math.random() * 100000000;
            }
            tree.add(randomNum);
            numbers.add(randomNum);
        }

        int randDel;
        for(int i = 0; i < 100000; i++) {
            //randomly delete item
            randDel = (int)(Math.random() * numbers.size());
            tree.deleteTakingSuccessor(numbers.get(randDel));
            numbers.remove(randDel);

            //randomly insert number
            randomNum = Math.random() * 100000000;
            while(tree.contains(randomNum)) {
                randomNum = Math.random() * 100000000;
            }
            tree.add(randomNum);
            numbers.add(randomNum);

            xValues.add((double)i);
            yValues.add(tree.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Average depth for random BST after 'x' number of successor deletion + random insertion", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
         /*
            Insert 5000 random items into a BST. Make a plot of the avg depth of my BST.
         */
        BST<Double> tree = new BST();
        double randomNum;
        List<Double> yValues = new ArrayList<>();
        List<Double> xValues = new ArrayList<>();

        List<Double> numbers = new ArrayList<>();
        //Initialize the tree with random numbers
        for(int i = 0; i < 5000; i++) {
            randomNum = Math.random() * 100000000;
            while(tree.contains(randomNum)) {
                randomNum = Math.random() * 100000000;
            }
            tree.add(randomNum);
            numbers.add(randomNum);
        }

        int randDel;
        for(int i = 0; i < 100000; i++) {
            //randomly delete item
            randDel = (int)(Math.random() * numbers.size());
            tree.deleteTakingRandom(numbers.get(randDel));
            numbers.remove(randDel);

            //randomly insert number
            randomNum = Math.random() * 100000000;
            while(tree.contains(randomNum)) {
                randomNum = Math.random() * 100000000;
            }
            tree.add(randomNum);
            numbers.add(randomNum);

            xValues.add((double)i);
            yValues.add(tree.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Average depth for random BST after 'x' number of successor deletion + random insertion", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment2();
    }
}
