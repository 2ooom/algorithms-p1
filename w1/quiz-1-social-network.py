"""
Social network connectivity. Given a social network containing n members
and a log file containing m timestamps at which times pairs of members
formed friendships, design an algorithm to determine the earliest time
at which all members are connected (i.e., every member is a friend of a
friend of a friend ... of a friend). Assume that the log file is sorted
by timestamp and that friendship is an equivalence relation. The running
time of your algorithm should be `m log ⁡n` or better and use
extra space proportional to `n`.
"""
import unittest

class UnionFind:
    def __init__(self, n):
        super().__init__()
        self.items = [i for i in range(n)]
        self.sizes = [1 for i in range(n)]

    def _root(self, index):
        xs = self.items
        i = xs[index]
        while xs[i] != xs[xs[i]]:
            i = xs[i] = xs[xs[i]]
        return xs[i]

    def get_union_size(self, i):
        pi = self._root(i)
        return self.sizes[pi]

    def union(self, i, j):
        pi = self._root(i)
        pj = self._root(j)
        if pi == pj:
            return
        # Sizing
        if self.sizes[pi] < self.sizes[pj]:
            pi, pj = pj, pi
        
        self.sizes[pi] += self.sizes[pj]
        self.items[pj] = self.items[pi]

    def connected(self, i, j):
        return self._root(i) == self._root(j)

def get_earliest_connection_time(timestamps, n):
    union = UnionFind(n)
    for (t, p, q) in timestamps:
        union.union(p, q)
        if union.get_union_size(p) == n:
            return t
    return None

class UnionFindTests(unittest.TestCase):

    def test_initialized(self):
        n = 3
        union = UnionFind(n)
        self.assertListEqual([0, 1, 2], union.items)
        self.assertListEqual([1, 1, 1], union.sizes)
        for i in range(n):
            for j in range(n):
                is_connected = union.connected(i, j)
                self.assertEquals(is_connected, i == j)

    def test_union_direct(self):
        union = UnionFind(10)
        union.union(1, 5)
        self.assertTrue(union.connected(1, 5))
        self.assertTrue(union.connected(5, 1))
        self.assertEqual(union.get_union_size(1), 2)

    def test_union_indirect(self):
        union = UnionFind(10)
        union.union(1, 5)
        union.union(0, 4)
        union.union(4, 9)
        union.union(9, 1)
        self.assertTrue(union.connected(0, 4))
        self.assertTrue(union.connected(9, 5))
        self.assertTrue(union.connected(1, 4))
        self.assertEqual(union.get_union_size(0), 5)

    def test_union_full(self):
        n = 10
        union = UnionFind(n)
        union.union(5, 7)
        union.union(4, 0)
        union.union(4, 9)
        union.union(3, 0)
        union.union(1, 8)
        union.union(8, 7)
        union.union(6, 7)
        union.union(1, 2)
        union.union(9, 7)
        for i in range(n):
            self.assertEqual(union.get_union_size(i), n)

if __name__ == '__main__':
    unittest.main()