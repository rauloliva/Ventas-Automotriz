package Test;

import com.automotriz.Presentacion.Hashing;

public class Encrypt {

    public static void main(String[] args) {
        Hashing h = new Hashing("123");
        System.out.println(h.encrypt());
    }
}
