package perpus;

import java.util.ArrayList;
import java.sql.SQLException;

import model.Buku;
import model.user;
import model.librarian;
import model.visitor;

import service.MysqlBukuService;
import service.MysqlUserService;

public class PerPus {

    public static void main(String[] args) {
        // Inisialisasi service
        MysqlBukuService bukuService = new MysqlBukuService();
        MysqlUserService userService = new MysqlUserService();

        try {
            // 1. Tampilkan semua Buku
            ArrayList<Buku> daftarBuku = bukuService.getAllBuku();
            displayDaftarBuku(daftarBuku);

            // 2. Tambah Buku baru
            Buku bukuBaru = new Buku("Clean Code", null, "Robert C. Martin", true);
            bukuService.addBuku(bukuBaru);
            System.out.println("\nSetelah tambah buku baru:");
            displayDaftarBuku(bukuService.getAllBuku());

            // 3. Update Buku (misal id 1)
            Buku bukuUpdate = new Buku("Clean Code 2nd Edition", 1, "Robert C. Martin", true);
            bukuService.updateBuku(bukuUpdate);
            System.out.println("\nSetelah update buku id 1:");
            displayDaftarBuku(bukuService.getAllBuku());

            // 4. Delete Buku (misal id 2)
            Buku bukuDelete = new Buku();
            bukuDelete.setId_buku(2);
            bukuService.deleteBuku(bukuDelete);
            System.out.println("\nSetelah delete buku id 2:");
            displayDaftarBuku(bukuService.getAllBuku());

            // 5. Tampilkan semua User
            ArrayList<user> daftarUser = userService.getAllUsers();
            displayDaftarUser(daftarUser);

            // 6. Tambah User baru (visitor dan librarian)
            visitor visitorBaru = new visitor(null, "Andi", "Jl. Mawar", "08123456789", "MEM001");
            librarian librarianBaru = new librarian(null, "Budi", "Jl. Melati", "08987654321", "LIB001");
            userService.addUser(visitorBaru);
            userService.addUser(librarianBaru);
            System.out.println("\nSetelah tambah user baru:");
            displayDaftarUser(userService.getAllUsers());

            // 7. Update User (misal id 1)
            visitor visitorUpdate = new visitor(1, "Andi Updated", "Jl. Baru", "08111111111", "MEM001");
            userService.updateUser(visitorUpdate);
            System.out.println("\nSetelah update user id 1:");
            displayDaftarUser(userService.getAllUsers());

            // 8. Delete User (misal id 2)
            user userDelete = userService.getUserById(2);
            if (userDelete != null) {
                userService.deleteUser(userDelete);
                System.out.println("\nSetelah delete user id 2:");
            } else {
                System.out.println("\nUser dengan ID 2 tidak ditemukan.");
            }
            displayDaftarUser(userService.getAllUsers());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayDaftarBuku(ArrayList<Buku> daftarBuku) {
        System.out.println("Daftar Buku:");
        for (Buku buku : daftarBuku) {
            buku.printBuku();
        }
    }

    public static void displayDaftarUser(ArrayList<user> daftarUser) {
        System.out.println("Daftar User:");
        for (user u : daftarUser) {
            System.out.println("ID: " + u.getId() + ", Nama: " + u.getNama() +
                               ", Alamat: " + u.getAlamat() + ", No Telp: " + u.getNoTelp());
            u.showRole();
        }
    }
}
