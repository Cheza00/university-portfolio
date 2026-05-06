import copy
import unittest
import testlib
import json
import random
from ddt import file_data, ddt, data, unpack

import program01 as program

# scommentate la seconda riga per disattivare i timeout
DEBUG = False
#DEBUG = True

@ddt
class Test(testlib.TestCase):

    def do_test(self, file_txt, file_out, k, expected_file, expected):
        '''Implementazione del test
            - file_txt: il file in cui reperire le parole
            - file_out: il file di testo con le parole selezionate
            - k       : il numero di parole da mettere per riga
            - expected: il file che ci aspettiamo
            TIMEOUT: 1 secondo per ciascun test
        '''
        if DEBUG:
            result   = program.es(file_txt ,file_out, k)
        else:
            with    self.forbidden_function('builtins.input'), \
                    self.timeout(1), \
                    self.timer(1):
                result   = program.es(file_txt, file_out, k)
        self.assertEqual(type(result),  int,     "il risultato prodotto deve essere un intero")
        self.assertEqual(result,        expected, "la lista restituita non e' corretta")
        self.check_text_file( file_out, expected_file )
        return 1

    #    test_ID k  expected
    @data( ('1',        2,    7),
           #('2',        3,    4),
           ('3',        3,    8),
           ('4',        5,    9),
           ('5_10_500', 7,   18),
           ('strane',   4,   63),
           ('tante',   71, 3017),
           #('tante2',  87, 3372),
           ('tante3',  17,  696),
           ('9990',     2,    8),
           ('19961',    3,   12),
           ('29937',    7,   21),
           #('39876',    8,   19),
           #('100000',   8,    0),
           #('tutte',   10,  194),
           )
    @unpack
    def test_ID(self, ID, K, expected):
        filename      = f"parole_{ID}.txt"
        test_txt      = f"test_{ID}.txt"
        expected_file = f"ris_{ID}.txt"
        return self.do_test(filename, test_txt, K, expected_file, expected)

if __name__ == '__main__':
    Test.main()
