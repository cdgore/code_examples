class MaxStocks(object):
    
    def naive_stocks(self, arr):
        ''' Given an array of stock prices, maximize profit
        buying exactly once and selling exactly once
    
        Runtime complexity: O(n^2)
        Space complexity: O(1)
    
        :param arr: array of stock prices
    
        :returns: max_profit
        '''
        return max([
            max([
                b - a
                for b
                in arr
                if b >= a
            ])
            for a
            in arr
        ])
        
    def dp_stocks(self, arr):
        ''' Given an array of stock prices, maximize profit
        buying exactly once and selling exactly once
    
        Runtime complexity: O(n)
        Space complexity: O(n)
    
        :param arr: array of stock prices
    
        :returns:
            (max_profit, buy_index, sell_index)
        '''
        def get_potential_max_sell(arr):
            '''
            [2, 3, 5, 4] =>
            [4, 5, 5, 5], [3, 2, 2, 2]
            ([5, 5, 5, 4], [2, 2, 2, 3])
            '''
            maxes = []
            indices = []
            cur_max = float('-inf')
            recent_max_index = -1
            for i, a in enumerate(arr[::-1]):
                if a > cur_max:
                    cur_max = a
                    recent_max_index = len(arr) - 1 - i
                indices.append(recent_max_index)
                maxes.append(cur_max)
            return (maxes[::-1], indices[::-1])
            
        maxes, max_indices = get_potential_max_sell(arr)
    
        min_index = -1
        max_index = -1
        max_total = float('-inf')
        for i, (a_i, max_sell, max_sell_i) in enumerate(zip(
                arr,
                maxes,
                max_indices)):
            if max_sell - a_i > max_total:
                max_total = max_sell - a_i
                min_index = i
                max_index = max_sell_i
    
        return (max_total, min_index, max_index)
    