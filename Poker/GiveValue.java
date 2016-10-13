/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class GiveValue {
    public static int[] intGet(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        return a;
    }

    public static String[] StringGet(String[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = new String();
        }
        return s;
    }

    public static Long[] LongGet(Long[] l) {
        for (int i = 0; i < l.length; i++) {
            l[i] = new Long(0);
        }
        return l;
    }

    public static Integer[] intergerGet(Integer[] integers) {
        for (int i = 0; i < integers.length; i++) {
            integers[i] = 0;
        }
        return integers;
    }
}