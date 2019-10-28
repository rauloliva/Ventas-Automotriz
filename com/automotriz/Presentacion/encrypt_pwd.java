/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automotriz.Presentacion;

/**
 *
 * @author oliva
 */
public class encrypt_pwd {

    public static void main(String[] args) {
        Hashing h = new Hashing("123");
        System.out.println(h.encrypt());
    }
}
