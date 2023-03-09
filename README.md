# LRU-cache
The LRU Cache Implementation Project is a general-purpose cache implementation in Java language that applies the Least Recently Used (LRU) replacement policy. The cache uses a combination of a hash table and a doubly linked list to provide fast access to the data and efficient tracking of the least recently used item. Processing huge amount of data in very little time.

Data Structures Implemented:
------------------------------
- Hash Table
- Doubly Linked List

Features:
-------------------
- Fast access to data with O(1) time complexity for cache operations
- Efficient tracking of the least recently used item using LRU replacement policy
- Scalable solution for caching data with a customizable cache size


Results:
--------------

```
dataset: dataset-1000/data-1000.dat
requests: dataset-1000/requests-10000.dat
cache size: 100 items (10% of all items)
output:
---------------------------------------------------
Read 10000 items in 1403 ms
Stats: lookups 10000, hits 1030, hit-ratio 0.103000

***************************************************

dataset: dataset-5000/data-5000.dat
requests: dataset-5000/requests-100000.dat
cache size: 500 items (10% of all items)
output:
---------------------------------------------------
Read 100000 items in 36574 ms
Stats: lookups 100000, hits 10074, hit-ratio 0.100740
```

License:
--------------------
This project is licensed under the [MIT](LICENCE.txt) license. 
