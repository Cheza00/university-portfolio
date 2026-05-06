'''Nikita, a famous spy, needs to follow a series of clues (namely a
sequence of words) that will lead her to discover one or more secret
information (sequences of words).  To find the secret(s) Nikita has to
visit different cities: in each city Nikita will find which is the
next city she has to move to and will also get a new word of the
secret.  The next city you have to move to depends on the next clue.

It is a bit like a treasure hunt, in which Nikita explores a network
of cities, collecting information.

NOTE: From the same city, the same clue can lead to multiple
    alternative cities.  So the paths to explore could be multiple and
    the secrets more than one.

NOTE: if in a certain city the instruction corresponding to the next
    clue is missing, this means that the enemy spy network has
    captured and destroyed the information. This also means that the
    secret that led Nikita in that city cannot be completed anymore.
    Thus, our spy goes on, trying to complete all the other secrets
    she can still reconstruct.

We want to reconstruct all the secrets that Nikita could discover,
given the available clues and the pieces of instructions scattered
throughout the different cities.

The instructions about how to move between the cities are contained in
a text file, according to the following format:
- every line starting with the '#' character should be ignored
- cities are always written in UPPERCASE
- the clues and words of the secret(s) to be discovered are always
  written in lowercase letters

The file contains, separated by at least one space/tab/return, zero or
more instructions to follow.  Each instruction is written as the
concatenation of four words:
    - city 	  (UPPERCASE word)
    - clue 	  (lowercase word)
    - destination (UPPERCASE word)
    - secret 	  (lowercase word)

Example:
    the instruction ROMAcarciofoPARIGIchampagne indicates that
        - when the spy is in 'ROME'.
        - if the following clue is 'carciofo'
        - the spy must go to 'PARIGI'.
        - and add the word 'champagne' to the secret

NOTE: You can assume that the each line of instruction is unique.
NOTE: there may be different instructions that even starting from the
      same city AND having the same clue, lead to different cities and/or
      produce different secrets
    Example:
    ROMAcarciofoPARIGIchampagne
    ROMAcarciofoCANCANCANCUNchampagne
    ROMAcarciofoPARIGImitraglietta
    ROMAcarciofoCATANZAROcommissario

Design and implement the function ex1(instructions_file, initial_city, clues)
recursive or using recursive functions or methods, which receives as
arguments:
 - instructions_file: the name of a text file that contains
                      instructions to follow in each city
 - initial_city:      the name of the initial city from which Nikita
                      starts her journey (UPPERCASE word)

 - clues:             a list of clues (string of lowercase words separated
                      by spaces) 

that reconstruct all the possible secrets Nikita can discover and that
returns the set of ALL possible pairs (secret, CITY), where

- secret is one of the possible secrets Nikita can discover, namely a
         string obtained by the concatenation of the words collected,
         separated by space
- CITY   is the city reached by Nikita when she completed that secret.

Example:

If the file is 'example.txt', the starting city is 'ROMA' and the
clues are "la bocca sollevò dal fiero pasto", then all the possible
secret/city pairs will be:
     ('vendita diamanti rubati stanotte ad anversa', 'CANCUN')
     ('vendita cannoni mercato nero del cairo',      'CANCUN')
     ('furto di diamanti a buckingham palace',       'MILANO')
     ('mata hari ha sedotto ambasciatore zambia',    'MILANO')


NOTE: it is forbidden to import/use other libraries or open files
      except the one indicated

NOTE: The test system recognizes recursion ONLY if the recursive
      function/method is defined in the outermost level.  DO NOT
      define the recursive function within another function/method
      otherwise you will fail all the tests.

"""
