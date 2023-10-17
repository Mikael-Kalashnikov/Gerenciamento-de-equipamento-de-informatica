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

public class GerarPDFVendas {
    public static void main(String[] args) throws FileNotFoundException {
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("relatorio_vendas.pdf"));
            document.open();

            // Conectar ao banco de dados (substitua com suas informações de conexão)
            Connection connection = getConnection();
            String query = "SELECT c.nome AS cliente, e.equipamento, v.quantidade, v.data, v.status, v.responsavel, v.total FROM vendas v " +
                    "JOIN clientes c ON v.cliente_id = c.id " +
                    "JOIN equipamentos e ON v.equipamento_id = e.id";
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
        PdfPTable table = new PdfPTable(7); // 7 colunas

        table.addCell("Cliente");
        table.addCell("Equipamento");
        table.addCell("Quantidade");
        table.addCell("Data");
        table.addCell("Status");
        table.addCell("Responsável");
        table.addCell("Total");

        return table;
    }

    private static void addDataToTable(PdfPTable table, ResultSet resultSet) throws SQLException {
        table.addCell(resultSet.getString("cliente"));
        table.addCell(resultSet.getString("equipamento"));
        table.addCell(resultSet.getString("quantidade"));
        table.addCell(resultSet.getString("data"));
        table.addCell(resultSet.getString("status"));
        table.addCell(resultSet.getString("responsavel"));
        table.addCell(resultSet.getString("total"));
    }
}
