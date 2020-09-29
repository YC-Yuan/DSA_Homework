package hw3;

public class HeadLinkedList {

    public HeadLinkedList(Node node) {
        this.head = node;
    }

    public static void main(String[] args) {
        HeadLinkedList headLinkedList = new HeadLinkedList(new Node(1));
        headLinkedList.print();
        headLinkedList.remove(new Node(1));
        System.out.println("headLinkedList.size() = " + headLinkedList.size());
        headLinkedList.addNew(new Node(1));
        headLinkedList.addNew(new Node(2));
        headLinkedList.addNew(new Node(3));
        headLinkedList.addNew(new Node(4));
        System.out.println("headLinkedList.size() = " + headLinkedList.size());
        headLinkedList.print();
        headLinkedList.remove(new Node(2));
        headLinkedList.print();
        headLinkedList.remove(new Node(6));
    }

    public Node head;

    //返回元素个数
    public int size() {
        if (this.head == null) return 0;
        Node node = this.head;
        int size = 1;
        while (node.next != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    //按顺序打印所有节点的data
    public void print() {
        StringBuilder result = new StringBuilder();
        if (this.head != null) result.append(this.head.data);
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
            result.append(node.data);
        }
        if (result.toString().equals("")) System.out.println("Nothing to print with an empty List.");
        else System.out.println(result.toString());
    }

    public boolean contains(Node e) {
        if (this.head == null) return false;
        if (this.head.equals(e)) return true;
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
            if (node.equals(e)) return true;
        }
        return false;
    }

    public void addNew(Node e) {
        if (this.contains(e)) System.out.println(e.data + " already exist.");
        else if (this.head == null) {
            this.head = e;
        } else {
            Node node = this.head;
            while (node.next != null) node = node.next;
            node.next = e;
        }
    }

    public void remove(Node e) {
        //如果为空
        if (this.head == null) System.out.println(e.data + " doesn't exist.");
            //如果head就是要删除的元素
        else if (this.head.equals(e)) {
            if (this.head.next != null) this.head = this.head.next;
            else head = null;
        } else {
            //非head的处理
            Node node = this.head;
            while (node.next != null) {
                if (node.next.equals(e)) {
                    if (node.next.next == null) node.next = null;
                    else node.next = node.next.next;
                    return;
                }
                node = node.next;
            }
            System.out.println(e.data + " doesn't exist.");
        }
    }

    static class Node {
        public Object data;
        public Node next;

        public Node(Object e) {
            this.data = e;
        }

        public boolean equals(Node node) {
            return this.data.equals(node.data);
        }
    }
}
