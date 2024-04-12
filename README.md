# Mini Java Data Structure
[Referenced book in CS61B Course](https://inst.eecs.berkeley.edu/~cs61b/fa14/book2/data-structures.pdf)

This document mainly focuses on the design principles of the Java.util Official library class system.

For details of the code implementation, please refer to the source code in ./src.

First of all, data structures are basically divided into Collection and Map.


## The Java Collection Abstraction
![](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141508.png)
### Interface: Defined by Public Methods

Collection<E>: Store elements within type E, iter by iterator()
- size()
- contains(Object)
- add(E)
- remove(Object)
- iterator() -> Iterator

Iterator:
- hasNext()
- next()
- remove()

List extends Collection: Use Integer index
- get(Int)
- Add(Int, Object)
- remove(Int)
- set(Int, Object)
- listIterator(int index) -> ListIterator: Select a position to start iteration

ListIterator:
- hasPrevious()
- previous()
- set(Object)
- add(Object)

Set extends Collection: Same interface with Collection, but No duplicate element

> Q1: In Collection Interface, why .contains(Object), .add(E) and .remove(Object) 
> have different type of parameter?
> 
> A1: Because only in .add(E) method, we need to convert the input object to type E and store it, 
> for the other two methods, we only use .equals(Object) to compare 
> if the input object equals an element in the Collection.

> Q2: [Why there is not .add(Object) in Iterator Interface?](https://stackoverflow.com/questions/11196561/why-there-is-no-add-method-in-iterator-interface#:~:text=The%20sole%20purpose%20of%20an,the%20case%20of%20a%20HashSet%20)
>
> Opinion1: As the Iterator makes no guarantee about the order of iteration, the .add(Object) method
> has unclear semantics: The added Object's "position" is unknown(before current pos or after current pos).
> As the ListIterator guarantee the order of the iteration, we can provide the .add(Object) method
>
> Opinion2: The behavior of an .add(Object) method is not well-defined in all cases.
> And remove() method is more useful than .add(Object) method.
>
> Opinion3: .add(Object) method in the Iterator will have chance to write infinite
> loop. But I think this not make sense, because ListIterator provide .add(Object) method.

> Q3: Why there is not .set(Object) in Iterator Interface?
> 
> A3: It's not useful


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
![](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141615.png)
