"""
Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n-1]
is sorted and the subarray a[n] to a[2*n-1] is sorted. How can you merge the
two subarrays so that a[0] to a[2*n-1] is sorted using an
auxiliary array of length n (instead of 2n)?
"""

def sort(xs):
    l = len(xs)
    n = int(l / 2)
    aux = []
    j = n
    for i in range(n):
        if xs[i] > xs[j]:
            aux.append(xs[i])
            xs[i] = xs[j]
            j += 1

    a = 0
    i = n
    while a < len(aux):
        if j >= l or aux[a] < xs[j]:
            xs[i] = aux[a]
            a += 1
        elif j < l:
            xs[i] = xs[j]
            j += 1
        i += 1

import unittest

class AuxilarySortTests(unittest.TestCase):

    def test_sort(self):
        xs = [1, 2, 3, 4, 5, 6]
        sort(xs)
        self.assertSequenceEqual([1, 2, 3, 4, 5, 6], xs)
        xs = [4, 5, 6, 1, 2, 3]
        sort(xs)
        self.assertSequenceEqual([1, 2, 3, 4, 5, 6], xs)
        xs = [1, 3, 7, 18, 3, 5, 6, 10]
        sort(xs)
        self.assertSequenceEqual([1, 3, 3, 5, 6, 7, 10, 18], xs)
        xs = [1, 3, 7, 9, 3, 5, 6, 10]
        sort(xs)
        self.assertSequenceEqual([1, 3, 3, 5, 6, 7, 9, 10], xs)

if __name__ == '__main__':
    unittest.main()