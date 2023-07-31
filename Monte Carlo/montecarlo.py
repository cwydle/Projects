import random
import math
import time
import timeit
from multiprocessing import Pool
# You will need some import statements up here.

# Programming Assignment 5
#
# Student Name: Crystal Luong
#
# Do not delete this comment containing the assignment instructions.
#
# What to submit:
# (a) This montecarlo.py file
#     As always, you are not allowed to change the names of
#     py files I've given you, functions, parameters, etc.
# (b) A text file with the output from when you run your
#     generate_table and time functions.  One text file with
#     both output tables is fine.
#
# 1) Implement the function pi_monte_carlo to estimate
#    the value of pi using Monte Carlo simulation.
#    See the details of how to do this in Blackboard,
#    which shows the sum that you need to compute.
#    You will need to import the random module.  Take a
#    look at the documentation of the random module to find
#    the function that generates random floating-point
#    values in the interval [0.0, 1.0).
#
#    IMPORTANT NOTE: Several different Monte Carlo algorithms
#    exist for estimating pi. One of which is described by the
#    equation I have in Blackboard with a very specific summation.
#    It happens to be one of the better ones that exist, but it
#    is also not the one you would likely find if you attempted
#    to Google for this. If you implement a different Monte Carlo
#    algorithm for estimating pi other than the one specified in
#    the assignment, then you will lose all points related to this
#    part of the assignment.
#
# 2) Implement a parallel version of this in the function
#    pi_parallel_monte_carlo.The second parameter, processes,
#    indicates how many processes to use. You should use
#    a Pool (see the parallel examples for the import that you
#    will need). The easiest ways to do this is to either use
#    the apply_async method of the Pool class or the map method
#    of the Pool class.
#
#    Hint 1: If you use apply_async, you'll start by determining
#           how many samples per process, which you can compute
#           from n and p.  You would then call apply_async p times
#           to have p processes call pi_monte_carlo (the sequential
#           version) using the number of samples necessary to spread
#           the n samples across p processes. Once you call apply_async
#           p times (make sure you store the Future objects that those
#           calls return in a list), you'll call get() on each of those
#           Future objects, and average the p results.
#
#    Hint 2: If you want to use Pool.map, then start the same
#           way by determining how many samples to use for each
#           process. Create your Pool with p processes.  Generate
#           a list of length p where the elements are the numbers of
#           samples for each process, which should sum to n.
#           Call pool.map (assuming your Pool is named pool) to map
#           your sequential pi_monte_carlo to that list.
#           When pool.map returns, compute the average of the p
#           results and return it.
#
#    Hint 3: Make sure you use a with block for your Pool (see examples
#           in video and corresponding sourcecode) to ensure the Pool
#           is closed.
#
# 3) Implement the generate_table function as specified below.
#
# 4) Implement the time function as specified below.
#
# 5) Run your generate_table and time functions from the shell
#    and save the output to a textfile.
#
# 6) Are the results what you expected to see? If so, why?
#    If not, why do you think your results are different
#    then you expected? You can just answer in a comment.
#   The results are what I expected to see because the more processes that are used to run the code, the better the runtime.
#   It is expected that pi_monte_carlo(n) would have a slower runtime of 2~ seconds while the pi_parallel_monte_carlo() with 4
#   processes would have the better runtime for n = 12582912.
#   Also, as the processes increase, the runtime gets better. It is also expected that as n increases, the closer the
#   functions will produce a number close to pi. Looking at the table, it seems to be slightly true because even at n = 12582912
#   not all numbers are exactly 3.1415, which is pi. Some are close, but are not the same as pi. There are a few that are 3.1415.
#   Also it is expected that the pi_monte_carlo(n) and the pi_parallel_monte_carlo(n, p=4) of 1 process would be similar in runtime, which
#   is shown in the results. For n being smaller, the estimations are off but as n increases the estimations gets closer to pi. I think it is weird
#   that the pi_parallel_monte_carlo() with 1 process did slightly worse in regard with runtime than piMonteCarlo(n), but overall the more processes, the better
#   the runtime.
#
# 7) Submit the .py file and the textfile with the output.

def pi_monte_carlo(n) :
    """Computes and returns an estimation of pi
    using Monte Carlo simulation.

    Keyword arguments:
    n - The number of samples.
    """
    # Almost all module functions depend on the basic function random(), which generates a random float uniformly in the half-open range 0.0 <= X < 1.0.
    sumC = 0
    for i in range(n):
        U = random.random()
        sumC = sumC + math.sqrt(1 - U**2)
    return 4 * sumC / n


def pi_parallel_monte_carlo(n, p=4) :
    """Computes and returns an estimation of pi
    using a parallel Monte Carlo simulation.

    Keyword arguments:
    n - The total number of samples.
    p - The number of processes to use.
    """
    # You can distribute the work across p
    # processes by having each process
    # call the sequential version, where
    # those calls divide the n samples across
    # the p calls.
    # Once those calls return, simply average
    # the p partial results and return that average.
    #    Hint 1: If you use apply_async, you'll start by determining
    #           how many samples per process, which you can compute
    #           from n and p.  You would then call apply_async p times
    #           to have p processes call pi_monte_carlo (the sequential
    #           version) using the number of samples necessary to spread
    #           the n samples across p processes. Once you call apply_async
    #           p times (make sure you store the Future objects that those
    #           calls return in a list), you'll call get() on each of those
    #           Future objects, and average the p results.
    #
    #    Hint 2: If you want to use Pool.map, then start the same
    #           way by determining how many samples to use for each
    #           process. Create your Pool with p processes.  Generate
    #           a list of length p where the elements are the numbers of
    #           samples for each process, which should sum to n.
    #           Call pool.map (assuming your Pool is named pool) to map
    #           your sequential pi_monte_carlo to that list.
    #           When pool.map returns, compute the average of the p
    #           results and return it.
    i = 0
    samples = int(n/p)
    with Pool(p) as pool:
        output = pool.map(pi_monte_carlo, [samples]*p)
    for output in output:
        i += output
    return i/p


def generate_table() :
    """This function should generate and print a table
    of results to demonstrate that both versions
    compute increasingly accurate estimations of pi
    as n is increased.  It should use the following
    values of n = {12, 24, 48, ..., 12582912}. That is,
    the first value of n is 12, and then each subsequent
    n is 2 times the previous.  The reason for starting at 12
    is so that n is always divisible by 1, 2, 3, and 4.
    The first
    column should be n, the second column should
    be the result of calling piMonteCarlo(n), and you
    should then have 4 more columns for the parallel
    version, but with 1, 2, 3, and 4 processes in the Pool."""
    print('Pi estimations of piMonteCarlo() and pi_parallel_monte_carlo()')
    print("\tn \t\t\t piMonteCarlo() \t\t 1 Process \t\t 2 Processes \t\t 3 Processes \t\t\t 4 Processes")
    n = 12
    Max = 12582912
    with Pool() as pool:
        while n <= Max:
            estpi = pi_monte_carlo(n)
            estpi1 = pi_parallel_monte_carlo(n, 1)
            estpi2 = pi_parallel_monte_carlo(n, 2)
            estpi3 = pi_parallel_monte_carlo(n, 3)
            estpi4 = pi_parallel_monte_carlo(n, 4)
            print('{0:8}\t\t\t{1:.4f}\t\t\t\t{2:.4f}\t\t\t\t{3:.4f}\t\t\t\t{4:.4f}\t\t\t\t{5:.4f}'.format(n, estpi, estpi1, estpi2, estpi3, estpi4))
            n *= 2


def time() :
    """This function should generate a table of runtimes
    using timeit.  Use the same columns and values of
    n as in the generate_table() function.  When you use timeit
    for this, pass number=1 (because the high n values will be slow)."""
    print('Runtimes of piMonteCarlo() and pi_parallel_monte_carlo()')
    print("\tn \t\t\t piMonteCarlo() \t\t 1 Process \t\t 2 Processes \t\t 3 Processes \t\t\t 4 Processes")
    n = 12
    Max = 12582912
    with Pool(4) as pool :
        while n <= Max:
            t = timeit.timeit(lambda: pi_monte_carlo(n), number=1)
            t1 = timeit.timeit(lambda: pi_parallel_monte_carlo(n, 1), number=1)
            t2 = timeit.timeit(lambda: pi_parallel_monte_carlo(n, 2), number=1)
            t3 = timeit.timeit(lambda: pi_parallel_monte_carlo(n, 3), number=1)
            t4 = timeit.timeit(lambda: pi_parallel_monte_carlo(n, 4), number=1)
            print('{0:8}\t\t\t{1:.4f}\t\t\t\t{2:.4f}\t\t\t\t{3:.4f}\t\t\t\t{4:.4f}\t\t\t\t{5:.4f}'.format(n, t, t1, t2, t3, t4))
            n *= 2


if __name__ == "__main__":
    generate_table()
    time()

