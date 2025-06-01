package service;

import java.sql.Connection;
import model.Buku;
public class MysqlBukuService {
    
    private Connection koneksi;

    public MysqlBukuService() {
        // Initialize the connection to the MySQL database
        this.koneksi = util.MysqlUtilities.getConnection();
    }


    public Buku createBuku() {
        return new Buku();
    }
    // Implement methods to interact with the MySQL database for Buku operations
    // For example, methods to add, update, delete, and retrieve Buku records

    public void addBuku(Buku buku) {
        // Code to add a new Buku record to the database
        String sql = "INSERT INTO buku (judul, penulis, isFiksi) VALUES (?, ?, ?)";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, buku.getJudul());
            preparedStatement.setString(2, buku.getPenulis());
            preparedStatement.setString(3, buku.getIsFiksi() ? "Ya" : "Tidak");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBuku(Buku buku) {
        // Code to update an existing Buku record in the database
        String sql = "UPDATE buku SET judul = ?, penulis = ?, isFiksi = ? WHERE id_buku = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            // Assuming buku is an instance of Buku with updated values
            preparedStatement.setString(1, buku.getJudul());
            preparedStatement.setString(2, buku.getPenulis());
            preparedStatement.setString(3, buku.getIsFiksi() ? "Ya" : "Tidak");
            preparedStatement.setInt(4, buku.getId_buku());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBuku(Buku buku) {
        // Code to delete a Buku record from the database
        String sql = "DELETE FROM buku WHERE id_buku = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setInt(1, buku.getId_buku());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.util.ArrayList<Buku> getAllBuku() {
        java.util.ArrayList<Buku> listBuku = new java.util.ArrayList<>();
        String sql = "SELECT * FROM buku";
        try {
            var statement = koneksi.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Buku buku = new Buku();
                buku.setId_buku(resultSet.getInt("id_buku"));
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setIsFiksi("Ya".equalsIgnoreCase(resultSet.getString("isFiksi")));
                listBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBuku;
    }

    public java.util.ArrayList<Buku> getBukuByJudul(String judul) {
        java.util.ArrayList<Buku> listBuku = new java.util.ArrayList<>();
        String sql = "SELECT * FROM buku WHERE judul = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, judul);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Buku buku = new Buku();
                buku.setId_buku(resultSet.getInt("id_buku"));
                buku.setJudul(resultSet.getString("judul"));
                buku.setPenulis(resultSet.getString("penulis"));
                buku.setIsFiksi("Ya".equalsIgnoreCase(resultSet.getString("isFiksi")));
                listBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBuku;
    }


}
