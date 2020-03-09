"""
Shuffling a linked list. Given a singly-linked list containing nnn items,
rearrange the items uniformly at random. Your algorithm should consume a
logarithmic (or constant) amount of extra memory and run in time proportional
to n log⁡n n in the worst case. 
"""

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
    pass

if __name__ == '__main__':
    head = Node(1, Node(2, Node(3, Node(4, Node(5)))))
    print(f"Before: {head}")
    shuffle(head)
    print(f"After : {head}")
