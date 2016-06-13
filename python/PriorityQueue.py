class PriorityQueue(object):
    def __init__(self, max_size, compare=None):
        self.array = []
        self.compare = compare if compare is not None else lambda x, y: x <= y
        self.max_size = max_size if max_size is not None else 0
    
    def insert(self, a):
        self.array.append(a)
        k = len(self.array) - 1
        while k > 0:
            parent_index = (k - 1) // 2
            if self.compare(self.array[k], self.array[parent_index]):
                # swap nodes
                self.swap_nodes(parent_index, k)
            k = parent_index
            
    def swap_nodes(self, a, b):
        tmp_node = self.array[a]
        self.array[a] = self.array[b]
        self.array[b] = tmp_node
                
    def peak_head(self):
        if len(self.array) == 0:
            return None
        else:
            return self.array[0]
    
    def extract(self):
        if len(self.array) > 0:
            to_return = self.array.pop()
            self.array[0] = to_return
            k = 0
            while k < len(self.array):
                # Decide which child node to compare with parent
                # left child index: 2 * k + 1
                # right child index: 2 * k + 2
                
                compare_child_index = 2 * k + 2 \
                    if 2 * k + 2 < len(self.array) \
                        and self.compare(
                            self.array[2 * k + 2],
                            self.array[2 * k + 1]
                        ) \
                    else 2 * k + 1
                
                if (compare_child_index < len(self.array)
                    and self.compare(
                        self.array[compare_child_index],
                        self.array[k])):
                    # Decide whether or not a child node
                    # should switch with the parent
                    self.swap_nodes(compare_child_index, k)
                    k = compare_child_index
                else:
                    break
            
            return to_return
            
        
    def p_queue_insert(self, a):
        if (len(self.array) < self.max_size \
            or self.compare(self.peak_head(), a)):
            # If the priority queue is smaller than the 
            # maximum size or the element under consideration
            # belongs in the priority queue, insert it
            self.insert(a)
            if len(self.array) > self.max_size:
                self.extract()
        
    def get_all(self):
        return self.array
