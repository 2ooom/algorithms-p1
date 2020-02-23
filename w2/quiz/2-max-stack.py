"""
Stack with max. Create a data structure that efficiently supports
the stack operations (push and pop) and also a return-the-maximum
operation. Assume the elements are real numbers so that you can
compare them.
"""

class MaxStack:
    def __init__(self):
        super().__init__()
        self.xs = []
        self.max_vals = []

    def max(self):
        if self.max_vals:
            return self.max_vals[-1]
        return None

    def push(self, x):
        self.xs.append(x)
        self.max_vals.append(max(x, self.max()) if self.max_vals else x)

    def pop(self):
        self.max_vals.pop()
        return self.xs.pop()


import unittest

class MaxStackTests(unittest.TestCase):
    def test_inc_push(self):
        n = 10
        s = MaxStack()
        for i in range(n):
            s.push(i)
            self.assertEquals(i, s.max())
        for i in range(n - 1):
            x = s.pop()
            self.assertEquals(x - 1, s.max())
        s.pop()
        self.assertIsNone(s.max())

    def test_dec_push(self):
        n = 10
        s = MaxStack()
        for i in range(n):
            s.push(n - i)
            self.assertEquals(n, s.max())
        for i in range(n - 1):
            s.pop()
            self.assertEquals(n, s.max())
        s.pop()
        self.assertIsNone(s.max())

if __name__ == '__main__':
    unittest.main()