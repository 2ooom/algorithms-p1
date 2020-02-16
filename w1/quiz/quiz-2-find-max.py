"""
Union-find with specific canonical element. Add a method find() to the union-find
data type so that find(i) returns the largest element in the connected component
containing iii. The operations, union(), connected(), and find() should all take
logarithmic time or better.

For example, if one of the connected components is {1, 2, 6, 9} then the find()
method should return 9 for each of the four elements in the connected components.
"""
from UnionFind import UnionFind
import unittest

class UnionFindMax(UnionFind):
    def __init__(self, n):
        super().__init__(n)
        self.max = [1 for i in range(n)]

    def union(self, i, j):
        super.union(i, j)
        pi = self._root(i)
        pj = self._root(j)
        self.max[pi] = self.max[pj] = max(self.max[pi], self.max[pj])

    def find(self, i):
        pi = self._root(i)
        return self.max[pi]


class UnionFindMaxTests(unittest.TestCase):

    def test_initialized(self):
        n = 3
        union = UnionFindMax(n)
        for i in range(n):
            for j in range(n):
                self.assertEquals(union.find(i), i)

    def test_union_two(self):
        union = UnionFindMax(10)
        union.union(1, 5)
        self.assertEqual(union.find(1), 5)
        self.assertEqual(union.find(5), 5)

    def test_union_indirect(self):
        union = UnionFindMax(10)
        union.union(1, 5)
        union.union(0, 4)
        union.union(4, 9)
        union.union(9, 1)
        connected = [0, 1, 4, 5, 9]
        for i in connected:
            self.assertEquals(union.find(i), 9)


if __name__ == '__main__':
    unittest.main()