package model;

public class librarian extends user {

    public librarian(Integer id, String nama, String alamat, String noTelp) {
        super(id, nama);
        this.setAlamat(alamat);
        this.setNoTelp(noTelp);
    }

    @Override
    public void showRole() {
        System.out.println("Role: Penjaga dan Administrator Perpustakaan");
    }
    
}
