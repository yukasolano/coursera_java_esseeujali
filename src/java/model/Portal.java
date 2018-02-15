package model;

import java.util.List;

public class Portal {
    
    
    public static String marcarLivroComoLido(String usuario, String paginas, String livro_id, String estilo){
        int pontuacao = 1 + Integer.parseInt(paginas)/100;
        UsuarioDAO dao = new UsuarioDAO();
        String msg = "Parabéns! Você recebeu " + pontuacao + " pontos.";
        dao.adicionarPontos(usuario, pontuacao);
        dao.marcarLivroComoLido(usuario, Integer.parseInt(livro_id));
        if(dao.getQtdDeLivrosLidoPorEstilo(usuario, estilo)  == 5){
            dao.adicionarTrofeu(usuario, estilo);
            msg += " E ganhou o troféu: Leitor de " + estilo + ".";
        }
        return msg;
    }
    
    public static boolean verificaSeFoiLido(String usuario, String livro_id){
        return new UsuarioDAO().verificaSeLivroFoiLido(usuario, Integer.parseInt(livro_id));        
    }
    
    public static Usuario recuperaUsuarioAtualizado(String login){
        return new UsuarioDAO().recuperar(login);        
    }
    
    public static List<Livro> getLivros(){
        return new LivroDAO().lista();
    }
    
    public static Livro getLivro(String id){ 
        return new LivroDAO().getLivro(Integer.parseInt(id));
    }

}
