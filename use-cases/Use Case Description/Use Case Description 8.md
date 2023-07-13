# Use Case Description 8

## Use Case: Display all the cities in a continent by largest population to smallest. 

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to view the sorted list of cities in a continent in descending order by their population size so that I can efficiently analyze the population distribution across cities within the continent.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables.

### Success End Condition
A report is available for archivist to view the population of each city in a continent. 

### Failed End Condition
Show the error message "Failed to get city details".

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for cities population information in a continent. 

## Main Success Scenario
1. Extract all the cities in a continent from the world database. 
2. Print all the cities in a continent by largest population to smallest. 

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: July 14, 2023 (Release v0.1.0.2)

