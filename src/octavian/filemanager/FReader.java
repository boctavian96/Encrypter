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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author octavian
 */
public class FReader{

	private final String filename;
        
        public FReader(String filename){
            this.filename = filename;
        }
        
        public List<String> readList(){
            String input = read();
            List<String> result = new ArrayList<String>();
            
            for(int i = 0; i < input.length(); i++){
                
            }
            
            return result;
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
            String text = read();
            return text.getBytes();
        }
        
        public byte[] readNumbers(){
            String readFile = read();
            String[] resultString = readFile.split("\\s");
            byte[] resultByte = new byte[resultString.length];
            
            for(int i = 0; i < resultString.length; i++){
                resultByte[i] = Byte.parseByte(resultString[i]);
            }
            
            return resultByte;
        }
}