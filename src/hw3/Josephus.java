package hw3;

public class Josephus {
    public static void main(String[] args) {
        int M=50000;
        int N=200000;
        CircularLinkedList list = new CircularLinkedList(N);

        long timeStart=System.currentTimeMillis();

        Node currentNode=list.first;
        while(list.size>1){
            for(int i=0;i<M%list.size;i++){
                currentNode=currentNode.next;
            }
            list.remove(currentNode);
        }

        long timeEnd=System.currentTimeMillis();

        System.out.println("Running time:"+(timeEnd-timeStart));

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
