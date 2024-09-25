package com.ssafy.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ErrorServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 에러 정보 가져오기
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        response.setContentType("text/html;charset=UTF-8");
        
        try (var out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>오류 발생</h1>");
            
            if (throwable != null) {
                out.println("<p>오류 메시지: " + throwable.getMessage() + "</p>");
            } else if (statusCode != null) {
                out.println("<p>상태 코드: " + statusCode + "</p>");
            }
            
            out.println("<p><a href='/basic_jsp/index.jsp'>홈으로 돌아가기</a></p>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
