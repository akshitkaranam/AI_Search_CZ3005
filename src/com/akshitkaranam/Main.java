package com.akshitkaranam;

import com.akshitkaranam.Utilities.Graph;
import com.akshitkaranam.Utilities.Path;

import static com.akshitkaranam.Tasks.Misc.initialiseGraph;
import static com.akshitkaranam.Tasks.Task2.ucsRestricted;
import static com.akshitkaranam.Tasks.Task3.aStar;


public class Main {


    public static void main(String[] args) {
        Graph graph = initialiseGraph();

        System.out.println("Task 1: ");
        Path task1 = ucsRestricted(graph, 1, 50);
        System.out.println(task1);
        System.out.println();

        System.out.println("Task 2: ");
        Path task2 = ucsRestricted(graph, 1, 50);
        System.out.println(task2);
        System.out.println();


        System.out.println("Task 3: ");
        Path task3 = aStar(graph, 1, 50);
        System.out.println(task3);

    }


}
