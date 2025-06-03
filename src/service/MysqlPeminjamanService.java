package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlPeminjamanService {

    // Implementasi service untuk peminjaman buku
    // Misalnya, method untuk meminjam buku, mengembalikan buku, dan melihat daftar peminjaman

    // Contoh method:
    private Connection koneksi;

    public MysqlPeminjamanService() {
        // Inisialisasi koneksi dari util.MysqlUtilities
        this.koneksi = util.MysqlUtilities.getConnection();
    }
    public void pinjamBuku(Integer idBuku, Integer idUser) {
        // Implementasi logika peminjaman buku
        // Misalnya, insert ke tabel peminjaman
        String sql = "INSERT INTO peminjaman (id_buku, id_user, tanggalPinjam) VALUES (?, ?, NOW())";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setInt(1, idBuku);
            ps.setInt(2, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
            throw new RuntimeException("Gagal meminjam buku karena user tidak ada", e);
            // javax.swing.JOptionPane.showMessageDialog(null, "Gagal meminjam buku karena user tidak ada" );
        }
    }

    public void kembalikanBuku(Integer idBuku) {
        // Implementasi logika pengembalian buku
        // Misalnya, update tanggal pengembalian di tabel peminjaman
        String sql = "UPDATE peminjaman SET tanggalKembali = NOW() WHERE id_buku = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setInt(1, idBuku);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}