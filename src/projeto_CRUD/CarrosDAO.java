package projeto_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Kelly Ortiz e Welker
 */
public class CarrosDAO {
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    private Connection conn;
    private List<Carros> Carros;
   
    
    public CarrosDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/Projeto","root","");
            String url = "jdbc:derby://localhost:1527/Projeto";
            String usuario ="projeto";
            String senha = "projeto";
            this.stmC = this.conn.prepareStatement("INSERT INTO carros(modelo,marca,ano,categoria) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmR = this.conn.prepareStatement("SELECT * FROM carros");
            this.stmU = this.conn.prepareStatement("UPDATE carros SET modelo=?,marca=?,ano=?,categoria=?WHERE id=?");
            this.stmD = this.conn.prepareStatement("DELETE FROM carros WHERE id=?");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }    
    public void close(){
        try{
            this.conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
     public List<Carros> read() throws SQLException {
        try {
            ResultSet rs = this.stmR.executeQuery();
            
            List<Carros> carros = new ArrayList<>();
            
            while(rs.next()) {
                Carros c = new Carros();
                c.setId(rs.getLong("id"));
                c.setModelo(rs.getString("Modelo"));
                c.setMarca(rs.getString("Marca"));
                c.setAno(rs.getDate("Ano"));
                c.setCategoria(rs.getString("Categoria"));
                carros.add(c);
            
            
            return Carros;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
 
    public Carros create(Carros novoCarro) {
        try {
            this.stmC.setString(1, novoCarro.getModelo());
            this.stmC.setString(2, novoCarro.getMarca());
            this.stmC.setDate(3, novoCarro.getAno());
            this.stmC.setString(4, novoCarro.getCategoria());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            novoCarro.setId(id);
            
            return novoCarro;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
