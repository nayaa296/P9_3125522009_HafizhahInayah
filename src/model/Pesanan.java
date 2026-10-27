package model;

import java.util.ArrayList;

/**
 * Class Pesanan mengelola transaksi pemesanan restoran/warkop.
 * Memiliki relasi ke Pelanggan dan kumpulan DetailPesanan.
 */
public class Pesanan {
    private String kodePesanan;
    private Pelanggan pelanggan;
    private ArrayList<DetailPesanan> listDetail;
    private double totalBiaya;
    private boolean statusBayar;

    public Pesanan(String kodePesanan, Pelanggan pelanggan) {
        this.kodePesanan = kodePesanan;
        this.pelanggan = pelanggan;
        this.listDetail = new ArrayList<>();
        this.totalBiaya = 0.0;
        this.statusBayar = false;
    }

    // Behavior: Validasi stok, pengurangan stok menu secara otomatis, dan penambahan item ke pesanan
    public boolean tambahItem(Menu menu, int jumlah) {
        if (menu == null) {
            System.out.println("[GAGAL] Menu tidak valid!");
            return false;
        }

        if (jumlah <= 0) {
            System.out.println("[GAGAL] Jumlah pemesanan harus lebih dari 0!");
            return false;
        }

        // Delegasi tanggung jawab pengecekan dan pengurangan stok ke object Menu
        boolean stokCukup = menu.kurangiStok(jumlah);
        if (stokCukup) {
            DetailPesanan detail = new DetailPesanan(menu, jumlah);
            listDetail.add(detail);
            hitungTotalBiaya();
            System.out.println("[SUKSES] Menambahkan " + jumlah + "x " + menu.getNamaMenu() + " ke pesanan " + kodePesanan);
            return true;
        } else {
            System.out.println("[GAGAL] Pesanan \"" + menu.getNamaMenu() + "\" (" + jumlah + " porsi) dibatalkan karena stok kurang.");
            return false;
        }
    }

    // Behavior: Menghitung total biaya akumulasi dari seluruh detail pesanan
    public void hitungTotalBiaya() {
        double total = 0.0;
        for (DetailPesanan detail : listDetail) {
            total += detail.getSubtotal();
        }
        this.totalBiaya = total;
    }

    // Behavior: Memproses pembayaran pesanan dan mengubah status bayar
    public void prosesPembayaran() {
        hitungTotalBiaya();
        this.statusBayar = true;
        System.out.println("[PEMBAYARAN] Pembayaran pesanan " + kodePesanan + " sebesar Rp " 
                + String.format("%,.0f", totalBiaya) + " BERHASIL diproses. Status: LUNAS.");
    }

    // Behavior: Mencetak nota pembayaran lengkap
    public void cetakNota() {
        System.out.println("==========================================================");
        System.out.println("                   WARKOP & RESTO NUSANTARA               ");
        System.out.println("            Jl. Trunojoyo No. 45, Sumenep, Madura         ");
        System.out.println("==========================================================");
        System.out.println(" Kode Pesanan : " + kodePesanan);
        System.out.println(" Pelanggan    : " + pelanggan.getNama() + " (" + pelanggan.getIdPelanggan() + ")");
        System.out.println(" Nomor Meja   : Meja " + pelanggan.getNomorMeja());
        System.out.println(" Status Bayar : " + (statusBayar ? "LUNAS" : "BELUM LUNAS"));
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-4s %-22s %-6s %-12s %-10s\n", "No", "Menu", "Qty", "Harga", "Subtotal");
        System.out.println("----------------------------------------------------------");

        int no = 1;
        for (DetailPesanan d : listDetail) {
            System.out.printf("%-4d %-22s %-6d Rp %-9.0f Rp %-9.0f\n",
                    no++,
                    d.getMenu().getNamaMenu(),
                    d.getJumlah(),
                    d.getMenu().getHarga(),
                    d.getSubtotal());
        }

        System.out.println("----------------------------------------------------------");
        System.out.printf(" TOTAL TAGIHAN                         : Rp %,.0f\n", totalBiaya);
        System.out.println("==========================================================");
        System.out.println("          Terima kasih atas kunjungan Anda!               ");
        System.out.println("==========================================================\n");
    }

    public String getKodePesanan() {
        return kodePesanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public ArrayList<DetailPesanan> getListDetail() {
        return listDetail;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public boolean isStatusBayar() {
        return statusBayar;
    }
}
