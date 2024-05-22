package com.softnovo.algorithm.juc.pool;

import apple.laf.JRSUIUtils;
import com.alibaba.fastjson2.JSON;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Test {
    private static Map<Long, Long> cache = new HashMap<>();
//    static {
//        cache.putIfAbsent(0L, 1L);
//        cache.putIfAbsent(1L, 1L);
//    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(f(40));
        System.out.println(f(10));
        System.out.println(f(3));

        Map<Person, String> mapp = new HashMap<>(4);

        for (int i = 0; i < 10; i++) {
            mapp.put(new Person(String.valueOf(i)), "1");
            System.out.println(mapp.size());
        }




//        ResizeableCapacityLinkedBlockingQueue<String> blockingDeque = new ResizeableCapacityLinkedBlockingQueue<>(5);
//        for (int i = 0; i < 10; i++) {
//            blockingDeque.put("i");
//            System.out.println(i);
//        }
//        System.out.println(f(2));
//        System.out.println(f(3));
//        System.out.println(f(4));
//        System.out.println(f(10));

        TreeData treeData = new TreeData("0", "");
        TreeData treeData1 = new TreeData("1", "0");
        TreeData treeData11 = new TreeData("11", "1");
        TreeData treeData111 = new TreeData("111", "11");
        TreeData treeData2 = new TreeData("2", "0");
        TreeData treeData22 = new TreeData("22", "2");
        TreeData treeData3 = new TreeData("3", "0");
        TreeData treeData33 = new TreeData("33", "3");
        TreeData treeData333 = new TreeData("333", "33");
        TreeData treeData3333 = new TreeData("3333", "333");
        TreeData treeData4 = new TreeData("4", "0");
        TreeData treeData5 = new TreeData("5", "0");

        List<TreeData> treeDataList = new ArrayList<>();
        treeDataList.add(treeData33);
        treeDataList.add(treeData333);

        treeDataList.add(treeData11);
        treeDataList.add(treeData111);
        treeDataList.add(treeData2);
        treeDataList.add(treeData22);
        treeDataList.add(treeData3);

        treeDataList.add(treeData3333);
        treeDataList.add(treeData4);
        treeDataList.add(treeData5);
        treeDataList.add(treeData);
        treeDataList.add(treeData1);

        //Collections.sort(treeDataList, Comparator.comparing(TreeData::getParentId));
        System.out.println(treeDataList);

        System.out.println(JSON.toJSONString(formatTree(treeDataList)));
    }

    static long f(int n) {
        if (n <= 2) {
            return 2;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            long l = f(n - 1) + f(n - 2);
            cache.put(Long.valueOf(n), l);
            return l;
        }
    }

    public static List<TreeData> formatTree(List<TreeData> list) {
        List<TreeData> nodeList = new ArrayList();
        Iterator<TreeData> var2 = list.iterator();

        while (var2.hasNext()) {
            TreeData treeData1 = var2.next();
            //是否有父节点
            boolean flag = false;
            Iterator<TreeData> var5 = list.iterator();

            while (var5.hasNext()) {
                TreeData treeData2 = var5.next();
                if (StringUtils.isNotEmpty(treeData1.getParentId()) && treeData1.getParentId().equals(treeData2.getId())) {
                    //treeData2是个父节点
                    flag = true;
                    if (treeData2.getChildList() == null) {
                        treeData2.setChildList(new ArrayList());
                    }

                    treeData2.getChildList().add(treeData1);
                    break;
                }
            }

            if (!flag) {
                //没有父节点，本身是个父节点
                if (treeData1.getChildList() == null) {
                    treeData1.setChildList(new ArrayList());
                }

                nodeList.add(treeData1);
            }
        }

        return nodeList;
    }

    public static TreeData formatTree2(List<TreeData> list) {
        // 《parent，TreeData》
        Map<String, TreeData> map = new HashMap<>();
        for (TreeData treeData : list) {
            if (CollectionUtils.isEmpty(treeData.getChildList())) {
                treeData.setChildList(new ArrayList<>());
            }
            map.put(treeData.getId(), treeData);
            if (map.get(treeData.getParentId()) != null) {
                map.get(treeData.getParentId()).getChildList().add(treeData);
            }
        }
        return map.get("0");
    }


}
