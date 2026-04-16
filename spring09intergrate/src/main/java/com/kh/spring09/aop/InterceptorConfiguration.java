package com.kh.spring09.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//application.properties에 하기 어려운 설정들(계산이 필요한 경우)
//인터셉터 등 홈페이지의 운영과 관련된 설정은 반드시 상속이 필요
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

   
	//등록한 인터셉터들을 가져오도록 설정하고
	@Autowired
	
	private TestInterceptor testInterceptor;
	
	@Autowired
	private MemberOnlyInterceptor memberOnlyInterceptor;

    @Autowired
    private MasterOnlyInterceptor masterOnlyInterceptor;
    @Autowired
    private MasterDenyInterceptor masterDenyInterceptor;
	
	//가져온 인터셉터를 특정 주소에서 일하도록 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//레지스트리에 테스트인터셉터가 모든주소에서 일할 수 있다고 작성해두세요
		registry.addInterceptor(testInterceptor).addPathPatterns("/**");
		//주소(path patterns)작성 규칙
		//spring 표현식의 규칙을 따른다.
//		*을 1개 또는 2개까지 사용 가능하다.
//		*을 1개 쓰면 현재 작성한 엔드포인트 내에서만 범위 설정이 가능
//		*을 2개 쓰면 현재 엔드포인트부터 하위 엔드포인트를 모두 포함한 범위 설정이 가능
//		(ex) 국가 정보와 관련된 모든 페이지를 타겟으로 설정하고 싶다면
		// /country/*로 설정
		// /country/insert, /country/list, /country/edit...
		// 상세 페이지가 /country/detail?countryNo=1이 아니고 /country/detail/1이라면?
		// /country/**로 설정
		
		registry.addInterceptor(memberOnlyInterceptor)
		.addPathPatterns("/book/**",
				//"/course/insert*","/course/edit","/course/delete");
				"/course/**","/member/**"
				,"/admin/**"
				)
		.excludePathPatterns(
						"/course/list",
						"/course/detail",
						"/member/join*",
						"/member/login",
						"/member/goodbyeFinish"
						);//허용하는 것만 빼고 잠가라
	
			registry.addInterceptor(masterOnlyInterceptor)
				.addPathPatterns("/admin/**");
	
			
			registry.addInterceptor(masterDenyInterceptor)
			.addPathPatterns(
					"/admin/member/detail"
					,"/admin/member/edit"
					,"/admin/member/block"
					
					
					);
	
	}
	
}
