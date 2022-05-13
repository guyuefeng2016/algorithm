package com.fei;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * 在无序数组里面求第k大的数
 * @author: guyuefeng
 * @create: 2022-05-10 14:45
 **/
public class TopK {

    static Random random = new Random();

    /**
     * 快排方式
     * @param arr
     * @param k
     * @return
     */
    public static int getTopK(int[] arr, int k){
        if (arr == null || arr.length < k){
            return -1;
        }

        int index = random.nextInt(arr.length);
        return recursion(arr, 0, arr.length - 1, index, k-1);
    }

    public static int recursion(int[] arr, int left, int right, int index, int k){
        if (left == right){
            return arr[left];
        }
        int[] partion = partion(arr, left, right, index);

        int res = -1;
        if (k > partion[0] && k <= partion[1]){
            return arr[k];
        } else if (k <= partion[0]){
            res = recursion(arr, left, partion[0] , partion[0], k);
        } else if (k > partion[1]){
            res = recursion(arr, partion[1]+1 , right, right, k);
        }

        return res;
    }

    public static int[] partion(int[] arr, int left, int right, int index){
        int[] resArr = new int[2];

        swap(arr, index, right);

        int watch = arr[right];
        int l=left-1;
        int r=right;
        int i=left;

        while (i<r){
            if (arr[i] < watch){
                swap(arr, i++, ++l);
            } else if (arr[i] == watch){
                i++;
            } else {
                swap(arr, i, --r);
            }
        }

        swap(arr, r, right);

        resArr[0] = l;
        resArr[1] = r;
        return resArr;
    }

    /**
     * bfprt算法
     * @param arr
     * @param k
     * @return
     */
    public static int getTopKBfprt(int[] arr, int k){
        if (arr == null || arr.length < k){
            return -1;
        }

        wLen = arr.length;
        return recursionBfprt(arr, 0, arr.length - 1, k-1);
    }
    private static int wLen = 0;

    public static int recursionBfprt(int[] arr, int left, int right, int k){
        if (left == right){
            return arr[left];
        }

        int medium = getMedium(arr, left, right);

        int res = medium;
        if (arr.length == wLen) {
            int[] partion = partionBfprt(arr, left, right, medium);
            if (k >= partion[0] && k <= partion[1]) {
                return arr[k];
            } else if (k < partion[0]) {
                res = recursionBfprt(arr, left, partion[0] - 1, k);
            } else if (k > partion[1]) {
                res = recursionBfprt(arr, partion[1] + 1, right, k);
            }
        }

        return res;
    }


    public static int getMedium(int[] arr, int left, int right){
        int arrLen = right - left + 1;
        int mediumArrLen = arrLen / 5 + (arrLen % 5 == 0 ? 0 : 1);

        int[] mediumArr = new int[mediumArrLen];

        for (int i=0; i<mediumArrLen; i++){
            int start = left + i*5;
            int end = Math.min(start + 4, right);

            int narrLen = end - start + 1;
            int[] narr = new int[narrLen];
            int k=0;
            for (int j=start; j<=end; j++){
                narr[k++] = arr[j];
            }
            insertSort(narr);

            mediumArr[i] = narr[narrLen>>1];
        }

        return recursionBfprt(mediumArr, 0, mediumArrLen-1, mediumArrLen>>1);
    }

    public static void insertSort(int[] arr){
        if (arr.length == 0 || arr.length == 1){
            return;
        }
        for (int i=1,len=arr.length; i<len; i++){
            int j = i;
            while (j > 0 && arr[j] < arr[j-1]){
                swap(arr, j, j-1);
                j--;
            }
        }
    }


    public static int[] partionBfprt(int[] arr, int left, int right, int watch){
        int[] resArr = new int[2];

        int l=left-1;
        int r=right+1;
        int i=left;

        while (i<r){
            if (arr[i] < watch){
                swap(arr, i++, ++l);
            } else if (arr[i] == watch){
                i++;
            } else {
                swap(arr, i, --r);
            }
        }

        resArr[0] = l+1;
        resArr[1] = r-1;
        return resArr;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * bfprt算法
     * @param arr
     * @param k
     * @return
     */
    public static int getTopKBfprt2(int[] arr, int k){
        if (arr == null || arr.length < k){
            return -1;
        }

        return bfprt(arr, 0, arr.length - 1, k-1);
    }

    /**
     * The recursive procedure in BFPRT.
     *
     * @param arr The array from which select.
     * @param p   The start index of the sub-array from which select.
     * @param r   The end index of the sub-array from which select.
     * @param i   The order of the element to be selected.
     * @return The selected element.
     */
    protected static int bfprt(int[] arr, int p, int r, int i) {
        if (p == r) {
            return arr[p];
        }
        int pivot = medianOfMedians(arr, p, r);
        int[] pivotRange = partition(arr, p, r, pivot);
        if (p + i >= pivotRange[0] && p + i <= pivotRange[1]) {
            return arr[pivotRange[0]];
        } else if (p + i < pivotRange[0]) {
            return bfprt(arr, p, pivotRange[0] - 1, i);
        } else {
            return bfprt(arr, pivotRange[1] + 1, r, i + p - pivotRange[1] - 1);
        }
    }

    /**
     * Compute the median of the medians of the input array.
     *
     * @param arr The array to be computed.
     * @param p   The start index of the sub-array.
     * @param r   The end index of the sub-array.
     * @return The median of the medians of the sub-array.
     */
    protected static int medianOfMedians(int[] arr, int p, int r) {
        int num = r - p + 1;
        int extra = num % 5 == 0 ? 0 : 1;
        int[] medians = new int[num / 5 + extra];
        for (int i = 0; i < medians.length; i++) {
            medians[i] = computeMedian(arr, p + i * 5, Math.min(p + i * 5 + 4, r));
        }
        return bfprt(medians, 0, medians.length - 1, medians.length / 2);
    }

    /**
     * Partition the array into two parts.
     * <p>
     * After this method, elements less than pivot are put left, pivots are put middle, others are put right.
     *
     * @param arr The array to be sorted.
     * @param p   The start index of the sub-array to be sorted.
     * @param r   The end index of the sub-array to be sorted.
     * @return A two elements array. The first element indicates the index of the first pivot, the second element
     * indicates the index of the last pivot.
     */
    protected static int[] partition(int[] arr, int p, int r, int pivot) {
        int small = p - 1;
        int equal = 0;
        int temp;
        for (int j = p; j <= r; j++) {
            if (arr[j] < pivot) {
                small++;
                temp = arr[small];
                arr[small] = arr[j];
                if (equal > 0) {
                    arr[j] = arr[small + equal];
                    arr[small + equal] = temp;
                } else {
                    arr[j] = temp;
                }
            } else if (arr[j] == pivot) {
                equal++;
                temp = arr[j];
                arr[j] = arr[small + equal];
                arr[small + equal] = temp;
            }
        }
        return new int[]{small + 1, small + equal};
    }

    /**
     * Compute the median of the given array.
     *
     * @param arr   Array to be computed.
     * @param begin The begin index of the range to be computed.
     * @param end   The end index of the range to be computed.
     * @return The median of the array in the specified range.
     */
    private static int computeMedian(int[] arr, int begin, int end) {
        Arrays.sort(arr, begin, end + 1);
        return arr[begin + (end - begin) / 2];
    }

    public static void main(String[] args) {
        int count = 1000000;

        Random random = new Random();
        boolean print = false;

        long startTime = System.currentTimeMillis();

        for (int j=0; j<count; j++) {
            int[] arr= new int[random.nextInt(1000)+1];
            for (int i = 0,len=arr.length; i < len; i++) {
                arr[i] = random.nextInt(100);
            }

            if (print){
                System.out.println("原数组: "+ Arrays.toString(arr));
            }

            int[] originArr = Arrays.copyOf(arr, arr.length);

            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int[] arr3 = Arrays.copyOf(arr, arr.length);

            int[] ints = Arrays.copyOf(arr, arr.length);
            Arrays.sort(ints);

            if (print){
                System.out.println("排序后: "+ Arrays.toString(ints));
            }

            int k = random.nextInt(arr.length)+1;

            int topK = getTopK(arr, k);

            int topKBfprt = getTopKBfprt(arr2, k);

            int topKBfprt2 = getTopKBfprt2(arr3, k);

            if (topK != ints[k-1]){
                System.out.println("error ! topK: "+topK+", actual K: "+ints[k-1]+" , k: "+k);

                System.out.println("原数组: "+ Arrays.toString(originArr));
                System.out.println("排序后: "+ Arrays.toString(ints));
                return;
            }

            if (topKBfprt != ints[k-1]){
                System.out.println("error2 ! topKBfprt: "+topKBfprt+", actual K: "+ints[k-1]+" , k: "+k);

                System.out.println("原数组2: "+ Arrays.toString(originArr));
                System.out.println("排序后2: "+ Arrays.toString(ints));
                return;
            }

            if (topKBfprt2 != ints[k-1]){
                System.out.println("error3 ! topKBfprt2: "+topKBfprt2+", actual K: "+ints[k-1]+" , k: "+k);

                System.out.println("原数组2: "+ Arrays.toString(originArr));
                System.out.println("排序后2: "+ Arrays.toString(ints));
                return;
            }

            if (print){
                System.out.println("topK: "+topKBfprt2);
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("spend time: "+(endTime-startTime));
        System.out.println("Nice !");
    }


}
