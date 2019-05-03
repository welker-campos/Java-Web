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
public class JogoCrud {
            public static void main (String[] args) throws SQLException, ClassNotFoundException{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Drive JDBC carregado!");
            String url = "jdbc:derby://localhost:1527/Carros_CRUD";
            String usuario ="projeto";
            String senha = "projeto";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso!");
            String sqlInsert = "INSERT INTO jogos(timeA,timeB,golsA,golsB)";
            sqlInsert += "VALUES (?,?,?,?)";
            String sqlSelect = "SELECT * FROM jogos";
            String sqlUpdate = "UPDATE jogos SET timeA=?,timeB=?,golsA=?,golsB=?";
            sqlUpdate += "WHERE id=?";
            String sqlDelete = "DELETE FROM jogos WHERE id=?";
            PreparedStatement stmInsert = conexao.prepareStatement(sqlInsert);
            PreparedStatement stmSelect = conexao.prepareStatement(sqlSelect);
            PreparedStatement stmUptade = conexao.prepareStatement(sqlUpdate);
            PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);
            boolean querSair = false;
            Scanner sc = new Scanner(System.in);
            while(!querSair){
                System.out.println("\nMenu:");
                System.out.println("(1) Adicionar jogo: ");
                System.out.println("(2) Verificar jogo: ");
                System.out.println("(3) Atualizar dados do jogo: ");
                System.out.println("(4) Deletar jogo ");
                System.out.println("(5) Sair");
                System.out.println("Opção escolhida: ");
                int opcao = Integer.parseInt(sc.nextLine());
                if (opcao == 1){
                    System.out.println("Nome do novo time A: ");
                    String timeA = sc.nextLine();
                    System.out.println("Nome do novo time B: ");
                    String timeB = sc.nextLine();  
                    System.out.println("Gols do time A: ");
                    String golsA = sc.nextLine(); 
                    System.out.println("Gols do time B: ");
                    String golsB = sc.nextLine();
                    stmInsert.setString(1, timeA);
                    stmInsert.setString(2,timeB);
                    stmInsert.setString(3,golsA);
                    stmInsert.setString(4,golsB);
                    int retorno = stmInsert.executeUpdate();
                    System.out.println("Jogos inseridos: " + retorno);
                }
                else if (opcao == 2){
                    ResultSet rs = stmSelect.executeQuery();
                    System.out.println("Jogos:");
                    while(rs.next()){
                        Long id = rs.getLong("id");
                        String timeA = rs.getString("Nome do time A");
                        String timeB = rs.getString("Nome do time B");
                        String golsA = rs.getString("Gols do time A");
                        String golsB = rs.getString("Gols do time B");
                        System.out.println(id  + " - " + timeA + " - " + golsA
                                            + " - " + timeB + " - " + golsB);
                    }
                    rs.close();
                }
                else if (opcao == 3){
                    System.out.println("id do registro a ser atualizado: ");
                    long id = Long.parseLong(sc.nextLine());
                    System.out.println("Atualização do nome do time A: ");
                    String timeA = sc.nextLine();
                    System.out.println("Atualização do nome do time B ");
                    String timeB = sc.nextLine();
                    System.out.println("Atualização do gols do time A ");
                    String golsA = sc.nextLine();
                    System.out.println("Atualização do gols do time B: ");
                    String golsB = sc.nextLine();
                    stmInsert.setString(1, timeA);
                    stmInsert.setString(2,timeB);
                    stmInsert.setString(3,golsA);
                    stmInsert.setString(4,golsB);
                    int retorno = stmUptade.executeUpdate();
                    System.out.println("Jogos atualizados: " + retorno);
                }
                else if (opcao == 4){
                    System.out.println("id do registro a ser apagado: ");
                    long id = Long.parseLong(sc.nextLine());
                    stmDelete.setLong(1,id);
                    int retorno = stmDelete.executeUpdate();
                    System.out.println("Jogos apagados: " + retorno);
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


