"""
Counting inversions. An inversion in an array a[] is a pair of entries a[i]
and a[j] such that i<j but a[i]>a[j]. Given an array, design a linearithmic
algorithm to count the number of inversions.
"""
def count_inversions(xs):
    return count_inversions_internal(xs, [None]*len(xs), 0, len(xs) - 1)

def count_inversions_internal(xs, aux, l, r):
    if l == r:
        return 0
    m = int(l + (r - l)/2)
    res = count_inversions_internal(xs, aux, l, m)
    res += count_inversions_internal(xs, aux, m + 1, r)
    i = l
    j = m + 1
    for a in range(l, r + 1):
        if j > r or (i <= m and xs[i] <= xs[j]):
            aux[a] = xs[i]
            i += 1
        else:
            aux[a] = xs[j]
            res += j - a
            j += 1

    for a in range(l, r + 1):
        xs[a] = aux[a]
    return res

import unittest

class InversionsTests(unittest.TestCase):

    def test_single(self):
        self.assertEqual(0, count_inversions([1]))

    def test_two(self):
        self.assertEqual(0, count_inversions([1, 2]))
        self.assertEqual(1, count_inversions([2, 1]))
        self.assertEqual(0, count_inversions([2, 2]))

    def test_three(self):
        self.assertEqual(0, count_inversions([1, 2, 3]))
        self.assertEqual(0, count_inversions([1, 2, 2]))
        self.assertEqual(0, count_inversions([2, 2, 2]))
        self.assertEqual(1, count_inversions([1, 3, 2]))
        self.assertEqual(1, count_inversions([2, 1, 3]))
        self.assertEqual(2, count_inversions([2, 2, 1]))
        self.assertEqual(2, count_inversions([2, 3, 1]))
        self.assertEqual(2, count_inversions([3, 1, 2]))
        self.assertEqual(3, count_inversions([3, 2, 1]))

    def test_sorted(self):
        self.assertEqual(0, count_inversions([1, 2, 3, 4, 5, 6]))

    def test_full_inverted(self):
        self.assertEqual(5 + 4 + 3 + 2 + 1, count_inversions([6, 5, 4, 3, 2, 1]))

    def test_partially_inverted(self):
        self.assertEqual(4 + 3, count_inversions([6, 5, 2, 3, 4]))
        self.assertEqual(5 + 4 + 1 + 1 + 1, count_inversions([6, 5, 2, 3, 4, 1]))

if __name__ == '__main__':
    unittest.main()