package com.fei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 打印一个字符串的子序列，要求按照原来的顺序
 * 例如：abc
 * 他的子序列为： a、b、c、ab、ac、bc、abc
 * @author: guyuefeng
 * @create: 2022-04-17 18:21
 **/
public class ChildSequence {

    public static class CompareList<String> implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.hashCode() - o2.hashCode();
        }
    }

    public static List<String> recurision0(String str){
        List<String> list = new ArrayList<>();
        recursionStr0(str, 0, "", list);
        Collections.sort(list);

        list.sort(new CompareList());
        return list;
    }

    public static void recursionStr0(String str, int index, String res, List<String> list){
        if (index == str.length()){
            return ;
        }

        res += str.charAt(index)+"";
        list.add(res);

        recursionStr0(str, index+1, res,  list);
        res = res.replace(str.charAt(index)+"", "");
        recursionStr0(str, index+1, res, list);
    }


    public static List<String> recurision(String str){
        List<String> list = new ArrayList<>();
        recursionStr(str, 0, "", list);
        list.sort(new CompareList());
        return list;
    }

    public static void recursionStr(String str, int index, String res, List<String> list){
        if (index == str.length()){
            return ;
        }

        recursionStr(str, index+1, res, list);
        res += str.charAt(index)+"";
        list.add(res);
        recursionStr(str, index+1, res, list);
    }

    public static List<String> recurision1(String str){
        List<String> list = new ArrayList<>();
        recursionStr1(str, 0, "", list);
        list.sort(new CompareList());
        return list;
    }

    public static void recursionStr1(String str, int index, String res,  List<String> list){
        if (index == str.length()){
            return ;
        }

        recursionStr1(str, index+1, res, list);
        res += str.charAt(index)+"";
        recursionStr1(str, index+1, res, list);

        list.add(res);
    }

    public static void main(String[] args) {
        List<String> abc0 = recurision0("abc");
        List<String> abc1 = recurision("abc");
        List<String> abc2 = recurision1("abc");

        System.out.println("先序遍历："+abc0);
        System.out.println("中序遍历："+abc1);
        System.out.println("后序遍历："+abc2);
    }
}
