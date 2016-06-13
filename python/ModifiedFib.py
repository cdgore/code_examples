class ModifiedFib(object):
  
    def __init__(self, a, b, n):
        self.a = a
        self.b = b
        self.n = n
        
    def run_fib(self):
        self.run_fib_accum(self.a, self.b, 3)

    def run_fib_accum(self, a_accum, b_accum, i):
        next_val = a_accum + b_accum ** 2
        
        if (i == self.n):
            # Base case
            print str(next_val)
        else:
            self.run_fib_accum(b_accum, next_val, i + 1)

input_string = raw_input().split(' ')

modified_fib = ModifiedFib(
    int(input_string[0]),
    int(input_string[1]),
    int(input_string[2]))

modified_fib.run_fib()
