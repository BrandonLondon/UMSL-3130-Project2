CS 3130 Design and Analysis of Algorithms Fall 2020
---------------------------------------------------------------------------------------------------------------
Programming Project #2 [100 points].

Due date: Wednesday, October 28.

# The purpose of the programming assignment is to perform empirical comparative analysis of several modifications of Quicksort for integer arrays.

# A. [80 points] Implement the following:

```
o basic version of Quicksort as discussed in our textbook;
o Quicksort with switching to Insertion sort when the number of elements in the subarray is
less than or equal to 2% of the original number;
o Quicksort with ‘median-of-three’ partitioning;
o Quicksort with randomly selected pivot value.
```
Requirements:

- functions from standard libraries implementing Quicksort are NOT allowed;
    however, you can use any code from any other sources with proper references;
- for obtaining the working time of each algorithm for a particular array, use the
    same function as for part (C) of Project #1; call this function before and after a call
    to the function implementing a sorting algorithm;
- run your functions for the following types of arrays containing 100 0 , 1000 0 and
    100 000 integers: random numbers, sorted list, almost sorted list, in which every
    10 th number is out of order (random number);
- the size of integers is supposed to be from 1 to 10000;
- submit: (1) source code with the results; (2) the analysis of your experiments;
- please, do NOT include original and sorted arrays in your output!
- your source code MUST include information on the name of the programmer and
    the purpose of the project, as well as some other comments.

# B. [20 points] Analysis of the experiments.

Requirements to the analysis:

- the text must be TYPED;
- your analysis must include theoretical information about the efficiency of Quicksort and
    how it may be improved using all approaches suggested in this project;
- experimental results must be clearly presented in the form of the table and graphs;
- I expect to see your conclusions on how well the experimental results correspond to the
    theory, and which version of Quicksort works better for specific types of input.
