package com.gym.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alipay.api.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.gym.config.*;
import com.gym.model.PayPrivateCourseModel;
import com.gym.service.PayPrivateCourseService;
import com.gym.service.PrivateCourseManagementService;

@Controller
public class PayPrivateCourseController {
	@Autowired
	PayPrivateCourseService payServce;
	
	@Autowired
	PrivateCourseManagementService privateCourseManagementService;
	
	@RequestMapping("/pay_private_course")
	public String Pay(String name,String number,String trainer,String amount,String context,Model model,HttpSession session) throws UnsupportedEncodingException, AlipayApiException {		//跳转至扫码支付页面
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(PayConfig_1.gatewayUrl, PayConfig_1.app_id, PayConfig_1.merchant_private_key, "json", PayConfig_1.charset, PayConfig_1.alipay_public_key, PayConfig_1.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(PayConfig_1.return_url);
		alipayRequest.setNotifyUrl(PayConfig_1.notify_url);
		
		Date currentDate = new Date();							//将时间戳作为商户订单号
        SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String Date = nowTime.format(currentDate);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = Date;
		//付款金额，必填
		String total_amount = amount;
		//订单名称，必填
		String subject = name;
		//商品描述，可空
		String body = context;
		
		session.setAttribute("PayName",name);		//将商品的名称存成Session
		session.setAttribute("PayContext",context);	//将商品的描述存成Session
		session.setAttribute("payAmount",amount);	//将总价存成Session
		session.setAttribute("PayNumber",number);		//将商品数量存成Session
		session.setAttribute("PayTrainer",trainer);	//将私教教练存成Session
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		//输出
//		System.out.println(result);
		
		model.addAttribute("form",result);
		
		return "PayPrivateCourse";
	}
	
	@RequestMapping("/pay_over_private_course")
	public String PayOver(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {		//支付完毕
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, PayConfig_1.alipay_public_key, PayConfig_1.charset, PayConfig_1.sign_type); //调用SDK验证签名

		HttpSession session=request.getSession();
		String pastUrl=session.getAttribute("pastUrl").toString();	//取出支付页面前的地址
		session.removeAttribute("pastUrl"); 				//销毁当前Session
		System.out.println("之前页面的网址"+pastUrl);
		
		if(signVerified) {
			//商户订单号
			String no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			//商品名称
			String name=session.getAttribute("PayName").toString();		//取出商品名称
			
			//付款金额
			String amount =session.getAttribute("payAmount").toString();
			
			//商品描述
			String context=session.getAttribute("PayContext").toString();		//取出商品描述
			
			String privateCourseName=name;							//获取购买私教课程的基本信息
			String trainerName=session.getAttribute("PayTrainer").toString();
			int memberId=(int)session.getAttribute("id");
			String privateCourseNumber=session.getAttribute("PayNumber").toString();
			
			session.removeAttribute("payName");
			session.removeAttribute("payAmount");
			session.removeAttribute("PayTrainer");
			session.removeAttribute("PayNumber");
			session.removeAttribute("PayContext");
			
			PayPrivateCourseModel pay=new PayPrivateCourseModel();
			pay.setNo(no);
			pay.setTradeNo(tradeNo);
			pay.setName(name);
			pay.setAmount(amount);
			pay.setContext(context);
			
			payServce.insertBusiness(pay); 				//将交易数据写入数据库中
			privateCourseManagementService.insertBuyPrivateCourse(privateCourseName,trainerName,memberId,privateCourseNumber);		//将买私教课信息写入数据库
			
			return "redirect:"+pastUrl;				//为true则为支付成功
		}else {
			System.out.println("验签失败");
			return "redirect:"+pastUrl;				//为false则为验签失败
		}
	}
}
