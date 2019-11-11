package Test;

import com.automotriz.Presentacion.Hashing;
import com.automotriz.Presentacion.ReadProperties;

public class Encrypt {

    public static void main(String[] args) {
        ReadProperties.loadApplicationProps();
        Hashing h = new Hashing("123");
        System.out.println(h.encrypt());
    }
}
