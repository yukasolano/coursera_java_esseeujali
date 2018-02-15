package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Portal;
import model.Usuario;

@WebServlet(name = "PerfilControler", urlPatterns = {"/perfil"})
public class PerfilController extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Usuario usuarioAtualizado = Portal.recuperaUsuarioAtualizado(usuario.getLogin());
        request.setAttribute("pontuacao", usuarioAtualizado.getPontos());
        request.setAttribute("trofeus", usuarioAtualizado.getTrofeus());
        request.getRequestDispatcher("perfil.jsp").forward(request, response);  
    } 
}
