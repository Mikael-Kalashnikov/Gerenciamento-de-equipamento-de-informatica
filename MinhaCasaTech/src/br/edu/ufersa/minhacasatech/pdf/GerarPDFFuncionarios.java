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

public class GerarPDFFuncionarios {
    public static void main(String[] args) throws FileNotFoundException {
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("relatorio_funcionarios.pdf"));
            document.open();

            // Conectar ao banco de dados (substitua com suas informações de conexão)
            Connection connection = getConnection();
            String query = "SELECT nome, cpf, endereco, contato, total_vendas, data_cadastro FROM funcionarios";
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
        String url = "jdbc:postgresql:projetopoo";
        String user = "postgres";
        String password = "netodev";
        return DriverManager.getConnection(url, user, password);
    }

    private static ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private static PdfPTable createTable() {
        PdfPTable table = new PdfPTable(6);

        table.addCell("Nome");
        table.addCell("CPF");
        table.addCell("Endereço");
        table.addCell("Contato");
        table.addCell("Total de Vendas");
        table.addCell("Data de Cadastro");

        return table;
    }

    private static void addDataToTable(PdfPTable table, ResultSet resultSet) throws SQLException {
        table.addCell(resultSet.getString("nome"));
        table.addCell(resultSet.getString("cpf"));
        table.addCell(resultSet.getString("endereco"));
        table.addCell(resultSet.getString("contato"));
        table.addCell(resultSet.getString("total_vendas"));
        table.addCell(resultSet.getString("data_cadastro"));
    }
}
