package service;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Buku;

public class MysqlBukuService {

    private Connection koneksi;

    public MysqlBukuService() {
        // Inisialisasi koneksi dari util.MysqlUtilities
        this.koneksi = util.MysqlUtilities.getConnection();
    }

    public Buku createBuku() {
        return new Buku();
    }

    public void addBuku(Buku buku) {
        String sql = "INSERT INTO buku (judul, penulis, isFiksi) VALUES (?, ?, ?)";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, buku.getJudul());
            ps.setString(2, buku.getPenulis());
            ps.setBoolean(3, buku.isFiksi()); // Gunakan getter boolean
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBuku(Buku buku) {
        String sql = "UPDATE buku SET judul = ?, penulis = ?, isFiksi = ? WHERE id_buku = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, buku.getJudul());
            ps.setString(2, buku.getPenulis());
            ps.setBoolean(3, buku.isFiksi()); // Simpan sebagai boolean
            ps.setInt(4, buku.getId_buku());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBuku(Buku buku) {
        String sql = "DELETE FROM buku WHERE id_buku = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setInt(1, buku.getId_buku());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Buku> getAllBuku() {
        ArrayList<Buku> listBuku = new ArrayList<>();
        String sql = "SELECT * FROM buku";
        try (Statement stmt = koneksi.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Buku buku = new Buku();
                buku.setId_buku(rs.getInt("id_buku"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setIsFiksi(rs.getBoolean("isFiksi")); // Ambil langsung sebagai boolean
                listBuku.add(buku);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBuku;
    }

    public ArrayList<Buku> getBukuByJudul(String judul) {
        ArrayList<Buku> listBuku = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE judul = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setString(1, judul);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Buku buku = new Buku();
                    buku.setId_buku(rs.getInt("id_buku"));
                    buku.setJudul(rs.getString("judul"));
                    buku.setPenulis(rs.getString("penulis"));
                    buku.setIsFiksi(rs.getBoolean("isFiksi")); // Ambil langsung boolean
                    listBuku.add(buku);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBuku;
    }

    public DefaultTableModel getBukuTableModel() {
        String[] columnNames = {
                "Id Buku", "Judul Buku", "Penulis", "Ketersediaan", "Peminjam"};
        ArrayList<Object[]> rowData = new ArrayList<>();
        String sql = "SELECT b.id_buku, b.judul, b.penulis, " +
                 "CASE WHEN p.id_buku IS NULL THEN 'Tersedia' ELSE 'Dipinjam' END AS ketersediaan, " +
                 "m.nama AS peminjam " +
                 "FROM buku b " +
                 "LEFT JOIN peminjaman p ON b.id_buku = p.id_buku AND p.tanggalkembali IS NULL " +
                 "LEFT JOIN user m ON p.id_user = m.id_user";
        try (Statement stmt = koneksi.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("id_buku");
                row[1] = rs.getString("judul");
                row[2] = rs.getString("penulis");
                row[3] = rs.getString("ketersediaan");
                row[4] = rs.getString("peminjam");
                rowData.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[][] data = rowData.toArray(new Object[0][]);
        
        return new DefaultTableModel(data, columnNames);
    }
}
