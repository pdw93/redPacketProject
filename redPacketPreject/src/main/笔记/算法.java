import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName 算法
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/10 22:40
 * @Version 1.0
 **/
public class 算法 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 1).stream().sorted().collect(Collectors.toList());
        int result = 二分查找(list, 2, 0,list.size()-1);
        System.out.println(result);
    }

    /**
     * 查找‘a’在‘list’中的下标
     * @param list 待查找的有序集合
     * @param a 要查找的元素
     * @param left 查找左范围（包含）
     * @param right 查找右访问（包含）
     * @return 下标值，未查找到-1
     */
    public static int 二分查找(List<Integer> list, Integer a, int left, int right) {
        if (list != null) {
            int module = (int) Math.floor((left+right)/2);
            if (list.get(module).equals(a)) {
                return module;
            } else if (list.get(module) < a){
                left=module+1;
                if (left<=right){
                    return 二分查找(list,a,left,right);
                }
            } else {
                right=module-1;
                if (left<=right){
                    return 二分查找(list,a,left,right);
                }
            }
        }
        return -1;
    }
}
