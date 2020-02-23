"""
Queue with two stacks. Implement a queue with two stacks so that each queue
operations takes a constant amortized number of stack operations.
"""

class MyQueue:

    def __init__(self):
        super().__init__()
        self.s1 = []
        self.s2 = []

    def enqueue(self, x):
        self.s1.append(x)

    def dequeue(self):
        if not self.s2:
            while self.s1:
                x = self.s1.pop()
                self.s2.append(x)
        return self.s2.pop()

import unittest

class MyQueueTests(unittest.TestCase):

    def test_enqueue_dequeue(self):
        n = 10
        q = MyQueue()
        for i in range(n):
            q.enqueue(i)
        for i in range(n):
            self.assertEquals(i, q.dequeue())

    def test_enqueue_dequeue_immidiate(self):
        n = 10
        q = MyQueue()
        for i in range(n):
            q.enqueue(i)
            self.assertEquals(i, q.dequeue())

if __name__ == '__main__':
    unittest.main()