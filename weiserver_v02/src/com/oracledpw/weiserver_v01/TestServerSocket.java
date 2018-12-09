package com.oracledpw.weiserver_v01;
/**
 * 测试服务器端        ServerSocket
 * @author Administrator
 *  而且这个发送到浏览器的内容还得一定可靠 而且还得有序 所以采用TCP协议
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerSocket {
          
         public static void main(String[] args) {
        	 ServerSocket   s=null;
        	 try {
				//服务器端
				   s=new ServerSocket(8080);
				 
				 //等待浏览器来访问                          但是还得有一个浏览器对象
				 //但是这个服务器可以多个浏览器访问所以就用到了多线程    因为是多个访问 
				 while(true) {
				  Socket     liulanqi     = s.accept();
				   new ThreadServerSocket(liulanqi).start();
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(s!=null) {
					try {
						s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
        	 
        	 
		}
	            
	            
	            
	            
	            
	        
}
