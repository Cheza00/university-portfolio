def pathNum(n, k):
    if k == 0:
        return 0
    if k == 1: 
        return 2
    if k > 2*n-3:
        k = 2*n-3
    return pathsForK(n, 1, 0, k, 1) + pathsForK(n, 0, 1, k, 0)
        
        
def pathsForK(n, i, j, k, direction):
    if k == -1 or i >= n or j >= n:
        return 0
    if ((direction == 1 and j == n-1) or
        (direction == 0 and i == n-1)):
        return 1
    if direction == 1:
        return pathsForK(n, i + 1, j, k, direction) + pathsForK(n, i, j + 1, k - 1, 0)
    else:
        return pathsForK(n, i, j + 1, k, direction) + pathsForK(n, i + 1, j, k - 1, 1)
