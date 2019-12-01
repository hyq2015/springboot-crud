package springbootcrud.common;

import java.util.Random;

public class RandomCode {
    private static final int codes[] = {0, 1, 2 , 3, 4, 5, 6, 7, 8, 9};
    public static String getCode(int num) {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < num; i++) {
            s.append(codes[new Random().nextInt(10)]);
        }
        return s.toString();
    }
}
