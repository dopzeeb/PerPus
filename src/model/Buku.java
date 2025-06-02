package model;

public class Buku {
    private Integer id_buku;
    private String judul;
    private String penulis;
    private Boolean isFiksi;
    
    public Buku() {
        this.id_buku = 0;
        this.judul = "";
        this.penulis = "";
        this.isFiksi = false;
    }
    
    public Buku(String judul, Integer id_buku, String penulis, Boolean isFiksi) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.penulis = penulis;
        this.isFiksi = isFiksi;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public Integer getId_buku() {
        return id_buku;
    }
    
    public void setId_buku(Integer id_buku) {
        this.id_buku = id_buku;
    }
    
    public String getPenulis() {
        return penulis;
    }
    
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    
    // Revisi: ubah getter ini jadi isFiksi() sesuai konvensi boolean getter
    public Boolean isFiksi() {
        return isFiksi;
    }
    
    public void setIsFiksi(Boolean isFiksi) {
        this.isFiksi = isFiksi;
    }
    
    public void printBuku() {
        System.out.println("ID: " + getId_buku() + ", Judul: " + getJudul() + ", Penulis: " + getPenulis() + ", Fiksi: " + (isFiksi() ? "Ya" : "Tidak"));
    }
}
