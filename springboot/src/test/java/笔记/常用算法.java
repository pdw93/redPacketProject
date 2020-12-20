package 笔记;

import com.mysql.cj.protocol.a.BinaryResultsetReader;
import org.junit.Test;
import 笔记.pojo.SimpleNote;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName 常用算法
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/14 12:59
 * @Version 1.0
 **/
public class 常用算法 {

    /**
     * 查找‘a’在‘list’中的下标
     *
     * @param list  待查找的有序集合
     * @param a     要查找的元素
     * @param left  查找左范围（包含）
     * @param right 查找右访问（包含）
     * @return 下标值，未查找到-1
     */
    public static int 二分查找(List<Integer> list, Integer a, int left, int right) {
        if (list != null) {
            int module = (left + right) / 2;
            if (list.get(module).equals(a)) {
                return module;
            } else if (list.get(module) < a) {
                left = module + 1;
                if (left <= right) {
                    return 二分查找(list, a, left, right);
                }
            } else {
                right = module - 1;
                if (left <= right) {
                    return 二分查找(list, a, left, right);
                }
            }
        }
        return -1;
    }

    @Test
    public void testBinaryS(){
        int[] arr = {1,2,3};
        int i = binarySearch(arr, 2);
        System.out.println(i);
    }
    public int binarySearch(int[] arr, int search) {
        if (arr != null && arr.length > 0) {
            int left = 0;
            int right = arr.length - 1;
            int mid = (left + right) / 2;
            while (arr[mid] != search) {
                if (arr[mid] < search) {
                    // 右边
                    left = mid + 1;
                } else {
                    // 左边
                    right = mid - 1;
                }
                if (left > right) {
                    break;
                }
                mid = (left + right) / 2;
            }
            if (arr[mid] == search) {
                return mid;
            }
        }

        return -1;
    }


    /**
     * 测试广度优先搜索
     */
    @Test
    public void testBreadth_firstSearch() {
        SimpleNote firstNote = new SimpleNote("a");
        SimpleNote b = new SimpleNote("b");
        SimpleNote c = new SimpleNote("c");
        SimpleNote d = new SimpleNote("d");
        firstNote.getChildren().add(b);
        firstNote.getChildren().add(c);
        firstNote.getChildren().add(d);
        SimpleNote e = new SimpleNote("e");
        d.getChildren().add(e);
        SimpleNote searchNode = new SimpleNote("e");
        SimpleNote note = 广度优先搜索(firstNote, searchNode);
        System.out.println(note == null ? "null" : note.getValue());
    }

    /**
     * 广度优先搜索 适用于无权重图查找a-》b的最短路径
     *
     * @param firstNote  查找其实点
     * @param searchNote 要查找的点
     */
    public SimpleNote 广度优先搜索(SimpleNote firstNote, SimpleNote searchNote) {
        Queue<SimpleNote> searchQueue = new LinkedBlockingQueue<>();
        if (firstNote != null && firstNote.getChildren() != null) {
            firstNote.getChildren().stream().forEach(e -> e.setParent(firstNote));
            searchQueue.addAll(firstNote.getChildren());
        } else {
            return null;
        }
        while (searchQueue.size() > 0) {
            SimpleNote poll = searchQueue.poll();
            if (poll.isChecked()) {
                continue;
            }
            if (poll.equals(searchNote)) {
                return poll;
            } else {
                poll.getChildren().stream().forEach(e -> e.setParent(poll));
                searchQueue.addAll(poll.getChildren());
            }
            poll.setChecked(true);
        }
        return null;
    }


    /**
     * 测试快速排序
     */
    @Test
    public void quickSortTest() {
        int[] arr = new int[]{1, 2, 3, 5, 6};
        快速排序(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param [arr, left, right]
     * @return void
     * @Author shnstt
     * @Description //快速排序
     * @Date 13:23 2019/6/14
     **/
    public void 快速排序(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        // 基准
        int base = arr[left];
        int temp;
        while (i < j) {
            // 从右边开始查找小于基准的
            while (base <= arr[j] && i < j) {
                j--;
            }
            // 从左边查找大于基准的
            while (base >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件交换
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //将基准与i和j相等的位置交换
        if (i != left) {
            arr[left] = arr[i];
            arr[i] = base;
        }
        // 左边递归
        快速排序(arr, left, j - 1);
        // 右边递归
        快速排序(arr, j + 1, right);

    }
}
