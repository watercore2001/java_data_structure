# Mini Java Data Structure And Algorithm
[Referenced book in CS61B Course](https://inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf)

This document mainly focuses on the design principles of the Java.util Official library class system.

For details of the code implementation, please refer to the source code in ./src.

![collection](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141508.png)
![map](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141615.png)

## Interface: Defined by Public Methods

Collection<E>: Store elements within type E, iter by iterator()

List<E> extends Collection<E>: Use Integer index

ListIterator<E>

Set<E> extends Collection<E>: Same interface with Collection, but No duplicate element

SortedSet<E> extends Set<E>

Comparator<E>

Map


```

### Abstract Class: Implementor Helper
AbstractCollection: 
- Use `iterator.hasNext()` and `iterator.next()` to implements `self.contains(Object)` method
- Use `iterator.remove()` to implement `self.remove(Object)`

AbstractList:
- For `Random Access` backend (such as array), implement fail-fast
- As Random Access List is better directly use index to access element,
- So we use self to implement listIterator(and fail-fast) in this class, wait real implementation for self.
- Use `self.get(Int)` to implement `listIterator.next()` and `listIterator.previous()`.
- Use `self.remove(Int)` to implement `listIterator.remove()`.
- Use `self.set(Int, E)` to implement `listIterator.set(E)`.
- Use `self.add(Int, E)` to implement `listIterator.add(E)`.

AbstractSequentialList:
- For `sequential access` backend (such as linked list), do not implement fail-fast
- As SequentialList must use listIterator to access element, 
- So we use listIterator to implement self in this class, wait real implementation for listIterator(and fail-fast).
- Use `listIterator.next()` to implement `self.get(Int)`
- Use `listIterator.remove()` to implement `self.remove(Int)`.
- Use `listIterator.set(E)` to implement `self.set(Int, E)`.
- Use `listIterator.add(E)` to implement `self.add(Int, E)`.

AbstractSet: 
- Just same as AbstractCollection


### Concrete Class: Use Built-in Class to Implement Interface

ArrayList extends AbstractList:
- Use a resizable-array to implement self methods

LinkedList extends AbstractSequentialList:
- Use a double-linked list
- implement customized ListIterator methods

Vector:
- Basically Same as LinkedList

Stack:
- Easy to implement

HashingSet:
- Using Hashing Map

## The Java Map Abstraction


### Interface: Defined by Public Methods

Map:
SortedMap:

