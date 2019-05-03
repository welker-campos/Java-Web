package projeto_CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author Kelly e Walker
 */
public class CarrosCrud {
        public static void main (String[] args) throws SQLException, ClassNotFoundException{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Drive JDBC carregado!");
            String url = "jdbc:derby://localhost:1527/Carros_CRUD";
            String usuario ="projeto";
            String senha = "projeto";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("COnexão estabelecida com sucesso!");
            String sqlInsert = "INSERT INTO carros(modelo,marca,ano,categoria)";
            sqlInsert += "VALUES (?,?,?,?)";
            String sqlSelect = "SELECT * FROM carros";
            String sqlUpdate = "UPDATE carros SET modelo=?,marca=?,ano=?,categoria=?";
            sqlUpdate += "WHERE id=?";
            String sqlDelete = "DELETE FROM carros WHERE id=?";
            PreparedStatement stmInsert = conexao.prepareStatement(sqlInsert);
            PreparedStatement stmSelect = conexao.prepareStatement(sqlSelect);
            PreparedStatement stmUptade = conexao.prepareStatement(sqlUpdate);
            PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);
            boolean querSair = false;
            Scanner sc = new Scanner(System.in);
            while(!querSair){
                System.out.println("\nMenu:");
                System.out.println("(1) Adicionar carro: ");
                System.out.println("(2) Verificar carros: ");
                System.out.println("(3) Atualizar dados do carro: ");
                System.out.println("(4) Deletar carro: ");
                System.out.println("(5) Sair");
                System.out.println("Opção escolhida: ");
                int opcao = Integer.parseInt(sc.nextLine());
                if (opcao == 1){
                    System.out.println("Modelo do novo carro: ");
                    String modelo = sc.nextLine();
                    System.out.println("Marca do novo carro: ");
                    String marca = sc.nextLine();  
                    System.out.println("Ano do novo carro: ");
                    String ano = sc.nextLine(); 
                    System.out.println("Categoria do novo carro: ");
                    String categoria = sc.nextLine(); 
                    stmInsert.setString(1, modelo);
                    stmInsert.setString(2,marca);
                    stmInsert.setString(3,ano);
                    stmInsert.setString(4,categoria);
                    int retorno = stmInsert.executeUpdate();
                    System.out.println("Carros inseridos: " + retorno);
                }
                else if (opcao == 2){
                    ResultSet rs = stmSelect.executeQuery();
                    System.out.println("Carros:");
                    while(rs.next()){
                        Long id = rs.getLong("id");
                        String modelo = rs.getString("modelo");
                        String marca = rs.getString("marca");
                        String ano = rs.getString("ano");
                        String categoria = rs.getString("categoria");
                        System.out.println(id  + " - " + modelo + " - " + marca
                                            + " - " + ano + " - " + categoria);
                    }
                    rs.close();
                }
                else if (opcao == 3){
                    System.out.println("id do registro a ser atualizado: ");
                    long id = Long.parseLong(sc.nextLine());
                    System.out.println("Atualização do modelo: ");
                    String modelo = sc.nextLine();
                    System.out.println("Atualização da marca: ");
                    String marca = sc.nextLine();
                    System.out.println("Atualização do ano: ");
                    String ano = sc.nextLine();
                    System.out.println("Atualização da categoria: ");
                    String categoria = sc.nextLine();
                    stmInsert.setString(1, modelo);
                    stmInsert.setString(2,marca);
                    stmInsert.setString(3,ano);
                    stmInsert.setString(4,categoria);
                    int retorno = stmUptade.executeUpdate();
                    System.out.println("Carros atualizados: " + retorno);
                }
                else if (opcao == 4){
                    System.out.println("id do registro a ser apagado: ");
                    long id = Long.parseLong(sc.nextLine());
                    stmDelete.setLong(1,id);
                    int retorno = stmDelete.executeUpdate();
                    System.out.println("Carros apagados: " + retorno);
                }
                else if (opcao == 5){
                    querSair = true;
                }
                else{
                    System.out.println("Opção inválida!");
                }
            }
            conexao.close();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("Driver não encontrado!");
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Erro de conexão");
        }
    }
}

