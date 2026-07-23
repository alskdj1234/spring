package com.kh.spring11.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.annotation.CurrentUser;
import com.kh.spring11.dao.AccountDao;
import com.kh.spring11.dto.AccountDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.service.JwtService;
import com.kh.spring11.vo.account.AccountFindResponseVO;
import com.kh.spring11.vo.account.AccountJoinRequestVO;
import com.kh.spring11.vo.account.AccountJoinResponseVO;
import com.kh.spring11.vo.account.AccountMeResponseVO;
import com.kh.spring11.vo.account.ChangeAccountRequestVO;
import com.kh.spring11.vo.account.ChangeAccountResponseVO;
import com.kh.spring11.vo.account.ChangePasswordRequestVO;
import com.kh.spring11.vo.account.ChangePasswordResponseVO;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "회원 정보 관리 서비스")
@CommonsApiResponse

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private JwtService jwtService;
//	@Autowired
//	private ChangeInfoService changeInfoService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원가입
	@ApiResponse(responseCode = "200", description = "가입 성공")
	@PostMapping(value = "/", produces = "application/json")
	public AccountJoinResponseVO join(@RequestBody AccountJoinRequestVO request) {
		// AccountDto에 AccountJoinRequestVO의 데이터를 복사하고 가입 처리
		AccountDto accountDto = new AccountDto();
		BeanUtils.copyProperties(request, accountDto);// request → accountDto
		accountDao.insert(accountDto);
		// 가입된 결과(모든 데이터가 포함된)를 가져와서 응답 정보로 변환하여 반환
		AccountDto resultDto = accountDao.selectOne(accountDto.getAccountId());
		AccountJoinResponseVO response = new AccountJoinResponseVO();
		BeanUtils.copyProperties(resultDto, response);
		return response;
	}

	// 아이디 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-id/{accountId}")
	public boolean checkAccountId(@PathVariable String accountId) {
		return accountDao.checkAvailableId(accountId);
	}

	// 닉네임 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-nickname/{accountNickname}")
	public boolean checkAccountNickname(@PathVariable String accountNickname) {
		return accountDao.checkAvailableNickname(accountNickname);
	}

	// 이메일 중복검사 - 사용 가능하면 true, 불가능하면 false를 반환
	@GetMapping("/check-email/{accountEmail}")
	public boolean checkAccountEmail(@PathVariable String accountEmail) {
		return accountDao.checkAvailableEmail(accountEmail);
	}

	// 회원 정보를 반환하는 매핑(주의 : 내정보 아님)
	@ApiResponse(responseCode = "200", description = "조회 성공")
	@GetMapping(value = "/{accountId}", produces = "application/json")
	public AccountFindResponseVO find(@PathVariable String accountId) {
		AccountDto accountDto = accountDao.selectOne(accountId);
		if (accountDto == null)
			throw new TargetNotfoundException();

		AccountFindResponseVO response = new AccountFindResponseVO();
		BeanUtils.copyProperties(accountDto, response);// 가능한 항목 복사
		return response;
	}

	// 내정보라는건 cookie에 포함된 loginId를 읽으면 된다(지금은...나중엔 변하겠지만)
	// @CookieValue로 쿠키의 값을 읽어서 해당하는 정보를 조회해서 반환
	// stateless(무상태) 서버의 세션 대체 방안
	@ApiResponse(responseCode = "200", description = "조회 성공")
	@GetMapping(value = "/me", produces = "application/json")
	public AccountMeResponseVO me(
//		accessToken이라는 쿠키를 읽는 명령 (+나의 해석 및 검증이 필요)
//		@CookieValue(value = "accessToken") String accessToken

//		Spring Security가 해석해낸 JWT를 가져오는 명령 (+이미 해석되어 있음)
//		@AuthenticationPrincipal Jwt jwt

//		아예 무슨 명령을 써야 변환되는지까지 알려주고 최종형태를 달라고 해보자!
//		@AuthenticationPrincipal(
//			expression = "@jwtService.parseAccessToken(#this.tokenValue)"
//		)
			@CurrentUser TokenParseResponseVO parseVO) {
		// 토큰 해석 + 유효성 검증 = @CookieValue로 읽었을 때
		// TokenParseResponseVO parseVO = jwtService.parseAccessToken(accessToken);

		// @AutenticationPrincipal과 같이 쓰는 명령
		// 토큰을 내가 원하는 형태로 변환만 (+유효성 검증은 하지 않음, JwtDecoder 사용하지 않음)
		// TokenParseResponseVO parseVO = jwtService.parseAccessToken(jwt);

		AccountDto accountDto = accountDao.selectOne(parseVO.getAccountId());
		if (accountDto == null)
			throw new TargetNotfoundException();

		AccountMeResponseVO response = new AccountMeResponseVO();
		BeanUtils.copyProperties(accountDto, response);// 가능한 항목 복사
		return response;
	}

//	@ApiResponse(responseCode = "200", description = "비번 수정 성공")
//	@PostMapping(value = "/password", produces = "application/json")
//	public AccountChangePasswordResponseVO changePassword(@CookieValue(name = "accessToken", required = false) String accessToken, // 1. 쿠키에서 토큰 받기
//	        @RequestBody AccountChangePasswordVO passwordVO
//			) throws MessagingException, IOException {
//
//			    // 2. 토큰 없으면 인증 에러 던지기
//			    if (accessToken == null) {
//			        throw new WhoAreYouException();
//			    }
//
//			    // 3. 이미 만들어둔 jwtService로 토큰 해석해서 아이디 추출
//			    TokenParseResponseVO parseVO = jwtService.parseAccessToken(accessToken);
//			    
//			    // 4. 추출한 아이디를 VO에 세팅 (프론트가 넘긴 아이디 차단)
//			    passwordVO.setAccountId(parseVO.getAccountId());
//
//			    // 5. 서비스 호출해서 비밀번호 변경 실행
//			    String changed = changeInfoService.changePassword(passwordVO);
//			    
//			    AccountChangePasswordResponseVO vo = new AccountChangePasswordResponseVO();
//			    vo.setAccountPassword(changed);
//			    return vo;
//		
//	}

	@PatchMapping("/password")
	public ChangePasswordResponseVO password(@Valid @RequestBody ChangePasswordRequestVO request,
			@CurrentUser TokenParseResponseVO parseVO) {
		AccountDto accountDto = accountDao.selectOne(parseVO.getAccountId());
		if (accountDto == null)
			throw new TargetNotfoundException();

		String db = accountDto.getAccountPassword();
		String input = request.getPrevAccountPassword();
		boolean valid = passwordEncoder.matches(input, db);// Bcrypt 비교
		if (valid == false) {
			return ChangePasswordResponseVO.builder().result(false).message("비밀번호가 일치하지 않아요").build();
		}

		boolean same = request.getPrevAccountPassword().equals(request.getNewAccountPassword());
		if (same) {
			return ChangePasswordResponseVO.builder().result(false).message("이미 설정한 비밀번호에요").build();
		}

		// [4] 형식 검사
		String regex = "^(?=.*?[A-Z]+)(?=.*?[a-z]+)(?=.*?[0-9]+)(?=.*?[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\'\\\"\\`\\~\\<\\>\\.\\,\\/\\?\\\\\\|]+)[A-Za-z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\]\\{\\}\\'\\\"\\`\\~\\<\\>\\.\\,\\/\\?\\\\\\|]{8,16}$";
		if (request.getNewAccountPassword().matches(regex) == false) {
			return ChangePasswordResponseVO.builder().result(false)
					.message("비밀번호는 대문자, 소문자, 숫자, 특수문자를 반드시 포함하여 8~16자로 작성하세요").build();
		}

		accountDao.updateAccountPassword(accountDto.builder().accountId(parseVO.getAccountId())
				.accountPassword(request.getNewAccountPassword()).build());

		return ChangePasswordResponseVO.builder().result(true).message("변경 완").build();
	}

	// 회원 정보 수정 (본인)
	@PutMapping("/")
	public ChangeAccountResponseVO updateAll(@CurrentUser TokenParseResponseVO parseVO,
		@Valid	@RequestBody ChangeAccountRequestVO request) {
		//정보 조회 후 없으면 404
		AccountDto accountDto = accountDao.selectOne(parseVO.getAccountId());
		if(accountDto == null)throw new TargetNotfoundException();
		
		//정보 갈아 끼우기
		BeanUtils.copyProperties(request, accountDto);
		
		//추가 검증이 필요하면 여기에 작성후 거절 상태를 반환
		
		boolean passwordValid = passwordEncoder.matches(request.getAccountPassword(), accountDto.getAccountPassword());
		if(!passwordValid) {
			return ChangeAccountResponseVO.builder()
					.status(false)
					.message("비밀번호가 일치하지 않습니다.")
					.build();
		}
		
		//수정
		accountDao.updateAll(accountDto);
		
		
		return ChangeAccountResponseVO.builder()
				.status(true)
				.message("정보 변경 완")
				.build();
	}
}
