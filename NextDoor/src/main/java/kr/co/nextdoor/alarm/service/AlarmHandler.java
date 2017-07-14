package kr.co.nextdoor.alarm.service;
/*
* @Class : AlarmHandler
* @Date : 2017. 06. 20 
* @Author : 박찬섭
* @Desc : Alarm
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlarmHandler extends TextWebSocketHandler{

	static List<WebSocketSession> connectedUsers = new ArrayList<WebSocketSession>();
	
	@Autowired
	AlarmService alarmservice;
	/*
	    * @method Name : afterConnectionEstablished
	    * @date : 2017. 06. 20
	    * @author : 박찬섭
	    * @description : 회원이 접속하면 접속유저세션리스트에 세션 추가
	    */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		connectedUsers.add(session);
		
	}

	/*
	    * @method Name : afterConnectionClosed
	    * @date : 2017. 06. 20
	    * @author : 박찬섭
	    * @description : 회원이 접속을 종료하면 접속유저세션리스트에서 세션 제거
	    */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		connectedUsers.remove(session);
		
	}

	/*
	    * @method Name : handleTextMessage
	    * @date : 2017. 06. 20
	    * @author : 박찬섭
	    * @description : view단에서 send()요청이 오면 send파라메타를 처리 
	    */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> map = session.getAttributes();
		String user_id = (String) map.get("user_id");
		String content = message.getPayload();
		JSONParser jp = new JSONParser();
	    JSONObject jo = (JSONObject) jp.parse(content);
	    String receiver = (String)jo.get("receiver");
	    String specifictask_cont = (String)jo.get("specifictask_cont");
	    int alarmcount = alarmservice.CountAlarmList(receiver)+1;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user_id",user_id);
		data.put("specifictask_cont", specifictask_cont);
		data.put("receiver", receiver);
		data.put("alarmcount", alarmcount);
		ObjectMapper om = new ObjectMapper();
		String jsonStr = om.writeValueAsString(data);
		
		for (WebSocketSession webSocketSession : connectedUsers) {
			if (!session.getId().equals(webSocketSession)) {
				webSocketSession.sendMessage(new TextMessage(jsonStr));
			}
		}
	}
	
	/*
	    * @method Name : handleTransportError
	    * @date : 2017. 06. 20
	    * @author : 박찬섭
	    * @description : 에러 처리
	    */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
	}
	

}
