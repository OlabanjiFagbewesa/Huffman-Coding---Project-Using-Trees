# Huffman-Coding---Project-Using-Trees
Project implementing a form of data compression using the tree data structure


Description:

The goal of this assignment is to implement a form of data compression. That is, given some data, we want to express the same information using less space. For this project, we will specifically focus on compressing text files, so we must first understand how computers represent text internally.

Recall that computers store data as a sequence of bytes. A byte consists of eight bits, and it represents a value between 0 and 255 inclusive. To represent English text, we need a way of assigning each English letter, punctuation symbol, special character, etc. to a sequence of eight bits (a value from 0 to 255). This mapping is provided by the ASCII encoding, which is shown in the table below. Notice that ASCII only uses 128 out of the 256 possible values that a byte can store.
