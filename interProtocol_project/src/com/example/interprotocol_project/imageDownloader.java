package com.example.interprotocol_project;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class imageDownloader {
	
	public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    											 // should bigger than the file to be downloaded
	
	static long start = System.currentTimeMillis();
	static int bytesRead;
	static int current = 0;
	
	public static void getFile(final Activity activity) {
		//final ProgressDialog dialog = ProgressDialog.show(activity, "", "정보 수신중입니다...", true, false);
	
		new Thread(new Runnable() {
			public void run() {
				Log.e("gge", "start");
				try {
					Socket sock = new Socket("192.168.0.30", 63000);
					byte [] mybytearray = new byte [FILE_SIZE];
					InputStream is;
					is = sock.getInputStream();
					FileOutputStream fos;
					Log.e("gge", "before fos");
					fos = new FileOutputStream("/mnt/sdcard/Download/source-copy.JPG");
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					bytesRead = is.read(mybytearray,0,mybytearray.length);
					current = bytesRead;
					do{
						
						bytesRead = 
								is.read(mybytearray,current,(mybytearray.length-current));
					
					if(bytesRead >= 0) current+=bytesRead;
				}while(bytesRead >-1);
					bos.write(mybytearray,0,current);
					bos.flush();
					bos.close();
					long end = System.currentTimeMillis();
					sock.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
				

				}
			}
		).start();
	}
}