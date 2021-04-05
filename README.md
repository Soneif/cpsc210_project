# Grades Calculator

## User Stories

*As a student I want to be able to...*

- Add my grade(s)
- Remove grade(s)
- View all my grades
- Choose a class and check all my grades for said class
- Calculate the average in any of my classes
- Calculate my overall average
- Save my grades to file
- Load my grades from file


Phase 4: Task 2
Tested and designed GradesCalculator in the model package to be robust.

Phase 4: Task 3
- Change the design of UserInterface with InputPanel and OutputPanel
    - Increase cohesion
    - Reduce coupling
    - Improves readability and makes the program easier to update
    - *Both UserInterface and InputPanel have associations to OutputPanel, 
    while UserInterface has an association with InputPanel! This should have been avoided.*