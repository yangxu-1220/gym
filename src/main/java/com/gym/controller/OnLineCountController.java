package com.gym.controller;

import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * html页面js获取数据方式
 * 
 *   onlinenum = new WebSocket("ws://localhost:8080/online_count");
 *   //接收到消息的回调方法
 *   onlinenum.onmessage = function(event){
 *       if(event.data>=1)
 *       	alert('大于等于1');
 *   }
 *   
 */
@Component
@ServerEndpoint("/online_count")  					//该注解表示该类被声明为一个webSocket终端
public class OnLineCountController {
    //初始在线人数
    private static int online_num = 0;
    //线程安全的socket集合
    private static CopyOnWriteArraySet<OnLineCountController> webSocketSet = new CopyOnWriteArraySet<OnLineCountController>();
    //会话
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        this.session.getAsyncRemote().sendText(""+getOnline_num());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
    }

    public synchronized int getOnline_num(){
        return OnLineCountController.online_num;
    }
    public synchronized int subOnlineCount(){
        return OnLineCountController.online_num--;
    }
    public synchronized int addOnlineCount(){
        return OnLineCountController.online_num++;
    }
}
