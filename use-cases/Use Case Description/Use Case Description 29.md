# Use Case Description 29

## Use Case: Display the population of people living and not living in the cities in a country. 

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to view the total population of a country so that I can effectively analyze the total population within the country.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables. 

### Success End Condition
A report is available for archivist to view the total population of a country.

### Failed End Condition
No report is generated. 

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for the total population of a country.

## Main Success Scenario
1. Extract a country and its population from the world database.
2. Use sum function to calculate the total population of a country, total city population of a country, total non-city population of a country.
3. Print the total population of a country, total city population of a country, total non-city population of a country.

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: July 21, 2023 (Release v0.1.0.3)

