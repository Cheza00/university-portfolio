# -*- coding: utf-8 -*-
"""
Created on Wed Jan  8 12:06:57 2020

@author: Utente
"""

from invest import invest

test_list = {(1000,10,2):1210,
             (5000,1.10,20):6222,
             (3500,0.9,30):4579}

if __name__ == '__main__':
    for test_case, result in test_list.items():
        val = invest(*test_case)
        if  val == result:
            print('Test case: {}\tOK'.format(test_case))
        else:
            print('Test case: {}\tNOK, risultato atteso {}, risultato fornito {}'.format(test_case, result, val))
    