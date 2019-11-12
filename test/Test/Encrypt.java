package Test;

import com.automotriz.Presentacion.Hashing;
import com.automotriz.Presentacion.ReadProperties;

public class Encrypt {

    public static void main(String[] args) {
        ReadProperties.loadApplicationProps();
        String pwd = "123";
        Hashing h = new Hashing(pwd);
        System.out.println(h.encrypt());
    }
}
