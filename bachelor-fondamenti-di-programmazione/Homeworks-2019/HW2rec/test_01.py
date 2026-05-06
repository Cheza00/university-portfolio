import copy
import unittest
import testlib
import json
import random
from ddt import file_data, ddt, data, unpack

import program01 as program


# per riattivare le stampe e disattivare il timeout metti DEBUG=True
DEBUG=True
DEBUG=False

@ddt
class Test(testlib.TestCase):

    def do_test(self, lista,expected):
        '''Implementazione del test
            - fimm: il file in cui reperire le stringhe
            - expected: la risposta attesa
            TIMEOUT: 1.5 secondi per ciascun test
        '''
        if DEBUG:
            result   = program.es1(lista)
        else:
            with    self.ignored_function('builtins.print'), \
                    self.ignored_function('pprint.pprint'), \
                    self.forbidden_function('builtins.input'), \
                    self.timeout(1.5), \
                    self.timer(1.5):
                result   = program.es1(lista)
        self.assertEqual(type(result),  int,     "il risultato prodotto deve essere un intero")
        self.assertEqual(result,        expected, "il valore restituito non e' corretto")
        return 1

    @file_data("test_01.json")
    def test_json(self, sequenze, expected):
        return self.do_test(sequenze, expected)

#
#    def test_2000x200(self):
#        '''Secondo test con una lista ls2 di 2000 stringhe ciascuna di 200 interi. La risposta e' 1500.'''
#        #input('la lista vale '+ str( len(ls2))+'\n')
#        ls2 = crea(2000, 198, 1501)
#        return self.do_test(ls2,1500)
#
#    def test_2000x2000(self):
#        '''Secondo test con una lista ls2 di 2000 stringhe ciascuna di 2000 interi. La risposta e' 1500.'''
#        #input('la lista vale '+ str( len(ls2))+'\n')
#        ls2 = crea(2000, 1998, 1501)
#        return self.do_test(ls2,1500)
#
#    def test_5000x500(self):
#        '''Secondo test con una lista ls2 di 5000 stringhe ciascuna di 500 interi. La risposta e' 4500.'''
#        #input('la lista vale '+ str( len(ls2))+'\n')
#        return self.do_test(ls2,4500)

if __name__ == '__main__':
    Test.main()

