/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.Serializable;

/**
 *
 * @author drfelix
 */

public class TestPai implements Serializable{

    public TestPai(String helloWorhd) {
        this.helloWorhd = helloWorhd;
    }

    
//    @MyNumberMinimo(numMini = 30)
    private String helloWorhd;

    @Override
    public String toString() {
        return "TestPai{" + "helloWorhd=" + helloWorhd + '}';
    }

    public String getHelloWorhd() {
        return helloWorhd;
    }

    public void setHelloWorhd(String helloWorhd) {
        this.helloWorhd = helloWorhd;
    }
    
    
    
    
    
    
}
