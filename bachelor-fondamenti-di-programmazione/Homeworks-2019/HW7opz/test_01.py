
import testlib
import isrecursive
from ddt import file_data, ddt, data, unpack

import program01 as program

# scommentare la seconda riga per disabilitare timeout e abilitare print
DEBUG=True
DEBUG=False

@ddt
class Test(testlib.TestCase):

    def do_test(self, doRecTest, stringa, expected):
        '''Implementazione del test
            - doRecTest: esegui il test di ricorsione
            - stringa:   la stringa che codifica la sequenza
            - expected:  la risposta attesa
        '''
        # prima controlliamo che l'implementazione sia ricorsiva
        if doRecTest:
            try:
                isrecursive.decorate_module(program)
                program.es1(stringa)
            except isrecursive.RecursionDetectedError:
                pass
            else:
                raise Exception("Recursion not present")
            finally:
                isrecursive.undecorate_module(program)

        # poi calcoliamo il risultato
        if DEBUG:
            result   = program.es1(stringa)
        else:
            with    self.ignored_function('builtins.print'), \
                    self.ignored_function('pprint.pprint'), \
                    self.forbidden_function('builtins.input'), \
                    self.timeout(1), \
                    self.timer(1):
                result   = program.es1(stringa)

        # e controlliamo che sia corretto
        self.assertIsInstance(result,  set,      "il risultato prodotto deve essere un insieme")
        for x in result:
            self.assertIsInstance(x,   str,      "gli elementi dell'insieme devono essere stringhe")
        self.assertEqual(result,       expected, "l'insieme restituito non e' corretto")
        return 1

    @data(
        (False, ' '.join(['1']*1),                             { '1' } ),
        (False, ' '.join(['1']*2)+' 2',                         { '2' } ),
        (False, ' '.join(['1']*7),                             { '1' } ),
        (False, ' '.join(['1']*8)+' 2',                         { '2' } ),
        (True,  '2 2 1 2 3',                                   { '1 2 3', '2 1 3' } ),
        (False, '1 2 1 2 2',                                   { '2' } ),
        (True,  '1 2 1 2 1 2 3',                               { '1 2 3', '2 1 3'} ),
        (True,  '1 2 0 1 0 0 1',                               { '2 0 1', '2 1 0', '1 2 0'} ),   # esempio
        (True,  '3 3 2 3 2 3 2 3 1',                           { '3 2 1', '2 3 1'} ),
        (True,  '1 2 3 1 2 3 1 2 3',                           { '1 2 3', '1 3 2', '2 1 3', '2 3 1', '3 1 2', '3 2 1'} ),
        (True,  '1 2 3 2 2 1 1 3 3',                           { '1 2 3', '1 3 2', '2 1 3', '2 3 1', '3 2 1'} ),
        (True,  '1 1 2 2 3 3 1 2 3',                           { '1 2 3', '1 3 2', '2 3 1', '2 1 3', '3 1 2'} ),
        (True,  '1 2 1 2 1 2 1 2 1 2 3',                       { '1 2 3', '2 1 3'} ),
        (True,  '1 1 2 1 1 3 1 1 4 1 5',                       { '1 2 3 4 5', '2 1 3 4 5', '2 3 1 4 5', '2 3 4 1 5'} ),
        (True,  '1 2 3 4 7 5 7 6 7 8 9 8 8 6 9 5 9 4 3 2 1',   { '7 8 9', '7 9 8' } ),
        (True,  '1 2 1 3 1 4 1 5 1 6 1 7 1 8 1 9 10 9 10 9 10 1 8 1 7 1 6 1 5 1 4 1 3 1 2 1 11', {'9 10 11', '10 9 11'} ),
        (True,  '1 '*5 + '4 '*2 + '2 '*5 + '3 '*5 + '4 '*3 + '6',   { '1 4 2 3 6', '1 2 3 4 6'} ),
        (False, '1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 11 10 10 9 9 8 8 7 7 6 6 5 5 4 4 3 3 2 2 1 1', { '11' }),
    )
    @unpack
    def test_data(self, doRecTest, sequenza, expected):
        return self.do_test(doRecTest, sequenza, expected)

if __name__ == '__main__':
    Test.main()
