package com.kh.spring11.websocket.server;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.kh.spring11.websocket.vo.WebSocketV1RequestVO;
import com.kh.spring11.websocket.vo.WebSocketV1ResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller//등록은 컨트롤러지만 실제 역할은 웹소켓 서버
public class WebSocketV1BasicServer2 {

	//@SendTo 대신 직접 채널을 지정하여 메세지를 전송할 수 있는 도구
	// → 채널을 문자열 조합으로 계산하여 지정할 수 있음 (확장성이 무한대)
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/basic")//웹소켓(STOMP)의 매핑 방식
	public void basic(Message<WebSocketV1RequestVO> message) {//사용자가 보낸 메세지를 수신
		//수신된 객체를 꺼낸다
		WebSocketV1RequestVO request = message.getPayload();
		
		//수신한 메세지를 그대로 전송하는 것이 아니라 발신용 메세지를 만들어서 정보를 채워야 한다
		WebSocketV1ResponseVO response = WebSocketV1ResponseVO.builder()
					.content(request.getContent())//보낸메세지 그대로
					.time(LocalDateTime.now())//현재시각
				.build();
		
		simpMessagingTemplate.convertAndSend("/public/basic", response);
	}
	
}
