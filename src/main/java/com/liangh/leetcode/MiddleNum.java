package com.liangh.leetcode;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/29 13:55
 */
public class MiddleNum {

    public static void main(String[] args) {

        MiddleNum middleNum = new MiddleNum();

        int [] nums1 = {};
        int [] nums2 = {1};

        double midValue = middleNum.findMedianSortedArrays(nums1, nums2);

        System.out.println("中位数：" + midValue);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;


        boolean doubleFlag =  (m + n ) % 2 == 0 ? true : false;

        // 中间数
        int mid;

        if(doubleFlag){
            mid = (m + n ) / 2;
        }else {
            mid = (m + n ) / 2 + 1;
        }

        if(m == 0){
            return getMid(nums2, 0, mid, doubleFlag);
        }else if(n == 0) {
            return getMid(nums1, 0, mid, doubleFlag);
        }

        int pm = 0;
        int pn = 0;

        int singleArrayFlag = 0;

        int i = 0;
        for (; i < mid - 1; i++) {
            if(nums1[pm] > nums2[pn] ){
                pn ++;
                if (pn == n){
                    singleArrayFlag = 1;
                    break;
                }
            }else {
                pm ++;
                if( pm == m){
                    singleArrayFlag = 2;
                    break;
                }
            }
        }

        double midValue;

        if(singleArrayFlag != 0){

            if(singleArrayFlag == 1){
                // 取第一个数组的中位数
                return getMid(nums1,  n, mid, doubleFlag);
            }else {
                // 取第二个数组的中位数
                return getMid(nums2, m, mid, doubleFlag);
            }
        }else {
            // 两个数组都没有遍历完
            if(doubleFlag){
                int [] a = new int [2];

                // 获取第一个中位数
                if(nums1[pm] > nums2[pn]){
                    a[0] = nums2[pn];
                    pn ++;
                }else {
                    a[0] = nums1[pm];
                    pm ++;
                }

                if( pm == m || pn == n){
                    if(pm == m){
                        // 第一个数组已经读完了
                        a[1] = nums2[pn];
                    }else {
                        // 第二个数组已经读完了，只能从第一个数组中取值
                        a[1] = nums1[pm];
                    }
                }else {
                    // 获取第二个中位数
                    if(nums1[pm] > nums2[pn]){
                        a[1] = nums2[pn];
                    }else {
                        a[1] = nums1[pm];
                    }
                }

                midValue = (0.0 + a[0] + a[1]) / 2;

            }else {
                if(nums1[pm] > nums2[pn]){
                    midValue = nums2[pn];
                }else {
                    midValue = nums1[pm];
                }
            }

        }
        return midValue;
    }

    private double getMid(int[] nums1,  int n, int mid, boolean doubleFlag) {

        double midValue;

        if(doubleFlag){
            midValue = (0.0 + nums1[(mid - n - 1)] + nums1[(mid - n )] ) / 2;
        }else {
            midValue = nums1[(mid - n - 1)];
        }
        return midValue;
    }

}
