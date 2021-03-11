package com.hzw.study.arithmetic;

/**
 * @author huangzhiwei
 * @version 1.0
 * @description 解决leetcode中2号题，两数相加
 * @createTime 2021/3/11 11:29 AM
 */
public class LeetCode2 {

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  //正确的处理方式
  static class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

      ListNode head = new ListNode();

      ListNode node = head;
      int jinwei = 0;

      while (l1 != null || l2 != null || jinwei != 0){
        int val1 = l1 == null ? 0 : l1.val;
        int val2 = l2 == null ? 0 : l2.val;

        int curVal = val1 + val2 + jinwei;

        ListNode listNode = new ListNode(curVal % 10);

        //这里需要理解一下，将遍历的倒叙作为链表的头
        node.next = listNode;
        node = node.next;

        jinwei = curVal/10;

        //这里需要注意空指针的情况
        l1 = l1==null?null:l1.next;
        l2 = l2==null?null:l2.next;
      }

      return head.next;
    }

  }


  //错误的处理方式，链表太长[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]的情况下，long类型不够用
  static class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

      //将链表放到char组数中
      StringBuilder sb1 = new StringBuilder();
      toString(l1,sb1);
      long val1 = Long.valueOf(sb1.toString());

      StringBuilder sb2 = new StringBuilder();
      toString(l2,sb2);
      long val2 = Long.valueOf(sb2.toString());

      long sum = val1+val2;

      char[] chars = String.valueOf(sum).toCharArray();

      ListNode node = null;

      for (int i = 0; i < chars.length; i++) {
        char aChar = chars[i];
        ListNode itm = new ListNode();
        itm.val = Integer.valueOf(String.valueOf(aChar));

        ListNode mid = node;

        node = itm;
        node.next = mid;
      }


      return node;
    }

    //将链表处理为字符串
    private void toString(ListNode listNode,StringBuilder sb){
      if (listNode.next == null){
        sb.append(listNode.val);
      }else{
        toString(listNode.next,sb);
        sb.append(listNode.val);
      }
    }

  }

  public static void main(String[] args) {

    int[] arr1 = {2,4,3};
    int[] arr2 = {5,6,4};

    ListNode list1 = createList(arr1);
    ListNode list2 = createList(arr2);
    Solution1 solution = new Solution1();

    ListNode listNode = solution.addTwoNumbers(list1, list2);


  }

  //创建测试用的链表
  private static ListNode createList(int[] arr){
    ListNode node = null;
    for (int i = 0; i < arr.length; i++) {
      int i1 = arr[i];
      ListNode listNode = new ListNode();
      listNode.val = i1;
      ListNode next = node;
      node = listNode;
      node.next = next;

    }
    return node;
  }
}
