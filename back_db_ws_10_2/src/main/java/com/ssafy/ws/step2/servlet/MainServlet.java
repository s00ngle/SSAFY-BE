package com.ssafy.ws.step2.servlet;

import java.io.IOException;
import java.util.List;

import com.ssafy.ws.step2.dao.MovieDaoImpl;
import com.ssafy.ws.step2.dto.Movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public MainServlet() throws ServletException  {
        super();
        initDatabase();
    }
    private void initDatabase() {
    	try {
            MovieDaoImpl.getInstance().createTableIfNotExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        process(request, response);
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        System.out.println("act =====> " + act);
        
        String path = "";
        if ("mvregist".equals(act)) {
            path = mvregist(request, response);
            redirect(request, response, path);
		} else if ("regist".equals(act)) {
			path = regist(request, response);
			if ("/".equals(path)) {
				redirect(request, response, path);
			}
			else {				
				forward(request, response, path);
			}
		} else if ("list".equals(act)) {
			path = list(request, response);
			forward(request, response, path);
		} else {
			// 기본적으로 글 목록으로 이동
			path = "/";
			redirect(request, response, path);
		}
    }

    private String regist(HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
    		String title = request.getParameter("title");
        	String director = request.getParameter("director");
        	String genre = request.getParameter("genre");
        	int runningTime = Integer.parseInt(request.getParameter("running-time"));
        	
        	Movie movie = new Movie(0, title, director, genre, runningTime);
			MovieDaoImpl.getInstance().insertMovie(movie);
			
	    	request.setAttribute("movie", movie);
	    	
	    	// ---------------- movieCount Session 방식 ------------------------
	    	HttpSession session = request.getSession();
            Integer movieCount = (Integer) session.getAttribute("movieCount");
            if (movieCount == null) {
                movieCount = 0;
            }
            movieCount++;
            session.setAttribute("movieCount", movieCount);
            // -----------------------------------------------------------------
	    	
	    	return "/regist_result.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/";
		}
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Movie> movies = MovieDaoImpl.getInstance().selectAllMovies();
			request.setAttribute("movies", movies);
			
//			int cnt = MovieDaoImpl.getInstance().movieCount();
//			request.setAttribute("cnt", cnt);
			
			return "/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/";
		}
	}
    
    private String mvregist(HttpServletRequest request, HttpServletResponse response) {
        return "/regist.jsp";
    }
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
}
