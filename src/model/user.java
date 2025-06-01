package model;

public abstract class user {
    // Atribut
    private Integer id;
    private String nama;
    private String alamat;
    private String noTelp;

    public user(Integer id, String nama){
        this.id = id;
        this.nama = nama;
        this.alamat = "";
        this.noTelp = "";
    }

    public abstract void showRole();
    public Integer getId() { return id; }
    public String getNama() { return nama; }
    public void setId(Integer id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp;}
}
