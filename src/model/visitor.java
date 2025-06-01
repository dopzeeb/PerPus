package model;

public class visitor extends user {
    
    public visitor(Integer id, String nama, String alamat, String noTelp) {
        super(id, nama);
        this.setAlamat(alamat);
        this.setNoTelp(noTelp);
    }
    @Override
    public void showRole() {
        System.out.println("Role: Pengunjung Perpustakaan");
    }
}
