import java.util.ArrayList;
import java.util.List;

/**
 * Write an application to find out how many total characters can be held in a list of strings before you run out of memory
 *
 * @author joshpowell
 */
public class OutOfMemory {
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int count = 0;
        while (true == true) {
            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i <= Integer.MAX_VALUE; i++) {
//                stringBuilder.append('a');
//            }
            list.add("a");
            count++;
            System.out.println(count);
        }
    }
}
