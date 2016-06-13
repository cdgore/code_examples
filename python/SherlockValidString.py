class Solution(object):
  def print_yes_or_no(self, char_array):
    char_counts = self.get_char_counts(char_array)
    
    count_counts = self.get_count_counts(char_counts)
    
    print_yes = self.del_one_or_fewer(count_counts)
    
    if print_yes:
      print 'YES'
    else:
      print 'NO'
  
  def get_char_counts(self, char_array):
    char_counts = {}
    
    for c in char_array:
      char_count = char_counts.get(c, 0)
      char_counts.update({c: char_count + 1})
    
    return char_counts
  
  def get_count_counts(self, char_counts):
    count_counts = {}
    
    for char, char_count in char_counts.items():
      char_count_count = count_counts.get(char_count, 0)
      count_counts.update({char_count: char_count_count + 1})
    
    return count_counts
  
  def del_one_or_fewer(self, count_counts):
    if len(count_counts) < 2:
      return True
    elif len(count_counts) == 2:
      at_least_one_one = False
      
      for k, v in count_counts.items():
        if v == 1:
          at_least_one_one = True
        
        if at_least_one_one:
          one_one_in_set = False
          min_count = 2 ^ 32
          max_count = 0
          
          for k, v in count_counts.items():
            if (k == 1 and v == 1):
              one_one_in_set = True
            
            if k < min_count:
              min_count = k
            
            if k > max_count:
              max_count = k
            
          if (one_one_in_set or ((max_count - 1 == min_count) and count_counts.get(max_count) == 1)):
            return True
          
    return False


solution = Solution()

input_string = raw_input()

solution.print_yes_or_no(input_string)
