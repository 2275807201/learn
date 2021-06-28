package com.liangh.leetcode;


import java.awt.HeadlessException;
import java.util.List;
import javax.sound.midi.Soundbank;

class LinkAdd {

    public static void main(String[] args) {
        LinkAdd linkAdd = new LinkAdd();

        ListNode l1 = generateListNode(798124);
        System.out.println(l1);

        ListNode l2 = generateListNode(29718);
        System.out.println(l2);

        ListNode listNode = linkAdd.addTwoNumbers(l1, l2);
        // 10,009,998
        System.out.println(listNode);
    }

    private static ListNode generateListNode(int i) {

        ListNode head = new ListNode();
        ListNode l1 = head;
        while (i > 0){
            int t = i % 10 ;

            l1.val = t;

            i = i / 10;
            if( i == 0){
                // 除完了中断
                break;
            }

            // 创建一个新的节点
            ListNode l2 = new ListNode();
            l1.next = l2;
            l1 = l2;
        }

        return head;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        return compute(l1, l2, 0);

    }

    private ListNode compute(ListNode l1, ListNode l2, int i) {

        int sum = i;
        if(l1 != null){
            sum += l1.val;
        }

        if(l2 != null){
            sum += l2.val;
        }

        int g = sum % 10;
        int s = sum / 10;

        ListNode head = new ListNode();
        head.val = g;

        ListNode t1 = l1 == null ? null : l1.next;
        ListNode t2 = l2 == null ? null : l2.next;
        if( t1 != null || t2 != null || s > 0){
            ListNode high = compute(t1, t2, s);
            head.next = high;
        }

        return head;
    }


}

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

//    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);

        ListNode t = next;
        while(t != null){
            sb.append(t.val);
            t = t.next;
        }

        return sb.toString();
    }
}