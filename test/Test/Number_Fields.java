/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.automotriz.VO.UsuarioVO;
import java.lang.reflect.Field;

/**
 *
 * @author A0786299
 */
public class Number_Fields {

    public static void main(String[] args) {
        for (Field f : UsuarioVO.class.getDeclaredFields()) {
            System.out.println(f.getName());
        }
        System.out.println(UsuarioVO.class.getFields().length);
    }
}
