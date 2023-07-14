# Use Case Description 23

## Use Case: Display the world's population. 

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to view the list of population in each continent including the people who are living in cities and who are not, so that I can effectively analyze the people that are living in the city and their overall population at continent level.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables.

### Success End Condition
A report is available for archivist to view the population of people living in cities and people not living in cities in each continent. 

### Failed End Condition
Show the error message "Failed to get city details".

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for information on the population of people, people living in cities, and people not living in cities in each continent.

## Main Success Scenario
1. Extract all the continents from the world database.
2. Use sum function to calculate the total population of each continent, total city population of each continent, total non-city population of each continent. 
3. Print the total population of each continent, total city population of each continent, total non-city population of each continent.

## Extensions
None. 

## Sub-variations
None.

## Schedule
Due Date: July 21, 2023 (Release v0.1.0.3)	

