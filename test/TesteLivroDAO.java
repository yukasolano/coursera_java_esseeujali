import model.Conn;
import model.Livro;
import model.LivroDAO;
import static org.junit.Assert.*;

import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;


public class TesteLivroDAO {

    JdbcDatabaseTester jdt;
    LivroDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new LivroDAO();
        jdt = new JdbcDatabaseTester(Conn.getDriver(),Conn.getDatabase(), Conn.getUser(), Conn.getPassword());
        jdt.setDataSet(new FlatXmlDataFileLoader().load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void insereLivro() throws Exception{

        Livro livro = new Livro("O pequeno príncipe", "Antoine de Saint-Exupéry", "Fantasia", 98);
        dao.inserir(livro);

        ITable currentTable = jdt.getConnection().createDataSet().getTable("LIVRO");
        IDataSet expectedDataset = new FlatXmlDataFileLoader().load("/verificaInsercaoLivro.xml");
        ITable expectedTable = expectedDataset.getTable("LIVRO");
        ITable filteredCurrentTable = DefaultColumnFilter.excludedColumnsTable(currentTable, new String[]{"id"});
        ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(expectedTable, new String[]{"id"});
        Assertion.assertEquals(filteredExpectedTable, filteredCurrentTable);
    }

    
    @Test
    public void listaLivros() throws Exception{
        List<Livro> livros = dao.lista();

        assertEquals(1, livros.size());
        assertEquals("O senhor dos anéis", livros.get(0).getTitulo()); 
        assertEquals("J. R. R. Tolkien", livros.get(0).getEscritor());
        assertEquals("Fantasia", livros.get(0).getEstilo());
        assertEquals(1202, livros.get(0).getPaginas());
    }
    
    @Test
    public void recuperaLivro() throws Exception{
        Livro livro = dao.getLivro(1);

        assertEquals("O senhor dos anéis", livro.getTitulo());  
        assertEquals("J. R. R. Tolkien", livro.getEscritor());
        assertEquals("Fantasia", livro.getEstilo());
        assertEquals(1202, livro.getPaginas());
        
    }


}
