
from UnionFind import UnionFind

def get_earliest_connection_time(timestamps, n):
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
    union = UnionFind(n)
    for (t, p, q) in timestamps:
        union.union(p, q)
        if union.get_union_size(p) == n:
            return t
    return None