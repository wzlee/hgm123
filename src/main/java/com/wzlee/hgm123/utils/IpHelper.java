package com.wzlee.hgm123.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzlee.hgm123.domain.IpData;

public class IpHelper {
	public static Object[] GET_IP_ARR(String ipfrom, String ipto) {
	    ArrayList<String> ips = new ArrayList<String>();
	    String[] ipfromd = ipfrom.split("\\.");
	    String[] iptod = ipto.split("\\.");
	    int[] int_ipf = new int[4];
	    int[] int_ipt = new int[4];
	    for (int i = 0; i < 4; i++) {
	        int_ipf[i] = Integer.parseInt(ipfromd[i]);
	        int_ipt[i] = Integer.parseInt(iptod[i]);
	    }
	    for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
	        for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
	                : 255); B++) {
	            for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
	                    : 255); C++) {
	                for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
	                        : 255); D++) {
	                    ips.add(new String(A + "." + B + "." + C + "." + D));
	                }
	            }
	        }
	    }
	    return ips.toArray();
	}
	
	public static IpData queryIpData(String ip){
		IpData id = new IpData();
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		getMethod.getParams().setContentCharset("UTF-8");
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+ getMethod.getStatusLine());
			}
			//读取内容
			InputStream responseBody = getMethod.getResponseBodyAsStream();
			String responseText;
			try {
				responseText = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
				JSONObject jo = JSON.parseObject(responseText);
				if(jo.getIntValue("code") == 0){
					id = (IpData)JSON.parseObject(jo.get("data").toString(), IpData.class);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(queryIpData("183.16.115.121").getRegion());
//		for(Object obj:GET_IP_ARR("192.168.0.100","192.168.0.255")){
//			System.out.println(obj.toString());
//		}
	}
}
