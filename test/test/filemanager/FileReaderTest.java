/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.filemanager;

import octavian.filemanager.FReader;
import octavian.filemanager.FWriter;

/**
 *
 * @author octavian
 */
public class FileReaderTest {
    public static void main(String[] args){
        final String PATH = "/home/octavian/NetBeansProjects/Encrypter/dummy.txt";
        String fwInput = "Am scris ceva !!!";
        
        FWriter fw = new FWriter(PATH);
        fw.write(fwInput.getBytes());
        System.out.println("Binary length write : " + fwInput.getBytes().length);
        FReader fr = new FReader(PATH);
        byte[] citire = fr.readBytes();
        System.out.println("Am citit : " + new String(citire) + " Binary length read : " + citire.length);
    }
}
