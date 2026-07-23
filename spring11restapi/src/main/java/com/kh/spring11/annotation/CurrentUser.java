package com.kh.spring11.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@AuthenticationPrincipal(
	expression = "@jwtService.parseAccessToken(#this.tokenValue)"
)
public @interface CurrentUser {

}
