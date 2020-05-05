package com.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class DoubleLinkedListNode {
        int key;
        int value;
        DoubleLinkedListNode next;
        DoubleLinkedListNode previous;

        public DoubleLinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            previous = null;
        }
    }

    // We maintain Map for fast searching whether the node exists
    // in the linked list
    Map<Integer, DoubleLinkedListNode> nodesMap;

    // We maintain DLL to keep track of recently used nodes
    // start will be the most recent and end will be the
    // least recent
    DoubleLinkedListNode start;
    DoubleLinkedListNode end;
    int sizeLimit;

    public LRUCache(int size) {
        sizeLimit = size;
        nodesMap = new HashMap<>(sizeLimit);
    }

    public void removeNode(DoubleLinkedListNode node) {
        try {
            // Check that node is not null
            if(node == null) return;

            if(start == node) {
                // Start node removal
                start = node.next;
                start.previous = null;
            } else if(end == node) {
                // End node removal
                end = node.previous;
                end.next = null;
            } else {
                // Intermediary node removal
                node.next.previous = node.previous;
                node.previous.next = node.next;
            }
        }finally {

        }
    }

    public void addToStart(DoubleLinkedListNode node) {
        try {
            node.next = start;
            node.previous = null;
            if(start != null) {
                start.previous = node;
            }

            start = node;

            if(end == null) {
                end = start;
            }
        } finally {

        }
    }

    // This is not required, this can be achieved using remove & add nodes
    public void moveEntryToStart(DoubleLinkedListNode node) {
        try {
            // Detach the node
            if(node == start) return;

            if(node == end) {
                end = end.previous;
                end.next = null;
            } else {
                // Detaching the node
                node.next.previous = node.previous;
                node.previous.next = node.next;

                // Update start node
                node.next = start;
                node.previous = null;
                start.previous = node;
                start = node;
            }

        } finally {

        }
    }

    public void putEntry(int key, int value) {
        try {
            // Validate key & value

            // Check if entry exists in hashMap
            if(nodesMap.containsKey(key)) {
                DoubleLinkedListNode doubleLinkedListNode = nodesMap.get(key);
                //moveEntryToStart(doubleLinkedListNode);

                removeNode(doubleLinkedListNode);
                addToStart(doubleLinkedListNode);
            } else {
                if(nodesMap.size() == sizeLimit) {
                    nodesMap.remove(end.key);
                    removeNode(end);
                }

                DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
                addToStart(node);
                nodesMap.put(node.key, node);
            }
        } finally {

        }
    }

    public void print() {
        System.out.println("------------------");
        DoubleLinkedListNode node = start;
        while (node != null) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);

        lruCache.putEntry(1, 1);
        lruCache.putEntry(2, 2);
        lruCache.putEntry(3, 3);
        lruCache.print();

        lruCache.putEntry(1, 1);
        lruCache.print();

        lruCache.putEntry(4, 4);
        lruCache.print();
    }
}
