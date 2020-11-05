package com.gym.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gym.model.OrderDetailModel;
import com.gym.service.CartService;

@RequestMapping("/cart")
@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	/**
	 * 获取当前用户的ID去查询商品详情，跳转到CartIndex
	 * @param request
	 * @return
	 */	
	@RequestMapping("/selectDetailById")	
	public String SelectDetailById(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
		Integer userId=(int)session.getAttribute("id");
		System.out.println(userId);
		List<OrderDetailModel> orderDetail=cartService.SelectDetailById(userId);
		
		model.addAttribute("orderDetail", orderDetail);
		return "CartIndex";
	}
	
	/**
	 *  根据商品ID和订单ID去删除一条订单详情记录
	 * @param goodsId
	 * @param orderId
	 */
	@RequestMapping("/deleteDetailById")
	public String DeleteDetailById(Integer goodsId,String orderId) {
		cartService.DeleteDetailById(goodsId, orderId);
		return "redirect:/cart/selectDetailById";
	}
}
