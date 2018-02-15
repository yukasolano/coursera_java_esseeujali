package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LivroDAO  {
    private static final String database = Conn.getDatabase();
    private static final String user = Conn.getUser();
    private static final String password = Conn.getPassword();
    
    public void inserir(Livro livro) {		
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO livro(titulo, escritor, estilo, paginas) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, livro.getTitulo());
            stm.setString(2, livro.getEscritor());
            stm.setString(3, livro.getEstilo()); 
            stm.setInt(4, livro.getPaginas());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao inserir livro: " + e.getMessage());
        }
    }
    
    public List<Livro> lista() {	
        List<Livro> livros = new ArrayList<>();	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM livro;";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();	
            while(rs.next()){
                    Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"),rs.getString("escritor"),rs.getString("estilo"), rs.getInt("paginas"));
                    livros.add(livro);
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar lista de livros: " + e.getMessage());
        }
        return livros;
    }
    
    public Livro getLivro(int id) {	
        Livro livro = null;	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM livro WHERE id = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                    livro = new Livro(rs.getInt("id"), rs.getString("titulo"),rs.getString("escritor"),rs.getString("estilo"), rs.getInt("paginas"));
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar livro: " + e.getMessage());
        }
        return livro;
    }
    
}
