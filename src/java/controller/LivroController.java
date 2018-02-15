package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Portal;
import model.Livro;
import model.Usuario;

@WebServlet(name = "LivroController", urlPatterns = {"/livro"})
public class LivroController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String id = request.getParameter("id");
        
        if(id == null || id.isEmpty()){
            request.setAttribute("livros", Portal.getLivros());
            request.getRequestDispatcher("livros.jsp").forward(request, response); 
        }
        else{     
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            request.setAttribute("livro", Portal.getLivro(id));
            request.setAttribute("lido", Portal.verificaSeFoiLido(usuario.getLogin(), id));
            
            String msg = (String)request.getSession().getAttribute("msgpontos");
            request.setAttribute("msgpontos", msg);
            request.getSession().setAttribute("msgpontos", "");
            request.getRequestDispatcher("livro.jsp").forward(request, response); 
        }
    } 
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String paginas = request.getParameter("paginas");
        String livro_id = request.getParameter("livro_id");
        String estilo = request.getParameter("estilo");
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String msg = Portal.marcarLivroComoLido(usuario.getLogin(), paginas, livro_id, estilo);
        
        request.getSession().setAttribute("msgpontos", msg);
        response.sendRedirect("/EsseEuJaLi/livro?id=" + livro_id);
     
    } 


}
