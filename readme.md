# Extended Data Types
`edt` is an incredibly minimal and exclusively function library of a variety
of data types and structures that aren't included with Java by default. These
structures seek to optimize existing structures (or entirely re-invent them)
by appealing to more specific use cases and more general situations.

## Data Types
The following data types are included in `edt`:
- `DynamicArray` An ArrayList-like dynamic array that seeks to optimize
  performance at the cost of a higher memory usage. This class functions
  nearly identically to ArrayList from an outside perspective, but internally,
  this class more effectively optimizes memory allocation and indexing.
- `StaticArray` A static array - basically, an instance of the dynamic array
  structure defined above, but without the ability to add or remove items. This
  type can be converted to and from a dynamic array by utilizing their
  respective constructors. 
- `DynamicMap` A map design that utilizes linked dynamic arrays to optimize
  operation at the expense of an increased memory footprint. Linked dynamic
  arrays lead to `O` addition modifications, `O(n)` lookup operations, and
  `O(n^2)` deletion modifications.
- `TrioMap` A map-like structure that has three values instead of two. You
  can store three associated values together. This structure utilizes linked
  dynamic arrays, much like the dynamic map structure. Lookup and addition
  operations are similarly timed, as each array functions independently of the
  other two arrays.

## Performance
Each of the included data types and structures has a brief note about its
performance relative to alternatives. The intention of `edt` is to provide
extended data types and structures for situations where regular data types
and structures may work, but not as effectively as possible.