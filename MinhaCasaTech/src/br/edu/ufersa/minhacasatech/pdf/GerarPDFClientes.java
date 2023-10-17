package br.edu.ufersa.minhacasatech.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GerarPDFClientes {
    public static void main(String[] args) throws FileNotFoundException {
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("relatorio_clientes.pdf"));
            document.open();

            // Conectar ao banco de dados (substitua com suas informações de conexão)
            Connection connection = getConnection();
            String query = "SELECT nome, cpf, endereco, contato, data_cadastro FROM clientes";
            ResultSet resultSet = executeQuery(connection, query);

            PdfPTable table = createTable();

            while (resultSet.next()) {
                addDataToTable(table, resultSet);
            }

            document.add(table);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/seu_banco_de_dados";
        String user = "seu_usuario";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    private static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private static PdfPTable createTable() {
        PdfPTable table = new PdfPTable(5); // 5 colunas

        table.addCell("Nome");
        table.addCell("CPF");
        table.addCell("Endereço");
        table.addCell("Contato");
        table.addCell("Data de Cadastro");

        return table;
    }

    private static void addDataToTable(PdfPTable table, ResultSet resultSet) throws SQLException {
        table.addCell(resultSet.getString("nome"));
        table.addCell(resultSet.getString("cpf"));
        table.addCell(resultSet.getString("endereco"));
        table.addCell(resultSet.getString("contato"));
        table.addCell(resultSet.getString("data_cadastro"));
    }

    public void gerarRelatorio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
