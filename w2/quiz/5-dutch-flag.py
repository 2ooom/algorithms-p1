"""
Dutch national flag. Given an array of nnn buckets, each containing a red, white,
or blue pebble, sort them by color. The allowed operations are:

    swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
    color(i): determine the color of the pebble in bucket i.

The performance requirements are as follows:

    At most n calls to color().
    At most n calls to swap().
    Constant extra space.
"""

def sort(xs):
    def swap(i, j):
        xs[i], xs[j] = xs[j], xs[i]

    def color(x):
        if x == 0:
            return 'red'
        if x == 1:
            return 'white'
        return 'blue'

    r = w = b = -1
    for i in range(len(xs)):
        c = color(xs[i])
        if c == 'red':
            r += 1
            w += 1
            b += 1
            if r != i:
                swap(r, i)
            if(r != w):
                swap(w, i)
        elif c == 'white':
            w += 1
            b += 1
            if w != i:
                swap(w, i)
        else: #blue
            b += 1
            if b != i:
                swap(b, i)


import unittest

class SortTests(unittest.TestCase):
    def test_3_colour_sort(self):
        xs = [1, 0, 2, 1, 2, 0, 1]
        sort(xs)
        self.assertSequenceEqual([0, 0, 1, 1, 1, 2, 2], xs)

    def test_empty(self):
        xs = []
        sort(xs)
        self.assertSequenceEqual([], xs)

    def test_one_colour(self):
        n = 5
        for i in range(3):
            xs = [i] * n
            expected = [i] * n
            sort(xs)
            self.assertSequenceEqual(expected, xs)

    def test_reb_blue(self):
        xs = [0, 0, 2, 0, 0, 2, 0, 2, 0, 2, 0]
        sort(xs)
        self.assertSequenceEqual([0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2], xs)

    def test_red_white(self):
        xs = [0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0]
        sort(xs)
        self.assertSequenceEqual([0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1], xs)

    def test_white_blue(self):
        xs = [2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 2]
        sort(xs)
        self.assertSequenceEqual([1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2], xs)

if __name__ == '__main__':
    unittest.main()