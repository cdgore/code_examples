class Task(object):
    def __init__(self, id, func, *args, **kwargs):
        self.id = id
        self.func = func
        self.args = args
        self.kwargs = kwargs
        self.status = 'Finished' if 'finished' in kwargs else 'New'
        self.percent = 0.0

    def run_func(self, *args, **kwargs):
        self.status = 'Running'
        return {
            'id': self.id,
            'result': self.func(*args, **kwargs)
        }
    
    def result(self):
        if self.status == 'Finished':
            return self.func()


class TaskScheduler(object):

    def __init__(self):
        from collections import OrderedDict
        from concurrent.futures import ThreadPoolExecutor
        
        self.tasks = OrderedDict({})
        self.task_queue = OrderedDict({})
        self.finished_tasks = OrderedDict({})
        self.executor = ThreadPoolExecutor(20)
        self.task_limit = 10
        self.queue_limit = 10
    
    def task_callback(self, task_future):
        task_result = task_future.result()
        task = Task(
            task_result.get('id', 0),
            lambda: task_result.get('result'))
        try:
            del self.tasks[task.id]
        except:
            pass
        self.finished_tasks.update({
            task.id: task
        })
        if len(self.task_queue) > 0:
            # task queue has tasks
            next_task = self.task_queue.popitem(last=False)[1] # FIFO
            # run next task from queue
            self.start_task(next_task)
    
    def start_task(self, task):
        if len(self.tasks) < self.task_limit:
            # run task
            future = self.executor \
                .submit(task.run_func, *task.args, **task.kwargs) \
                .add_done_callback(lambda t: self.task_callback(t))
            self.tasks.update({
                task.id: future
            })
        elif len(self.task_queue) < self.queue_limit:
            # queue task
            self.task_queue.update({
                task.id: task
            })
        else:
            # return busy
            return "no space on task queue"
    
    def get_task_status(self, task_id):
        if task_id in self.finished_tasks:
            return "Task is Finished"
        elif task_id in self.tasks:
            return "Task is Running"
        elif task_id in self.task_queue:
            return "Task is Queued"
        else:
            return "No such task: %s" % task_id
            
