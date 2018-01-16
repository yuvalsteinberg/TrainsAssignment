# Solution for the Trains assignment.

## General overview:
* Package `trains` includes all classes that processes train system information, from the basic graph to answering 
queries on them.
	* `TrainSystem` is the manager and interface to query all information about the system. 
	* `Graph` represents the graph of direct routes (package visibility, as should not be exposed directly)
  	* `TripsProcessor` processes queries on the system and returns answers regarding trips in the system (package 
  	visibility, as should not be exposed directly)
  	* `Route` a bean to hold a single routes data (package visibility, as should not be exposed directly)
  	* `TripData` a bean to hold a single TripData data (public, as it is part of the response the `TrainSystem` returns)
  
* The `Tests.java` file is not under any package and holds the list of 10 defined tests. It initializes the Train-System
and runs some 'queries' against it.
	* **Note**: The graph string can either be received from the command line, or if no such present, from the hard coded 
	string (initialized as the original example).
	If taken from the command line, it is expected to be a single string, probably wrapped between double quotes 
	(for example: `java Tests "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7"`)
 