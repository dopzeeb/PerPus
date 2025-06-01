package model;



public class visitor extends user {
    private String MemberID;

    public visitor() {
        super();
        MemberID = "";    
    }

    public visitor(Integer id, String nama, String alamat, String noTelp, String MemberID) {
        super(id, nama, alamat, noTelp);
        this.MemberID = MemberID; 
    }

    public String getMemberID() {
        return MemberID;
    }
    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }
    
    @Override
    public void showRole() {
        System.out.println("Role: Pengunjung Perpustakaan");
    }
}
