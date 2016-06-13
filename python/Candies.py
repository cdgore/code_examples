class Candies(object):
    cache = {}
    
    def __init__(self, N, rating):
        self.N = N
        self.rating = rating
    
    def cand(self, i):
        if (i in self.cache):
            return self.cache[i]
        else:
            left_val = 0
            right_val = 0
            
            if (i - 1 < 0 or self.rating[i - 1] >= self.rating[i]):
                left_val = 1
            else:
                left_val = self.cand(i - 1) + 1
            
            if (i + 1 > self.N - 1 or self.rating[i + 1] >= self.rating[i]):
                right_val = 1
            else:
                right_val = self.cand(i + 1) + 1
            
            result = max(left_val, right_val)
            
            self.cache.update({
                i: result
            })
            
            return result

num_children = int(raw_input())
child_ratings = []

for x in range(num_children):
    child_ratings.append(int(raw_input()))

candies = Candies(
    num_children,
    child_ratings)

total_sum = 0

for i in range(num_children):
    total_sum += candies.cand(i)

print total_sum
