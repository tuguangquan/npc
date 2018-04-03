/**
 * 调用web service服务的工具类
 */
package com.ctgu.npc.business.common.utils;

import com.ctgu.npc.business.sug.mapper.SugMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.List;


public class MsgUtils {
	private final static String url = "http://10.19.248.31:9018/UMPManageMessageService/ManageMessage.asmx?wsdl";
	private final static String nameSpace = "http://organlist.yc.hb.cegn.cn/";
	private final static String sendtype = "3";
	private final static String level = "1";
	private final static String methodName = "SendMessage";
	private final static String definition = "";
	private final static String accpter = "";
	private final static String source = "人大履职服务平台";
	
	private static UserMapper userDao = SpringContextHolder.getBean(UserMapper.class);
	private static SugMapper sugDao = SpringContextHolder.getBean(SugMapper.class);
	
	
	
	/**
	 * 发送短信
	 * @param creator 发送人
	 * @param message 短信内容
	 * @param destination 接受人电话号码
	 * @return
	 */
	public static String  send(String creator,String message,String destination){
		Hashtable  returnInfo = new Hashtable();
		String obj = "false";
		try{	
		URL t_url = new URL(url);
			URLConnection connection = t_url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			ByteArrayOutputStream xmlData = new ByteArrayOutputStream();
			PrintStream outBuff=new PrintStream(xmlData,true,"utf-8");
			outBuff.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			outBuff.println("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
			outBuff.println("<soap:Body>");
			outBuff.println("<SendMessage xmlns=\"http://organlist.yc.hb.cegn.cn/\">");
			outBuff.println("<source><![CDATA["+source+"]]></source>");
			outBuff.println("<creator><![CDATA["+creator+"]]></creator>");
			outBuff.println("<message><![CDATA["+message+"]]></message>");
			outBuff.println("<definition><![CDATA["+definition+"]]></definition>");
			outBuff.println("<sendtype><![CDATA["+sendtype+"]]></sendtype>");
			outBuff.println("<destination><![CDATA["+destination+"]]></destination>");
			outBuff.println("<accpter><![CDATA["+accpter+"]]></accpter>");
			outBuff.println("<level><![CDATA["+level+"]]></level>");
			outBuff.println("</SendMessage>");
			outBuff.println("</soap:Body>");
			outBuff.println("</soap:Envelope>");
			
			httpConn.setRequestProperty("Content-Length", String.valueOf(xmlData.size()));
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", "http://organlist.yc.hb.cegn.cn/SendMessage");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);

			OutputStream out = httpConn.getOutputStream();
			out.write(xmlData.toByteArray());
			InputStream input = httpConn.getInputStream();
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();			
			Document doc=db.parse(input);
			if (doc != null) {
				Node GetLoginNameResponse=doc.getElementsByTagName("SendMessageResponse").item(0);
				NodeList pareList=GetLoginNameResponse.getChildNodes();
				for(int i=0;i<pareList.getLength();i++){
					Node pare=pareList.item(i);
					String name=pare.getNodeName();
					String value=pare.getTextContent();
					if(value==null)
						value="";
					returnInfo.put(name, value);					
				}				
			}	
			
			obj = (String) returnInfo.get("SendMessageResult");
		}catch (Exception e) {
			System.out.println("exception ->" + obj);
		}
		return obj;
	}
	
	
	
	/**
	 * 系统自动转发工作人员
	 * @param permission 权限标识
	 * @param message 短信内容
	 */
	/*public static void sysSend(String permission,String message){
		List<Users> list = userDao.findUserByPermission(permission, UserUtils.getSysLevel());
		for(Users user : list){
			new sendThread("0","系统自动转发" , message , user.getId() , user.getMobile()).start();
		}
		
		
	}*/
	
	/**
	 * 系统自动转发工作人员(指定单位)
	 * @param permission 权限标识
	 * @param officeID 单位id
	 * @param message 短信内容
	 */
	public static void sysSend(String permission , String officeID , String message){
		String arr[] = permission.split(",");
		List<Users> list = userDao.findUserByPermissionAndOfficeID(arr[0], officeID, UserUtils.getSysLevel());
		if(list.size()==0&&arr.length>1)
			list = userDao.findUserByPermissionAndOfficeID(arr[1], officeID, UserUtils.getSysLevel());
		for(Users user : list){
			new sendThread("0","系统自动转发" , message , user.getId() , user.getMobile()).start();
		}
	}
	
	/**
	 * 系统给个人发送短信
	 * @param message 短信内容
	 * @param receiverID 接收人id
	 * @param tel 接收电话号码
	 */
	public static void sendMsg(String message , String receiverID , String tel){
		sendMsg("0","系统自动转发" , message , receiverID , tel);
	}
	
	
	/**
	 * 个人给个人发送短信
	 * @param senderID 发送人id
	 * @param senderName 发送人姓名
	 * @param message 短信内容
	 * @param receiverID 接收人id
	 * @param tel 接收电话号码
	 */
	public static void sendMsg(String senderID ,String senderName, String message , String receiverID , String tel){
		new sendThread(senderID, senderName , message , receiverID , tel).start();
	}
	
	
	
	public static class sendThread extends Thread{

		private String senderID;
		private String creator;
		private String message;
		private String receiverID;
		private String destination;
		private Logger logger = LoggerFactory.getLogger(getClass());
		
		/**
		 * 
		 * @param senderID 发送人id
		 * @param creator 发送人姓名
		 * @param message 短信内容
		 * @param receiverID 接收人id
		 * @param destination 接收人电话号码
		 */
		public sendThread(String senderID,String creator,String message,String receiverID,String destination){
			super(sendThread.class.getSimpleName());
			this.senderID = senderID;
			this.creator = creator;
			this.message = message;
			this.receiverID = receiverID;
			this.destination = destination;
		}
		
		
		@Override
		public void run() {
			sugDao.insertNote(senderID, message, DateUtils.getDateTime(), receiverID, UserUtils.getSysLevel());
			String res = MsgUtils.send(creator, message, destination);
		}
		 
	}


	/**
	 * 系统自动转发工作人员
	 * @param permission 权限标识
	 * @param message 短信内容
	 */
	public static void sysSend(String permission,String message){
		List<Users> list = userDao.findUserByPermission(permission, UserUtils.getSysLevel());
		for(Users user : list){
			new sendThread("0","系统自动转发" , message , user.getId() , user.getMobile()).start();
		}
		
		
	}
	
}
