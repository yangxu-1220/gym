package com.gym.controller;

import java.io.UnsupportedEncodingException;
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
import com.gym.model.PayModel;
import com.gym.service.CartService;
import com.gym.service.GoodsStockService;
import com.gym.service.OrderService;
import com.gym.service.PayService;


@Controller
public class PayController {
	@Autowired
	PayService payServce;
	@Autowired
	OrderService orderService;
	@Autowired
	CartService cartService;
	@Autowired
	GoodsStockService goodsStockService;
	
	@RequestMapping("/pay")
	public String Index(String orderId,String name,float amount,String context,Model model,HttpSession session,String goodsIds,String numArray) {		//链接到支付页面
		int n_1 = 0;
		int m_1 = 0;
		String[] idArray;
		String[] numberArray;
		for(int i=0;i<goodsIds.length();i++) {
			if(goodsIds.charAt(i)==',') n_1++;
		}
		idArray=goodsIds.trim().split(",");
		for(int i=0;i<numArray.length();i++) {
			if(numArray.charAt(i)==',') m_1++;
		}
		numberArray=numArray.trim().split(",");
		PayModel pay=new PayModel();
		pay.setNo(orderId);					
		pay.setName(name);
		pay.setAmount(String.valueOf(amount));
		pay.setContext(context);
		model.addAttribute("pay",pay);
		session.setAttribute("orderId", orderId); 	//订单号存进Session
		session.setAttribute("name",name);		//将商品的名称存成Session
		session.setAttribute("context",context);	//将商品的描述存成Session
		session.setAttribute("goodsIds", idArray);	//将两个数组存入session
		session.setAttribute("numArray", numberArray);
		return "PayIndex";
	}
	
	@RequestMapping("/pay_do")
	public String Pay(PayModel realPay,Model model) throws UnsupportedEncodingException, AlipayApiException {		//跳转至扫码支付页面
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(PayConfig.gatewayUrl, PayConfig.app_id, PayConfig.merchant_private_key, "json", PayConfig.charset, PayConfig.alipay_public_key, PayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(PayConfig.return_url);
		alipayRequest.setNotifyUrl(PayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String((realPay.getNo()).getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		String total_amount = new String((realPay.getAmount()).getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		String subject = realPay.getName();
		//商品描述，可空
		String body = realPay.getContext();
		
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
		
		return "Pay";
	}
	
	@RequestMapping("/pay_over")
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
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, PayConfig.alipay_public_key, PayConfig.charset, PayConfig.sign_type); //调用SDK验证签名

		if(signVerified) {
			//商户订单号
			String no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");			
			//System.out.println(no);			
			
			//支付宝交易号
			String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			//商品名称
			HttpSession session=request.getSession();
			int userId=(int)session.getAttribute("id");
			String orderId=cartService.GetOrderIdByUserId(userId);		//根据userId获取orderId
			String name=session.getAttribute("name").toString();		//取出商品名称
			//String orderId=session.getAttribute("orderId").toString();	//取出自定义的orderId(y模块)
			String[] goodsIds=(String[])session.getAttribute("goodsIds");
			String[] numArray=(String[])session.getAttribute("numArray");
			goodsStockService.SubStockByGoodsId(goodsIds, numArray); 		//根据两个数组的数据修改库存
			
			//付款金额
			String amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			float orderTotalPrice=Float.parseFloat(amount);
			System.out.println(orderId);
			System.out.println(orderTotalPrice);
			orderService.RenewStateByOrderId(orderTotalPrice,orderId);					//根据ID修改订单状态为已支付(y模块)
			//商品描述
			String context=session.getAttribute("context").toString();		//取出商品描述
			
			PayModel pay=new PayModel();
			pay.setNo(no);
			pay.setTradeNo(tradeNo);
			pay.setName(name);
			pay.setAmount(amount);
			pay.setContext(context);
			
			
			payServce.insertBusiness(pay); 				//将交易数据写入数据库中
			return "redirect:/myOrder/index";				//为true则为支付成功
		}else {
			System.out.println("验签失败");
			return "false";				//为false则为验签失败
		}
	}
}
