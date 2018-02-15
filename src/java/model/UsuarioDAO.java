package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UsuarioDAO {

    private static final String database = Conn.getDatabase();
    private static final String user = Conn.getUser();
    private static final String password = Conn.getPassword();

    public void inserir(Usuario u) {	        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO usuario(login, nome, senha, pontuacao) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getNome());
            stm.setString(3, u.getSenha());
            stm.setInt(4, u.getPontos());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public Usuario recuperar(String login) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario WHERE login = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                        rs.getString("senha"),rs.getInt("pontuacao")) ;
                u.setTrofeus(getTrofeus(login));           
                return u;
            }			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar usuário: " + e.getMessage());
        }
        return null;
    }
    
    public List<String> getTrofeus(String login) {
        List<String> trofeus = new ArrayList<String>();
        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM trofeu WHERE login = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                trofeus.add(rs.getString("trofeu"));

            }  
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao recuperar lista de troféus: " + e.getMessage());
        }
        return trofeus;
    }

    public Usuario autenticar(String login, String senha) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario WHERE login = ? AND senha = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setString(2, senha);
            ResultSet rs = stm.executeQuery();	
            if(rs.next()){
                Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                    rs.getString("senha"),rs.getInt("pontuacao")) ;
                return u;
            }
            else{
                throw new DAOException("Falha na autenticação!");
            }
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao recuperar usuário: " + e.getMessage());
        }
    }

    public void adicionarPontos(String login, int pontos) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "UPDATE usuario SET pontuacao = pontuacao + ? WHERE login = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, pontos);
            stm.setString(2, login);
            stm.executeUpdate();			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao adicionar pontos ao usuário: " + e.getMessage());
        }
    }
    
    public void adicionarTrofeu(String login, String estilo) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO trofeu (login, trofeu) VALUES (?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setString(2, "Leitor de " + estilo);
            stm.executeUpdate();			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao adicionar troféu ao usuário: " + e.getMessage());
        }
    }

    public List<Usuario> ranking() {	
        List<Usuario> ranking = new ArrayList<>();	
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM usuario ORDER BY pontuacao DESC LIMIT 10;";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();	
            while(rs.next()){
                    Usuario u = new Usuario(rs.getString("login"),rs.getString("nome"), 
                        rs.getString("senha"),rs.getInt("pontuacao"));
                    ranking.add(u);
            }		
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao recuperar lista de usuários: " + e.getMessage());
        }
        return ranking;
    }
    
    public void marcarLivroComoLido(String login, int livro_id) {
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "INSERT INTO livro_lido (login, livro) VALUES (?, ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setInt(2, livro_id);
            stm.executeUpdate();			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Erro ao marcar livro como lido: " + e.getMessage());
        }
    }
    
    public boolean marcarLivroComoNaoLido(String login, int livro_id) {
        boolean lido = false;
        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "DELETE FROM livro_lido WHERE login = ? AND livro = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setInt(2, livro_id);
            stm.executeUpdate(); 
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao marcar livro como não lido: " + e.getMessage());
        }
        return lido;
    }
    
    public boolean verificaSeLivroFoiLido(String login, int livro_id) {
        boolean lido = false;
        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT * FROM livro_lido WHERE login = ? AND livro = ?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setInt(2, livro_id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                lido = true;
            }  
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao verificar se livro foi lido: " + e.getMessage());
        }
        return lido;
    }

    public int getQtdDeLivrosLidoPorEstilo(String login, String estilo) {
        int quantidade = 0;
        
        try(Connection con = DriverManager.getConnection(database, user, password)){
            String query = "SELECT count(*) FROM livro_lido INNER JOIN livro ON livro.id=livro_lido.livro where livro_lido.login=? AND livro.estilo=?;";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, login);
            stm.setString(2, estilo);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                quantidade = rs.getInt("count");
            }  
        } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DAOException("Erro ao contar quantiade de estilos lidos por usuário: " + e.getMessage());
        }
        return quantidade;
    }
}
