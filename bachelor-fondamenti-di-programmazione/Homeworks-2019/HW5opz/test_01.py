import copy
import unittest
import testlib
import json
import random
from ddt import file_data, ddt, data, unpack

import program01 as program

DEBUG=True
DEBUG=False

@ddt
class Test(testlib.TestCase):

    def do_test(self, fimm, foto, expectedpng, expected):
        '''Implementazione del test
            - fimm: il file in cui reperire le specifiche sui posters
            - foto: il nome del file da produrre
            - expectedpng: il nome del file immagine corretto
            - expected: la risposta attesa
            TIMEOUT: 1.5 secondi per ciascun test
        '''
        if DEBUG:
            result = program.es1(fimm, foto)
        else:
            with    self.ignored_function('builtins.print'), \
                    self.ignored_function('pprint.pprint'), \
                    self.forbidden_function('builtins.input'), \
                    self.timeout(1.5), \
                    self.timer(1.5):
                result   = program.es1(fimm,foto)
        self.assertEqual(type(result),  int,     "il risultato prodotto deve essere un intero")
        self.assertEqual(result,        expected, "il valore restituito non e' corretto")
        self.check_img_file(foto, expectedpng)
        return 1

    #   test_ID  expected
    @data(('1',   1080),
          ('2',    672),
          ('5',    478),
          ('25',  1928),
          ('45',  2384),
          ('65',  2414),
          ('85',  2130),
          ('105', 1926),
          ('125', 2192),
          ('145', 1864),
          ('165', 1836),
          ('185', 1636),
          ('appiccicati', 324),
          ('coperti', 1480),
          )
    @unpack
    def test_data(self, ID, expected):
        file_txt     = f"rettangoli_{ID}.txt"
        file_png     = f"test_{ID}.png"
        expected_png = f"ris_{ID}.png"
        return self.do_test(file_txt, file_png, expected_png, expected)

if __name__ == '__main__':
    Test.main()

