package model;

import java.util.Date;

public class peminjaman {
    private user usern;
    private Buku buku;
    private date tanggalPinjam;

    public peminjaman() {
        this.usern = new usern();
        this.buku = new buku();
        this.tanggalPinjam = new Date();
    }

    public peminjaman(user usern, Buku buku, Date tanggalPinjam) {
        this.usern = usern;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
    }

    public User getUser() {
        return usern;
    }

    public void setUser(User user) {
        this.usern = usern;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
