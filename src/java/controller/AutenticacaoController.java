package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOException;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(urlPatterns = {"/autenticacao"})
public class AutenticacaoController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            Usuario usuario = new UsuarioDAO().autenticar(login,senha);
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect("/EsseEuJaLi/livro");
        }catch (DAOException e) {
            request.setAttribute("mensagem", "Falha na autenticação do usuário");
            request.getRequestDispatcher("login.jsp").forward(request, response);  
        } 
    }  
}