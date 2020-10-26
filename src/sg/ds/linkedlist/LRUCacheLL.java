package sg.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheLL {
    static
    class Program {
        static class LRUCache {

            private Map<String, Node> cacheLookup = new HashMap<>();
            private LinkedListCache cache = new LinkedListCache();

            int maxSize;
            int size = 0;

            public LRUCache(int maxSize) {
                this.maxSize = maxSize > 1 ? maxSize : 1;
            }

            public void insertKeyValuePair(String key, int value) {
                Node node = cacheLookup.get(key);
                if (node != null) {
                   node.data = value;
                } else {
                    if (size == maxSize) {
                        Node lruData = cache.removeTail();
                        cacheLookup.remove(lruData.key);
                        size--;
                    }
                    node = new Node(key, value, null, null);
                    cache.setHead(node);
                    cacheLookup.put(key, node);
                    size++;
                }
            }

            public LRUResult getValueFromKey(String key) {
                Node node = cacheLookup.get(key);
                if (node != null) {
                    cache.setHead(node);
                    return new LRUResult(true, node.data);
                } else return new LRUResult(false, 0);
            }

            public String getMostRecentKey() {
                Node mostRecent = cache.getHead();
                if (mostRecent != null) return mostRecent.key;
                else return null;
            }
        }



        static class LRUResult {
            boolean found;
            int value;

            public LRUResult(boolean found, int value) {
                this.found = found;
                this.value = value;
            }
        }

        private static class Node {

            public Node(String key, int data, Node next, Node prev) {
                this.key = key;
                this.data = data;
                this.next = next;
                this.prev = prev;
            }

            String key;
            int data;
            Node next;
            Node prev;
        }

        private static class LinkedListCache {

            private Node head;
            private Node tail;

            public Node removeTail() {
                return remove(tail);
            }

            public Node getHead() {
                return head;
            }

            public void setHead(Node node) {
                //remove from existing position
                remove(node);
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    node.next = head;
                    if (node.next != null) {
                        node.next.prev = node;
                    }
                    head = node;
                }
            }

            private Node remove(Node node) {
                if (node == head) {
                    head = node.next;
                }
                if (node == tail) {
                    tail = node.prev;
                }
                if (node.prev != null) {
                    node.prev.next = node.next;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                node.next = node.prev = null;
                return node;
            }
        }
    } //end of program
}
