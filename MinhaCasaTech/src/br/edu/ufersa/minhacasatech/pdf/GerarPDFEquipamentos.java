package br.edu.ufersa.minhacasatech.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GerarPDFEquipamentos {
    public static void main(String[] args) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("relatorio_equipamentos.pdf"));
            document.open();

            String url = "jdbc:postgresql:projetopoo";
            String user = "postgres";
            String password = "netodev";
            Connection connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT numero_serie, equipamento, preco, quantidade, local, responsavel FROM equipamentos";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            PdfPTable table = new PdfPTable(6);

            PdfPCell header1 = new PdfPCell(new Paragraph("Nº de Série"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Equipamento"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Preço"));
            PdfPCell header4 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell header5 = new PdfPCell(new Paragraph("Local"));
            PdfPCell header6 = new PdfPCell(new Paragraph("Responsável"));

            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);
            table.addCell(header4);
            table.addCell(header5);
            table.addCell(header6);

            while (resultSet.next()) {
                String numeroSerie = resultSet.getString("numero_serie");
                String equipamento = resultSet.getString("equipamento");
                String preco = resultSet.getString("preco");
                String quantidade = resultSet.getString("quantidade");
                String local = resultSet.getString("local");
                String responsavel = resultSet.getString("responsavel");

                table.addCell(numeroSerie);
                table.addCell(equipamento);
                table.addCell(preco);
                table.addCell(quantidade);
                table.addCell(local);
                table.addCell(responsavel);
            }

            document.add(table);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}