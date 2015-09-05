"""

Stable Marriage Algorithm
@author: Alvin Wan

This code is most likely not as efficient as it could be. Interesting exercise.
"""

def stable_pairing(boys, girls, verbose=False):
    """accepts two dictionaries mapping person ID to list of preferences

    >>> boys = { \
        1: ['A', 'B', 'C'], \
        2: ['B', 'A', 'C'], \
        3: ['A', 'B', 'C'] \
    }
    >>> girls = { \
        'A': [2, 1, 3], \
        'B': [1, 2, 3], \
        'C': [1, 2, 3] \
    }
    >>> stable_pairing(boys, girls)
    {(3, 'C'), (2, 'B'), (1, 'A')}
    """
    def shout(*args):
        if verbose:
            print(*args)

    class Person:
        """person in this universe"""

        def __init__(self, name, prefs):
            self.name = name
            self.prefs = prefs

        def __str__(self):
            return '<Person name=%s prefs=%s>' % (str(self.name), str(self.prefs))

        def __repr__(self):
            return str(self)

    class Girl(Person):
        """a girl"""
        candidate = None

        def __init__(self, name, prefs):
            self.string = []
            super().__init__(name, prefs)

        def decide(self):
            """decide who to accept or reject"""
            i, candidate = len(self.prefs), None
            for candidate in self.string:
                j = self.string.index(candidate)
                candidate = Boy.by_name(candidate)
                if j < i:
                    i = j
                    self.candidate = candidate
                else:
                    candidate.rejected_by(self)
            self.satisfied = len(self.string) == 1
            self.string = []

        @staticmethod
        def by_name(name):
            try:
                return girls[name]
            except KeyError:
                raise Exception('Uh oh: No such girl named %s' % name)

    class Boy(Person):
        """a boy"""

        def propose(self):
            """Propose to next best girl on list"""
            try:
                Girl.by_name(self.prefs[0]).string.append(self.name)
            except IndexError:
                raise Exception('Uh oh: %s ran out of girls to propose to.' % self)

        def rejected_by(self, girl):
            """Cross off girl on list"""
            girl.string.remove(self.name)
            self.prefs.remove(girl.name)

        @staticmethod
        def by_name(name):
            try:
                return boys[name]
            except KeyError:
                raise Exception('Uh oh: No such boy named %s in boys: %s' % (name, str(boys)))

    strings = {k: [] for k, v in girls.items()}
    boys = {name: Boy(name, prefs) for name, prefs in boys.items()}
    girls = {name: Girl(name, prefs) for name, prefs in girls.items()}

    finished, i = False, 0
    while not finished:
        if i > 5:
            break
        shout('='*30, 'Day %d' % i, '='*30)
        for boy in boys.values():
            boy.propose()
        for girl in girls.values():
            shout('Girl %s: %s' % (girl.name, girl.string))
            girl.decide()
            shout(' -- chose %s' % getattr(girl.candidate, 'name', None))
        i += 1
        finished = all(girl.satisfied for girl in girls.values())

    return {(girl.candidate.name, girl.name) for girl in girls.values()}


if __name__ == '__main__':
    boys = { \
        1: ['A', 'B', 'C'], \
        2: ['B', 'A', 'C'], \
        3: ['A', 'B', 'C'] \
    }
    girls = { \
        'A': [2, 1, 3], \
        'B': [1, 2, 3], \
        'C': [1, 2, 3] \
    }
    stable_pairing(boys, girls, verbose=True)
