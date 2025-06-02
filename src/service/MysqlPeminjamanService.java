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
        String sql = "INSERT INTO peminjaman (id_buku, id_user, tanggal_pinjam) VALUES (?, ?, NOW())";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setInt(1, idBuku);
            ps.setInt(2, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kembalikanBuku(Integer idBuku, Integer idUser) {
        // Implementasi logika pengembalian buku
        // Misalnya, update tanggal pengembalian di tabel peminjaman
        String sql = "UPDATE peminjaman SET tanggal_kembali = NOW() WHERE id_buku = ? AND id_user = ?";
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            ps.setInt(1, idBuku);
            ps.setInt(2, idUser);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}