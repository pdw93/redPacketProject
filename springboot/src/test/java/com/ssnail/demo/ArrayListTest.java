package com.ssnail.demo;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ArrayListTest
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/23 11:14
 * @Version 1.0
 **/
public class ArrayListTest {

    @Test
    public void test5(){
        int[] arr = new int[0];
        System.out.println(arr[0]);
    }

    @Test
    public void test4(){
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(16);
        ThreadPoolExecutor my_thread_factory = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, workQueue, new DefaultThreadFactory("my thread factory"), new ThreadPoolExecutor.AbortPolicy());
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>(5);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        List<String> strings = list.subList(0, 3);

        list.remove(0);

        strings.clear();
        strings.add("f");
        strings.add("g");
        strings.add("h");
        strings.add("i");
        strings.remove(0);
        System.out.println(strings);
        System.out.println(list);
    }
    @Test
    public void test2(){
        List<Integer> integers = new ArrayList<>(10);
//        List<Object> objects = integers;
    }
    /**
     * arr.length < list.size() 时返回新数组
     */
    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        Integer[] arr = new Integer[1];
        // arr.length < list.size() 时返回新数组
        Integer[] integers = list.toArray(arr);
        System.out.println(Arrays.asList(arr));
        System.out.println(Arrays.asList(integers));
    }
}
