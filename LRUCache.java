class LRUCache {
    HashMap<Integer, Node> myMap;
    int capacity;
    Node head;
    Node tail;
    class Node{
        int key;
        int value;
        Node next, prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.myMap = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;        
    }
    
    public int get(int key) {
        if(myMap.containsKey(key)){
            Node currNode = myMap.get(key);
            deleteNode(currNode);
            addToTheHead(currNode);
            return myMap.get(key).value;
        }
        return -1;  
    }
    
    private void deleteNode(Node currNode){
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        currNode.next = null;
        currNode.prev = null;
    }

    private void addToTheHead(Node currNode){
        currNode.prev = head;
        currNode.next = head.next;
        currNode.next.prev = currNode;
        head.next = currNode;
    }

    public void put(int key, int value) {
        if(myMap.containsKey(key)){
            Node node = myMap.get(key);
            node.value = value;
            deleteNode(node);
            addToTheHead(node);
        }
        else
        {
            if(myMap.size() == capacity){
                Node lastNode = tail.prev;
                deleteNode(lastNode);
                myMap.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            myMap.put(key, newNode);
            addToTheHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */