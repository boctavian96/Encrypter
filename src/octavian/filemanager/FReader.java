/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author octavian
 */
public class FReader{

	private final String filename;
        
        public FReader(String filename){
            this.filename = filename;
        }

	public String read(){

            BufferedReader br = null;
            FileReader fr = null;
            StringBuilder output = new StringBuilder();

            try {
                    fr = new FileReader(filename);
                    br = new BufferedReader(fr);
                
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
			output.append(sCurrentLine);
                    }
		} catch (IOException e) {
                    e.printStackTrace();
		} finally {

                    try {
                        if (br != null)
                            br.close();

                        if (fr != null)
                            fr.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                    }
		}
            
            return output.toString();
	}
        
        public char[] readCharacters(){
            char[] result = read().toCharArray();
            
            return result;
        }
        
        public byte[] readBytes(){
            byte[] fileToByte = null;
            try{
            fileToByte = Files.readAllBytes(new File(filename).toPath());
            }catch(IOException e){
                e.printStackTrace();
            }
            
            return fileToByte;
        }
}