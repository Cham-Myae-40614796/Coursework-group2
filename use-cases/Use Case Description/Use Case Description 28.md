# Use Case Description 28

## Use Case: Display the population of people living and not living in the cities in a region.

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to view the total population of a region so that I can effectively analyze the total population at the regional level.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables. 

### Success End Condition
A report is available for archivist to view the total population of a region.

### Failed End Condition
No report is generated. 

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for the total population of a region.

## Main Success Scenario
1. Extract a region and its population from the world database.
2. Use sum function to calculate the total population of a region, total city population of a region, total non-city population of a region.
3. Print the total population of a region, total city population of a region, total non-city population of a region.

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: July 21, 2023 (Release v0.1.0.3)

