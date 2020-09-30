# DSA Homework3

袁逸聪 19302010020

## 3.6

采用环形链表便于删除，以size记录当前链表长度，便于使用M%size来减少后期的遍历次数。故每次遍历最多size次(由于size是均匀减小的，可以确定平均在size/2次)

```Java
public class Josephus {
    public static void main(String[] args) {
        int M=50;
        int N=80;
        CircularLinkedList list = new CircularLinkedList(N);

        Node currentNode=list.first;
        while(list.size>1){
            for(int i=0;i<M%list.size;i++){
                currentNode=currentNode.next;
            }
            list.remove(currentNode);
        }
        System.out.println("The remain node is no"+currentNode.no);
    }

    //实现单向环形链表
    static class Node {
        public Node next;
        public int no;

        public Node(int no) {
            this.no = no;
        }
    }

    static class CircularLinkedList {
        public Node first;
        public Node last;
        public int size = 0;

        public CircularLinkedList(int n) {
            for(int i=0;i<n;i++){
                this.add(new Node(i+1));
            }
        }

        //首尾相接的环形插入
        public void add(Node node) {
            if (size == 0) {
                size++;
                first = node;
                first.next = node;
                last = node;
            }
            else if (size > 0) {
                size++;
                last.next = node;
                last = node;
                node.next = first;
            }
        }

        //删除任意一个节点(没怎么太考虑head和tail的更新，因为这个问题里用不到)
        public void remove(Node node) {
            if (size == 0) System.out.println("The remain node is no"+node.no);
            else {
                int temp = node.next.no;
                node.next.no = node.no;
                node.no = temp;
                node.next = node.next.next;
                size--;
            }
        }
    }
}
```
运行时间：毫秒

|        | M=50000  |  M=100000  | M=200000  |
|  ----  |  ----  |  ----  |  ----  |
| N=50000  | 1075 | 1167 | 1272 |
| N=100000  | 5903 | 4200 | 4603 |
| N=200000  | 18796 | 23564 | 17021 |

可以看到因为M%size,M极大超过N时对运行时间没有明显增进。将复杂度从O(MN)变为O(N^2)

而在M小于N时，又变为O(MN),这里需要根据size的变化分段计算,size减小到M以下时又进入size^2级别

## 3.11

很直白的实现，说明下解构

Node只有data和next两个属性，因为要求单链表

重载equals用来判断重复，原本的equals会因为next不同而失效

HeadLinkedList则是由Node们组成的链表，实现了题目要求的方法

main方法的测试结果如下：

> 1<br>
> headLinkedList.size() = 0<br>
> headLinkedList.size() = 4<br>
> 1234<br>
> 134<br>
> 6 doesn't exist.

```Java
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
```

## 3.25a

多开辟一个stack用来记录最小值，称其为minStack

push时二者同同时push，minStack需要与前一项比较，如果新数更小push新数，否则重复push原数

由此保证每次peek时顶端都是最小的数

而pop时二者也同时pop，这样在主Stack的最小值pop时，minStack也不再有这个值(重复push只发生在首次push后)

```Java
public class MinimalStack {
    public static void main(String[]args){
        MinimalStack minimalStack = new MinimalStack();
        minimalStack.pop();
        minimalStack.push(2);
        minimalStack.push(1);
        minimalStack.push(3);
        minimalStack.push(4);
        System.out.println(minimalStack.findMin().toString());
        minimalStack.pop();
        System.out.println(minimalStack.findMin().toString());
    }

    public Stack<Integer> mainStack;
    public Stack<Integer> minStack;

    public MinimalStack() {
        this.mainStack = new Stack<>();
        this.minStack = new Stack<>();
    }


    public void push(Integer integer) {
        if (this.mainStack.empty()) {
            this.minStack.push(integer);
        } else {
            if (integer < this.minStack.peek()) this.minStack.push(integer);
            else this.minStack.push(this.minStack.peek());
        }
        this.mainStack.push(integer);
    }

    public Integer pop() {
        if (this.mainStack.empty()) {
            System.out.println("Empty stack");
            return null;
        } else {
            this.minStack.pop();
            return this.mainStack.pop();
        }
    }

    public Integer findMin() {
        if (this.mainStack.empty()) {
            System.out.println("Empty stack");
            return null;
        } else {
            return this.minStack.peek();
        }
    }
}
```

## 3.37

单链表向前插入：先向后插入在交换data就可以了(当前和后一个都能访问到)

单链表删除：先向后插入一个自己(仅复制data,插入过程中需要修改next)，再把原来的自己删掉