package com.kh.spring11.websocket.server;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.kh.spring11.websocket.vo.WebSocketV2RequestVO;
import com.kh.spring11.websocket.vo.WebSocketV2ResponseVO;

import lombok.extern.slf4j.Slf4j;

//웹소켓 서버 버전(2) - 헤더를 사용하는 서버
@Slf4j
@Controller
public class WebSocketV2AdvancedServer {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/advanced")//경로 작성 시 /app은 제외하고 작성(미리 설정한 경로)
	public void advanced(Message<WebSocketV2RequestVO> message) {
		//메세지 본문을 추출
		WebSocketV2RequestVO request = message.getPayload();
		//메세지 헤더를 추출
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
		String uuid = headerAccessor.getFirstNativeHeader("uuid");
		if(uuid == null) return;
		
		//사용자에게 보낼 메세지를 생성
		WebSocketV2ResponseVO response = WebSocketV2ResponseVO.builder()
					.sender(uuid)
					.content(request.getContent())
					.time(LocalDateTime.now())
				.build();
		
		simpMessagingTemplate.convertAndSend("/public/advanced", response);
	}
	
}






