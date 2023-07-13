# Use Case Description 30

## Use Case: Display the total district's population.

## Characteristic Information

### Goal in Context
*As a global population archivist, I want to view the total population of a district so that I can effectively analyze the total population within the district.*

### Scope
Organization

### Level
Primary task

### Preconditions
Connect to the world database to get data from Country and City tables. 

### Success End Condition
A report is available for archivist to view the total population of a district.

### Failed End Condition
No report is generated. 

### Primary Actor
Global Population Archivist. 

### Trigger
The organization has asked for the total population of a district.

## Main Success Scenario
1. Extract all the districts and their population from the world database.
2. Use sum function to totalize the total population of each district.
3. Print the total population of each district. 

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: July 21, 2023 (Release v0.1.0.3)

