# CZ3005_Assignment_1

1.	Program Architecture

The Programming Language used for this assignment is Java. Some object-oriented principles such as Classes, Polymorphism and Encapsulation has been used. These are the classes that were defined:

•	Node:
o	Contains it coordinates, number and a list of neighbours
•	Edge:
o	Contains the 2 connecting Node objects, the distance and the cost
•	EdgeKey:
o	A Helper object that contains the Integer values of both the Node objects in an Edge (used in the Graph object, for easy retrieval of Edge object)
•	Graph:
o	contains a HashMap<NodeNumber, Node> 
o	contains a HashMap<EdgeKey, Edge> 
•	Path:
o	Contains a List of Node objects in the path
o	The current distance, energy and cost (for heuristic function)


There are a total of 3 tasks performed:

•	Task 1: ucsUnrestricted search
•	Task 2: ucsRestricted search
•	Task 3: a* search

The details of the approaches and its output are elaborated in section 2 of this report. 

Run the Main.java file to check for the outputs.
