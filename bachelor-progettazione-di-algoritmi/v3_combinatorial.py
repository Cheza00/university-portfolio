def paths_comb(n, k):
    if k == 0: 
        return 0
    if k == 1:
        return 2
    if k > 2*n-3:
        k = 2*n-3
    fact = [1 for _ in range(n+1)]
    # posso partire da 2 perché 0! = 1 e 1! = 1
    for x in range(2, n+1):
        fact[x] = fact[x - 1] * x
    perm = 0
    for i in range(1,k+1):
        perm += int(2 * fact[n-2] / (fact[i//2]*fact[n-2-i//2]) * 
                    (fact[n-2] / (fact[(i-1)//2]*fact[n-2-(i-1)//2])))
    return perm
