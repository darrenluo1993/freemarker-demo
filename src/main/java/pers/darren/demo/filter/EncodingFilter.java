package pers.darren.demo.filter;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter {

	private static final long serialVersionUID = -4639069715927914838L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(UTF_8.name());
		res.setCharacterEncoding(UTF_8.name());

		chain.doFilter(req, res);
	}
}