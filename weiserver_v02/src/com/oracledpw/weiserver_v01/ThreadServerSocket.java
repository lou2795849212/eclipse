package com.oracledpw.weiserver_v01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 测试服务器能被多连的线程
 * 
 * @author Administrator
 *
 */
public class ThreadServerSocket extends Thread {

	// 第一开始的有一个浏览器对象因为这样才知道有浏览器来访问
	private Socket liulanqi;

	public ThreadServerSocket(Socket liulanqi) {
		super();
		this.liulanqi = liulanqi;
	}

	@Override
	public void run() {
		
		PrintWriter out=null;
		BufferedReader r=null;
		 //浏览器来了服务器得对浏览器说一句欢迎光临        所以无论是输出还是输入都得用流
		  
		try {
			//开始读
			r=new BufferedReader(new InputStreamReader(liulanqi.getInputStream()));
			//读响应头 若干响应行   空白行          一行行的读
			String requestLine=r.readLine();
			System.out.println(requestLine);
			//读若干行                      只要不等于空白行就一直读下去
			String str="";
			
			while(!(str=r.readLine()).equals("")) {
				System.out.println(str);
			}
			
			
			
			
			
			
			
			
			
			
			 out=new PrintWriter(liulanqi.getOutputStream());
			 //响应头
			 out.println("HTTP/1.1 200 OK");
			 //若干响应
			 out.println("Content-Type:text/html;Charset=utf-8");
			 //空白行
			 out.println();
			 //正文
			// out.println("<a href='http://192.168.1.110:808'>进入得力电脑</a>");
			 out.print("<b><font color='red'>Hello World</font></b>");
			 
			 //因为不达到8个字节不会发出所以得强制发出
			 out.flush();
	
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//注意如果不关闭程序就会卡到哪里不走一直不动
			if(r!=null) {
				try {
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null) {
				out.close();
			}
		}
		
	}

}
