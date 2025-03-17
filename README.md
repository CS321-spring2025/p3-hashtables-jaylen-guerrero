# Project #: Project Name

* Author: Jaylen Guerrero
* Class: CS321 Section 002
* Semester: 


## Overview

This program will create a linear probing and double hashing hashtable and add objects to the tables and show the results and stats of each table and compare them.

## Reflection


This project overall didn't go too bad. There were a couple things that gave me issues. I think I also made it a little bit harder on myself
by creating the delete method which wasn't needed and trying to test that. But other than making things harder for myself I thought it went good. 
The hardest thing for me was probably making the experiment class. I struggled with reading the google doc and finding how many elements were to be added
to the tables. Before I found that it should only be m * loadFactor, I had it capped at 200,000 or something close and having that would crash my program and I wasn't sure why.
Once I set a proper limit on the amount of elements to be added, everything worked and I passed the tests.
I thought this was a great project becasue there were no template files or interfaces so we were kind of left on our own to create everything from scratch.
I thought it was a good challenge and help me start from nothing and create something cool. There were a lot of times I had to go back to the instructions 
to find out exactly what was wanted. I hope I did everything correct in terms of the abstractions and which methods to include and leave out. Once I read the
instructions over and over I think I got a good grip on what was wanted.

## Compiling and Using

This section should tell the user how to compile your code.  It is
also appropriate to instruct the user how to use your code. Does your
program require user input? If so, what does your user need to know
about it to use it as quickly as possible?

To run this program you will have to compile all the java files using javac *.java. Then you can run 
java HashtableExperiment. And that will show you the usage and what each argument means. We have data source, load factor, and 
an optional debug level as arguments.

## Results 

Here are the stats of three tables: ALL USING .75 load factor
DATA SOURCE 1
HashtableExperiment: Found a twin prime for table capacity: 95791
        Using Linear Probing
HashtableExperiment: size of hash table is 95791
        Inserted 71844 elements, of which 2 were duplicates
        Avg. no. of probes = 2.5

        Using Double Hashing
HashtableExperiment: size of hash table is 95791
        Inserted 71844 elements, of which 2 were duplicates
        Avg. no. of probes = 1.86

DATA SOURCE 2

HashtableExperiment: Found a twin prime for table capacity: 95791
        Using Linear Probing
HashtableExperiment: size of hash table is 95791
        Inserted 71844 elements, of which 0 were duplicates
        Avg. no. of probes = 1.69

        Using Double Hashing
HashtableExperiment: size of hash table is 95791
        Inserted 71844 elements, of which 0 were duplicates
        Avg. no. of probes = 2.23

DATA SOURCE 3

HashtableExperiment: Found a twin prime for table capacity: 95791
        Using Linear Probing
HashtableExperiment: size of hash table is 95791
        Inserted 1972095 elements, of which 1900251 were duplicates
        Avg. no. of probes = .18

        Using Double Hashing
HashtableExperiment: size of hash table is 95791
        Inserted 1972095 elements, of which 1900251 were duplicates
        Avg. no. of probes = .07

The reason for the probes being <1 on the word list is because probes aren't counted on duplicates and there are so many duplicates in the word list it skews the avg probes.

## Sources used

The sources I used were the book and then just the lecture videos. I did use chatgpt for the twin prime generator becasue we didn't have to create that from scratch. There were a couple
things I had to change in the twin prime generator but other than that I just used the lectures and notes for what a hash table is suppossed to do and behave.

----------

