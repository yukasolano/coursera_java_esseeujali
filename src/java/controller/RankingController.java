package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "RankingController", urlPatterns = {"/ranking"})
public class RankingController extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> ranking = new UsuarioDAO().ranking();
        request.setAttribute("ranking", ranking);
        request.getRequestDispatcher("ranking.jsp").forward(request, response);  
    } 
}
