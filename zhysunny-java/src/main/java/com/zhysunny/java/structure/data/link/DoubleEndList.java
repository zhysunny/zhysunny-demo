package com.zhysunny.java.structure.data.link;

/**
 * 双端链表
 * @author 章云
 * @date 2019/9/21 10:04
 */
public class DoubleEndList {

    private Link first;  //指向链表中的第一个链结点
    private Link last;   //指向链表中最后一个链结点

    public DoubleEndList() {
        first = null;
        last = null;
    }

    //插入到链表的前端
    public void insertFirst(Link link) {
        if (isEmpty()) {  //如果为空链表，则插入的第一个链结点既是表头也是表尾
            last = link;
        }
        link.next = first;
        first = link;
    }

    //插入到链表的末端
    public void insertLast(Link link) {
        if (isEmpty()) {  //如果为空链表，则插入的第一个链结点既是表头也是表尾
            first = link;
        } else {
            last.next = link;
        }
        last = link;
    }

    //删除第一个链结点，返回删除的链结点引用
    public Link deleteFirst() throws Exception {
        if (isEmpty()) {
            throw new Exception("链表为空！不能进行删除操作");
        }
        Link temp = first;
        if (first.next == null) {
            last = null;  //如果只有一个链结点，则删除后会影响到last指针
        }
        first = first.next;
        return temp;
    }

    //打印出所有的链表元素
    public void displayList() {
        Link cur = first;
        while (cur != null) {  //循环打印每个链结点
            cur.displayLink();
            cur = cur.next;
        }
    }

    //判空
    public boolean isEmpty() {
        return (first == null);
    }

    public static void main(String[] args) {
        DoubleEndList doubleEndList = new DoubleEndList();
        doubleEndList.insertFirst(new Link(1,"111"));
        doubleEndList.insertFirst(new Link(2,"222"));
        doubleEndList.insertLast(new Link(3,"333"));
        doubleEndList.insertLast(new Link(4,"444"));
        doubleEndList.displayList();
    }

}
