package com.ssafy.sample.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.sample.dto.Movie;
import com.ssafy.sample.model.service.MovieServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/movie")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		if ("list".equals(act)) {
			doList(request, response);
		} else if ("create-form".equals(act)) {
			forward(request, response, "/movie/regist.jsp");
		} else if ("create".equals(act)) {
			doCreate(request, response);
		} else if ("detail".equals(act)) {
			doDetail(request, response);
		} else if ("update-form".equals(act)) {
			dpUpdateForm(request, response);
		} else if ("update".equals(act)) {
			doUpdate(request, response);
		} else {
			doMain(request, response);
		}
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movie> list;
		try {
			list = MovieServiceImpl.getInstance().selectAll();
			request.setAttribute("movieList", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		forward(request, response, "/movie/list.jsp");
	}

	private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = new Movie();
		movie.setCode(request.getParameter("code"));
		movie.setTitle(request.getParameter("title"));
		movie.setTime(Integer.parseInt(request.getParameter("time")));
		movie.setDirector(request.getParameter("director"));
		movie.setGenre(request.getParameter("genre"));
		
		try {
			MovieServiceImpl.getInstance().insert(movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		redirect(request, response, "/movie?act=list");
	}
	
	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		try {
			Movie movie = MovieServiceImpl.getInstance().selectByCode(code);
			request.setAttribute("movie", movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		forward(request, response, "/movie/detail.jsp");
	}
	
	private void dpUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = new Movie();
		movie.setCode(request.getParameter("code"));
		movie.setTitle(request.getParameter("title"));
		movie.setTime(Integer.parseInt(request.getParameter("time")));
		movie.setDirector(request.getParameter("director"));
		movie.setGenre(request.getParameter("genre"));
		
		request.setAttribute("movie", movie);
		
		forward(request, response, "/movie/updateForm.jsp");		
	}
	
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = new Movie();
		movie.setCode(request.getParameter("code"));
		movie.setTitle(request.getParameter("title"));
		movie.setTime(Integer.parseInt(request.getParameter("time")));
		movie.setDirector(request.getParameter("director"));
		movie.setGenre(request.getParameter("genre"));
		
		try {
			MovieServiceImpl.getInstance().update(movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		redirect(request, response, "/movie?act=detail&code=" + movie.getCode());
	}
	
	private void doMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward(request, response, "/index.jsp");
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
}
