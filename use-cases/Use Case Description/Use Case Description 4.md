# Use Case Description 4

## Use Case: Display the top 'N' populated countries in the world by largest population to smallest. 

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to see the list of the top 'N' populated countries in the world, so that I can analyze the world population distribution.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables. 

### Success End Condition
A report is available for archivist to view the top 'N' populated countries in the world. 

### Failed End Condition
Show the error message "Failed to get country details". 

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for the top 'N' populated countries information in the world.

## Main Success Scenario
1. Input 'N' value where 'N' is provided by the user. 
2. Extract the top 'N' populated countries in the world from the world database. 
2. Print the top 'N' populated countries in the world by largest population to smallest.  

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: July 14, 2023 (Release v0.1.0.2)

