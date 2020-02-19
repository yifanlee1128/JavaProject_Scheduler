In Container:
Runnable and time are used to store the information of the container.
I create a field called _cancel, it is used to judge whether the scheduled task is canceled, if _cancel equals to "true", then the runnable will not be executed.

In obejectPool
There are three fields in the object pool:
firstObject and currentObject are used to realize the double linked list structure and avoid to use the existing data structure in java (as required by teh professor)
_supplier is used to initiate the object pool and supply new object when all the objects in the pool are used up

In Scheduler
I use double linked list structure to link the existing scheduled task, so there are two pointers as the fields of this class.