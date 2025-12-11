# Non considero il caso in cui la tabella è di 1 x 1,
# in quanto la casella di partenza e quella di destinazione
# combaciano
def pathsCount_DP(n, k):
    if k == 0:
        return 0
    if k == 1: 
        return 2
    if k > 2*n-3:
        k = 2*n-3
    M = [[[[-1 for _ in range(2)]
            for _ in range(k)]
            for _ in range(n)]
            for _ in range(n)]
    return PathsForK_DP(n, 1, 0, k, 1, M) + PathsForK_DP(n, 0, 1, k, 0, M)
 

def PathsForK_DP(n, i, j, k, direction, M):
    if i >= n or j >= n:
        return 0
    if i == n-1 and j == n-1:
        return 1
    if (k == 0):
        if ((direction == 1 and j == n-1) or 
            (direction == 0 and i == n-1)):
            return 1
        else:
            return 0
    if M[i][j][k-1][direction] != -1:
        return M[i][j][k-1][direction]
    if direction == 1:
        M[i][j][k-1][direction] = (PathsForK_DP(n, i + 1, j, k, direction, M) + 
                                   PathsForK_DP(n, i, j + 1, k - 1, 0, M))
        return M[i][j][k-1][direction]
    else:
        M[i][j][k-1][direction] = (PathsForK_DP(n, i, j + 1, k, direction, M) +
                                   PathsForK_DP(n, i + 1, j, k - 1, 1, M))
        return M[i][j][k-1][direction]      
