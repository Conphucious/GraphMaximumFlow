# Graph Maximum Flow

![alt graph](https://raw.githubusercontent.com/Conphucious/GraphMaximumFlow/master/DependencyProject.png)

An exercise project to find the best path to maximum profits based on dependencies and expenses.

## How it works
- Every circle vector is a profit. 
- Every square vector is a cost.
- Every arrow coming from a shape vector is a dependency

Calculate the maximum profit and path. Similarly, if there is no profit (profit <=$0) then don't do any of the tasks at all.

## Solution Approach
I find every set of each possible subset of combinations, calculate each of their net total (profit - cost), and select the highest one.

If I have job A that makes $20 but has a cost of $10 and Job B that has a profit of $5 but has a cost of $2 then we have the set {{(20-10) + (5-2)}, {20-10}, {5-2}}. The most profitable thing to do would to do job A and B since the net for each set is {{13}, {10}, {3}}.
