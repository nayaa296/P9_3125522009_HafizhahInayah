package main;

import model.Menu;
import model.Pelanggan;
import model.Pesanan;

import java.util.ArrayList;

/**
 * Class Main untuk pengujian interaksi antar-object pada Sistem Pemesanan Restoran / Warkop.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   PROGRAM SISTEM PEMESANAN RESTORAN / WARKOP NUSANTARA   ");
        System.out.println("==========================================================\n");

        // 1. Inisialisasi Data Menu (Master Data)
        ArrayList<Menu> daftarMenu = new ArrayList<>();
        Menu m1 = new Menu("M01", "Kopi Tubruk Robusta", 8000, 15);
        Menu m2 = new Menu("M02", "Kopi Susu Gula Aren", 12000, 10);
        Menu m3 = new Menu("M03", "Nasi Goreng Spesial", 22000, 8);
        Menu m4 = new Menu("M04", "Mie Goreng Jawa", 18000, 5);
        Menu m5 = new Menu("M05", "Es Teh Manis", 5000, 20);

        daftarMenu.add(m1);
        daftarMenu.add(m2);
        daftarMenu.add(m3);
        daftarMenu.add(m4);
        daftarMenu.add(m5);

        // Tampilkan Stok Awal Menu
        System.out.println("--- DAFTAR MENU & KETERSEDIAAN STOK AWAL ---");
        tampilkanDaftarMenu(daftarMenu);

        // 2. Inisialisasi Data Pelanggan
        Pelanggan pelanggan1 = new Pelanggan("CUST-089", "Ahmad Fauzi", 4);
        System.out.println("\nPelanggan Terdaftar:");
        System.out.println("ID        : " + pelanggan1.getIdPelanggan());
        System.out.println("Nama      : " + pelanggan1.getNama());
        System.out.println("No. Meja  : " + pelanggan1.getNomorMeja());

        // 3. Membuat Objek Transaksi Pesanan Baru
        Pesanan pesanan1 = new Pesanan("TRX-20261027-01", pelanggan1);
        System.out.println("\n--- PROSES PENCATATAN PESANAN (" + pesanan1.getKodePesanan() + ") ---");

        // Menambahkan menu yang valid dan stok mencukupi
        pesanan1.tambahItem(m2, 2); // 2x Kopi Susu Gula Aren
        pesanan1.tambahItem(m3, 1); // 1x Nasi Goreng Spesial
        pesanan1.tambahItem(m5, 2); // 2x Es Teh Manis

        // Menguji validasi state stok: memesan melebihi stok yang tersedia (Mie Goreng hanya ada 5)
        System.out.println("\n[PENGUJIAN VALIDASI STOK: Memesan melebihi sisa stok]");
        pesanan1.tambahItem(m4, 10); // Meminta 10, stok hanya ada 5 -> harus ditolak

        // 4. Proses Pembayaran
        System.out.println("\n--- PROSES PEMBAYARAN KASIR ---");
        pesanan1.prosesPembayaran();

        // 5. Cetak Nota Pembayaran
        System.out.println();
        pesanan1.cetakNota();

        // 6. Tampilkan Pembaruan Stok Menu Setelah Pemesanan
        System.out.println("--- DAFTAR MENU & KETERSEDIAAN STOK AKHIR (SETELAH TRANSAKSI) ---");
        tampilkanDaftarMenu(daftarMenu);
    }

    private static void tampilkanDaftarMenu(ArrayList<Menu> list) {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-6s %-25s %-15s %-8s\n", "Kode", "Nama Menu", "Harga Satuan", "Stok");
        System.out.println("------------------------------------------------------------------");
        for (Menu m : list) {
            System.out.printf("%-6s %-25s Rp %-12.0f %-8d\n",
                    m.getKodeMenu(),
                    m.getNamaMenu(),
                    m.getHarga(),
                    m.getStok());
        }
        System.out.println("------------------------------------------------------------------");
    }
}
