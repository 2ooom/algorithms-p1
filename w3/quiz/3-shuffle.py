"""
Shuffling a linked list. Given a singly-linked list containing nnn items,
rearrange the items uniformly at random. Your algorithm should consume a
logarithmic (or constant) amount of extra memory and run in time proportional
to n log⁡n n in the worst case. 
"""
import random
import math
class Node:
    def __init__(self, value, next=None):
        self.value = value
        self.next = next

    def __str__(self):
        values = []
        head = self
        while head is not None:
            values.append(str(head.value))
            head = head.next
        return ", ".join(values)

def shuffle(head):
    if head is None:
        return
    n = 0
    prev = current = head
    while current is not None:
        prev = current
        current = current.next
        n += 1
    n_max = 2**math.ceil(math.log2(n))
    current = prev
    while n < n_max:
        current.next = Node(None)
        prev = current
        current = current.next
        n += 1
    shuffle_rec(head, current)
    prev = current = head
    while current is not None:
        while current.value is None and current.next is not None:
            current.value = current.next.value
            current.next = current.next.next
        if current.value is None:
            prev.next = None
        prev = current
        current = current.next

def shuffle_rec(left, right):
    if left is None or left == right:
        return
    """
    if left.next == right:
        if random.randint(0, 1):
            tmp = left.value
            left.value = right.value
            right.value = tmp
        return
    """
    current = currentX2 = left
    skip = False
    while currentX2.next != right:
        currentX2 = currentX2.next
        if not skip:
            current = current.next
        skip = not skip
    middle = current
    current = current.next
    shuffle_rec(left, middle)
    shuffle_rec(middle.next, right)
    while left != middle.next and current != right.next:
        if random.randint(0, 1):
            tmp = left.value
            left.value = current.value
            current.value = tmp
        left = left.next
        current = current.next

from collections import defaultdict

if __name__ == '__main__':
    head = Node('a', Node('b', Node('c', Node('d', Node('e')))))
    print(f"Before: {head}")
    shuffle(head)
    print(f"After : {head}")
    d = defaultdict(lambda :defaultdict(int))
    for i in range(50000):
        head = Node('a', Node('b', Node('c', Node('d', Node('e')))))
        shuffle(head)
        current = head
        i = 0
        while current is not None:
            d[current.value][i] += 1
            current = current.next
            i += 1
    for k, v in d.items():
        total = [vv for kk, vv in v.items()]
        print(k, sum(total), v)