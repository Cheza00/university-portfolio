import testlib
import isrecursive
from ddt import file_data, ddt, data, unpack
import sys

DEBUG = True
DEBUG = False

@ddt
class Test(testlib.TestCase):

    def do_test(self, name, expected):
        '''Implementazione del test
            - ftesto: il file in cui reperire la matrice e la lista delle parole
            - expected: la lista attesa
        '''
        fparole   = f"pezzi_{name}.txt"
        ftesto    = f"test_{name}.txt"
        fexpected = f"risTest_{name}.txt"
        if DEBUG:
            import program01 as program
            result   = program.es(fparole, ftesto)
            #result   = program.es(fparole)
        else:
            # prima controlliamo che l'implementazione sia ricorsiva
            try:
                import program01 as program
                isrecursive.decorate_module(program)
                program.es(fparole, ftesto)
            except isrecursive.RecursionDetectedError:
                pass
            else:
                raise Exception("Recursion not present")
            finally:
                isrecursive.undecorate_module(program)
                del program
                del sys.modules['program01']

            with    self.ignored_function('pprint.pprint'), \
                    self.forbidden_function('builtins.input'), \
                    self.check_open({fparole : ['r'], ftesto : ['w']}), \
                    self.check_imports( allowed=['program01','encodings.utf_8','encodings.utf_8_sig']), \
                    self.timeout(1), \
                    self.timer(1):
                import program01 as program
                result   = program.es(fparole, ftesto)
                #result   = program.es(fparole)
        self.assertIsInstance(    result, list,     "il risultato prodotto non e' una lista")
        for x in result:
            self.assertIsInstance(x,      int,      f"l'elemento {x} non è un intero")
        self.assertEqual(         result, expected, "la lista  restituita non e' corretta")
        self.check_text_file(ftesto, fexpected)

    @data(
            ('0',  [2, 1, 0]),
            ('1',  [12, 14, 10, 9, 7, 6, 2, 3, 13, 15, 1, 8, 11, 0, 5, 4]),
            ('2',  [7, 3, 5, 8, 6, 4, 0, 1, 2, 9]),
            ('3',  [10, 1, 5, 0, 8, 9, 2, 13, 12, 3, 7, 11, 6, 14, 4]),
            ('4',  [11, 12, 6, 3, 8, 10, 0, 14, 5, 9, 4, 15, 2, 1, 7, 13, 16]),
            ('5',  [3, 12, 9, 8, 2, 23, 1, 22, 16, 21, 13, 19, 14, 4, 18, 5, 6, 15, 10, 11, 0, 7, 20, 17]),
            ('6',  [15, 19, 11, 32, 8, 4, 39, 28, 29, 26, 38, 21, 18, 20, 6, 1, 35, 14, 7, 2, 37, 3, 24, 34, 0, 17, 13, 5, 27, 12, 9, 31, 10, 36, 25, 23, 30, 16, 22, 33]),
            ('7',  [17, 3, 8, 16, 4, 10, 6, 0, 14, 7, 18, 11, 5, 9, 15, 1, 12, 2, 13]),
            ('8',  [45, 21, 7, 33, 32, 2, 38, 41, 27, 49, 25, 10, 11, 9, 8, 5, 14, 26, 37, 18, 15, 42, 4, 44, 43, 40, 39, 48, 0, 6, 13, 29, 34, 24, 31, 35, 23, 20, 1, 22, 19, 50, 46, 3, 51, 17, 28, 36, 47, 12, 16, 30]),
            ('9',  [26, 50, 31, 23, 55, 39, 35, 10, 15, 58, 42, 25, 66, 38, 11, 47, 41, 24, 61, 19, 1, 52, 54, 0, 56, 44, 49, 4, 17, 21, 2, 33, 63, 43, 48, 14, 46, 62, 65, 29, 69, 37, 67, 40, 28, 5, 51, 34, 45, 68, 36, 12, 59, 9, 22, 27, 30, 60, 16, 3, 8, 6, 7, 13, 20, 32, 53, 57, 64, 18])
            )
    @unpack
    def test_json(self, name, expected):
        return self.do_test(name,expected)

if __name__ == '__main__':
    Test.main()



