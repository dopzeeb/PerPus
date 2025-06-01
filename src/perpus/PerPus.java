/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpus;

import java.util.ArrayList;

import model.Buku;
import model.user;
import service.MysqlBukuService;

/**
 *
 * @author ACER
 */
public class PerPus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ArrayList<Buku> daftarBuku = new ArrayList<>();
        // ArrayList<user> daftarUser = new ArrayList<>();

        MysqlBukuService bukuService = new MysqlBukuService();

        // Contoh penggunaan service untuk menambahkan buku
        daftarBuku = bukuService.getAllBuku();
        displayDaftarBuku(daftarBuku);

        
    }
    
    public static void displayDaftarBuku(ArrayList<Buku> daftarBuku) {
        System.out.println("Daftar Buku:");
        for (Buku buku : daftarBuku) {
            buku.printBuku();
        }
    }
}
