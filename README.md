# Java Data Structure
Try to implement Data Structure in official Java library

## Iterator
Some Abstract data structure use index to get item, although with different types of index.
For example, a List uses natural number as index, while a Map uses key as index.
On the other hand, certain Abstract data structure, like collection, do not have index to get item.

So, How to design a method for all Abstract class to get item?

The answer is method iterator() which returns an Iterator.

ListIterator: As List is indexed by nature number, 
ListIterator is designed to iter List from a specified number.


## Split Data Structure into collection.Collection and Map
Why?


## The Java collection.Collection Abstraction
![](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141508.png)
### Interface: Defined by Public Methods
- Collection
- List
- Set
### Abstract Class: Implementor Helper
### Concrete Class: Use Built-in Class to Implement Interface

## The Java Map Abstraction
![](https://obsidian-pictures-1306255178.cos.ap-beijing.myqcloud.com/20240322141615.png)
