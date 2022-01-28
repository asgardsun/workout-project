package custom.security;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {

		System.out.println("Login seuccess");
		List<String> roleNames = new ArrayList<>();
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});

		request.setAttribute("role", roleNames);
		//하던 곳으로 그대로 이동(아무것도 설정 안함)
		response.sendRedirect("/");
	}
	

}
