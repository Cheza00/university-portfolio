import copy
import unittest
import testlib
import json
import random
from ddt import file_data, ddt, data, unpack

import program01 as program

@ddt
class Test(testlib.TestCase):

    def do_test(self, S, m, expected):
        '''Implementazione del test
            - fimm: il file in cui reperire le stringhe
            - expected: la risposta attesa
            TIMEOUT: 1 secondi per ciascun test
        '''
        with    self.ignored_function('builtins.print'), \
                self.ignored_function('pprint.pprint'), \
                self.timer(1):
            result   = program.es1(S,m)
        self.assertEqual(type(result),  int,     "il risultato prodotto deve essere un intero")
        self.assertEqual(result,        expected, "il valore restituito non e' corretto")
        return 1

    @file_data("test_01.json")
    def test_1_S1(self, stringa, m, expected):
        return self.do_test(stringa, m, expected)

    def test_tanti_zeri_1000(self):
        '''test con una stringa S contenente 1000 zeri e 250000 sequenze'''
        N        = 1000
        mezzo    = N//2
        zeri     = ['0']*(mezzo-1)
        S        =",".join(zeri + ['1']*2 + zeri)
        m        = 2
        expected = mezzo**2
        return self.do_test(S,m,expected)

    def test_tanti_1(self):
        '''test con una stringa S contenente 20000 1 e 19001 sequenze da m=1000'''
        N        = 20000
        S        =",".join(['1']*N)
        m        = 1000
        expected = N-m+1
        return self.do_test(S,m,expected)

    def test_tanti_zeri_interni(self):
        '''test con una stringa S contenente 10000 zeri interni e 1 sequenza'''
        N        = 1000
        zeri     = ['0']*N
        S        =",".join(['1'] + zeri + ['1'])
        m        = 2
        expected = 1
        return self.do_test(S,m,expected)
    
if __name__ == '__main__':
    Test.main()


