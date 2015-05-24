package com.savi.cernet.soa;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.savi.base.util.Constants;
import com.savi.base.util.W3CXML;
import com.savi.cernet.util.NasCoderUtil;
public class WSUtil{
	public static String getUserName(String ip,ArrayList<Integer> timeoutFlag){
		try{
			if(timeoutFlag.size()==0){
				timeoutFlag.add(0);
			}
			/*
			while(true){
				if(Constants.webServiceURL!=null)break;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			*/
			if(Constants.webServiceURL==null){
				return "";
				//Constants.webServiceURL="http://166.111.9.13:8080/localOnlineSoa/localOnlineSoa";
			}
	    	LocalOnlineSoaPortBindingStub binding;
	        binding = (LocalOnlineSoaPortBindingStub)
	                          new LocalOnlineSoaServiceLocator(Constants.webServiceURL).getlocalOnlineSoaPort();
	        binding.setTimeout(500);
			String r = "12345678";
			long time = System.currentTimeMillis();
			String serverName = "gLr6mcAsOSs52DIr";
			String serverPwd = "oldGhqYQXrSMeQOt";
			String md5String = NasCoderUtil.stringToMD5(serverName + serverPwd + r + time);
			GetOnlineUserByIp arg=new GetOnlineUserByIp();
			/*
			arg.setAuthType("1");
			arg.setAuthenticateStr(md5String);
			arg.setAuthenticateReserved("");
			arg.setRandomNum(r);
			arg.setTheTimestamp(time+"");
			arg.setIp("2001:da8:23d:f110:357d:4777:a09b:71fc 2001:da8:23d:f110:2e0:4cff:fe36:e433");
			*/
	        // Test operation
	        //GetOnlineUserByIpResponse value = null;
	        String xmlString2 = binding.getOnlineUserByIp("1", md5String, "", r, time+"", ip);
			//返回用户信息XML格式样例
			/*
			String xmlString2=""+
			"<?xml version=\"1.0\" encoding=\"GBK\"?>"+
			"<list>"+
				"<user>"+
					"<ACCTSESSIONID>1</ACCTSESSIONID>"+
					"<ACCTSTARTTIME>1</ACCTSTARTTIME>"+
					"<IPV6>1</IPV6>"+
					"<MAC>1</MAC>"+
					"<NAS_PORT>1</NAS_PORT>"+  
					"<IPV4>1</IPV4>"+
					"<LOGIN_NAME>zhangli</LOGIN_NAME>"+
					"<NAS_IP>1</NAS_IP>"+     
				"</user>"+
			"</list>";
			*/
			//对xml进行解析，提取出用户名
			org.w3c.dom.Document xmlViewdoc = W3CXML.parseXMLDocument(xmlString2);
			NodeList nodes=xmlViewdoc.getElementsByTagName("LOGIN_NAME");
			Node node=nodes.item(0);
			if(node!=null){
				String userName=node.getChildNodes().item(0).getNodeValue();
				return userName;
			}else{
				return "";
			}
		}catch(java.rmi.RemoteException e){
			timeoutFlag.set(0, 1);
			return "";
		}catch(Exception e){
			//e.printStackTrace();
			return "";
		}
	}

	static public void main(String args[]){
		ArrayList<Integer> flag=new ArrayList<Integer>();
		String userName=WSUtil.getUserName("2001:da8:23d:f110:2e0:4cff:fe36:e433 ",flag);
		System.out.println(userName+"....."+flag.get(0));
	}	
}

