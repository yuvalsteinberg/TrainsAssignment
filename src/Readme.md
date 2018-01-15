# Solution for the Trains assignment.

## General overview:
* Package `trains` includes all classes that processes train system information, from the basic graph to answering queries on them.
	* `TrainSystem` is the manager and interface to query all information about the system. 
	* `Graph` represents the graph of direct routes (package visibility, as should not be exposed directly)
  	* `TripsProcessor` processes queries on the system and returns answers regarding trips in the system (package visibility, as should not be exposed directly)
  	* `Route` a bean to hold a single routes data (package visibility, as should not be exposed directly)
  	* `TripData` a bean to hold a single TripData data (public, as it is part of the response the `TrainSystem` returns)
  
* The `Tests.java` file is not under any package and holds the list of 10 defined tests. It initializes the Train-System and runs some 'queries' against it.
 