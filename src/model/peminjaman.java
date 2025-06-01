package model;


public class peminjaman {
    private Integer id_peminjaman;
    private String tanggalPinjam;
    private String tanggalKembali;
    private Buku buku;
    private user user;

    public peminjaman(Buku buku, user user) {
        this.id_peminjaman = 0; // Default value, can be set later
        this.tanggalPinjam = ""; // Default value, can be set later
        this.tanggalKembali = ""; // Default value, can be set later
        this.buku = buku;
        this.user = user;
    }

    public Integer getId_peminjaman() {
        return id_peminjaman;
    }   
    public void setId_peminjaman(Integer id_peminjaman) {
        this.id_peminjaman = id_peminjaman;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }
    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
    public String getTanggalKembali() {
        return tanggalKembali;
    }
    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    public Buku getBuku() {
        return buku;
    }
    public void setBuku(Buku buku) {
        this.buku = buku;
    }
    public user getUser() {
        return user;
    }
    public void setUser(user user) {
        this.user = user;
    }
    
}
