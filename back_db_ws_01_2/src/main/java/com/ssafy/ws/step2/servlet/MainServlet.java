package com.ssafy.ws.step2.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public MainServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        process(request, response);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("regist".equals(action)) {
            doRegist(request, response);
        }
    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 각 파라미터를 개별적으로 request attribute로 설정
        request.setAttribute("title", request.getParameter("title"));
        request.setAttribute("director", request.getParameter("director"));
        request.setAttribute("genre", request.getParameter("genre"));
        request.setAttribute("runningTime", request.getParameter("running-time"));

        // regist_result.jsp로 포워드
        RequestDispatcher dispatcher = request.getRequestDispatcher("/regist_result.jsp");
        dispatcher.forward(request, response);
    }
}
