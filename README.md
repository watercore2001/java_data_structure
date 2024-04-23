# Mini Java Data Structure And Algorithm
[Referenced book in CS61B Course](https://inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf)

This document mainly focuses on the design principles of the Java.util Official library class system.

For details of the code implementation, please refer to the source code in ./src.

![Abstract](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141508.png)
![map](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141615.png)

## Interface: Defined by Public Methods

Collection<E>: Store elements within type E, iter by iterator()

List<E> extends Collection<E>: Use Integer index

ListIterator<E>

Set<E> extends Collection<E>: Same interface with Collection, but No duplicate element

SortedSet<E> extends Set<E>

Comparator<E>

Map<K, V>

SortedMap<K, V>

## Abstract Class: Implementor Abstract

AbstractCollection<E>

AbstractList<E>

AbstractSequentialList<E>

AbstractSet<E>: Just same as AbstractCollection<E>

AbstractMap<K, V>

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

