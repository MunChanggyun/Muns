package kr.co.nextdoor.chat.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/*
* @Class : ChatHandlerInterceptor
* @Date : 2017. 06. 20 
* @Author : 이재민
* @Desc : Chat
*/
@Configuration
@EnableWebSocket
public class ChatHandlerInterceptor extends HttpSessionHandshakeInterceptor {

		/*
	    * @method Name : beforeHandshake
	    * @date : 2017. 06. 20
	    * @author : 이재민
	    * @description : Chathandler에  user_id, project_no를 중간에 가로채서 넣어줌
	    */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
		Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
		HttpServletRequest req = sshr.getServletRequest();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		attributes.put("project_no", req.getSession().getAttribute("project_no"));
		attributes.put("user_id", user.getUsername());
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {

		super.afterHandshake(request, response, wsHandler, ex);
	}

}