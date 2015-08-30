/**
 * 
 */
package com.samsung.android.example.helloaccessoryprovider.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;

/**
 * @author dream
 *
 */
public class FileSystem {
	private String path="";
	private String name="";
	private File SDCardpath, myDataPath, outFile;
	private FileOutputStream fileos;
	private String test="";

	
	public FileSystem(String path,String name,String type) throws IOException{  
		this.path = path;
		this.name = name;
					
		SDCardpath = Environment.getExternalStorageDirectory();
	    myDataPath = new File(SDCardpath.getAbsolutePath() + "/user_Authentication");
	    if (!myDataPath.exists()) myDataPath.mkdirs(); // if the path not exists then using mkdirs() to create it.
	    
	    //outFile = new File(SDCardpath.getAbsolutePath() + "/user_Authentication/" + name + "-" + getDate() + "." + type);
	    outFile = new File(SDCardpath.getAbsolutePath() + "/user_Authentication/" + getDate() + "." + type);
	    fileos = new FileOutputStream(outFile, true);
	    
	    Write("enviroment: "+Environment.getExternalStorageDirectory().getPath()+ "\r\n");
	    Write("myDataPath: "+myDataPath+ "\r\n");
	    //Write(Environment.g);
	}
	
	public void changeFile(String path,String name,String type) throws IOException{
		this.path = path;
		this.name = name;
		
		fileos.close();
		SDCardpath = Environment.getExternalStorageDirectory();
	    myDataPath = new File(SDCardpath.getAbsolutePath() + "/user_Authentication");
	    if (!myDataPath.exists()) myDataPath.mkdirs();
	    outFile = new File(SDCardpath.getAbsolutePath() + "/user_Authentication/" + name + "-" + getDate() + "." + type); // 儲存路徑Note3
	    fileos = new FileOutputStream(outFile, true);		
	}
	
	private String getDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
		Date curDate = new Date(System.currentTimeMillis()); 
		String str = formatter.format(curDate);	
		return str;
	}
	
	public void Write(String data) throws IOException{
		fileos.write(data.getBytes());
		fileos.flush();		
	}
	
	public void WriteLine(String data) throws IOException{
		data += "\r\n";
		fileos.write(data.getBytes());
		fileos.flush();	
	}
	
	public void close() throws IOException{
		fileos.close();
	}
}
