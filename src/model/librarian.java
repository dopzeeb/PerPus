package model;

public class librarian extends user {
    // Atribut
    private String IDPetugas;

    public librarian() {
        IDPetugas = "";
    }

    public librarian(Integer id, String nama, String alamat, String noTelp, String IDPetugas) {
        super(id, nama, alamat, noTelp);
        this.setIDPetugas(IDPetugas);
        this.setAlamat(alamat);
        this.setNoTelp(noTelp);
    }
    public String getIDPetugas() {
        return IDPetugas;
    }
    public void setIDPetugas(String IDPetugas) {
        this.IDPetugas = IDPetugas;
    }
    @Override
    public void showRole() {
        System.out.println("Role: Penjaga dan Administrator Perpustakaan");
    }
    
}
