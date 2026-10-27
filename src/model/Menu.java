package model;

/**
 * Class Menu merepresentasikan data makanan/minuman pada sistem restoran/warkop.
 * Mengimplementasikan prinsip enkapsulasi dan tanggung jawab pengelolaan stok mandiri.
 */
public class Menu {
    private String kodeMenu;
    private String namaMenu;
    private double harga;
    private int stok;

    public Menu(String kodeMenu, String namaMenu, double harga, int stok) {
        this.kodeMenu = kodeMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.stok = Math.max(stok, 0); // Memastikan stok awal tidak bernilai negatif
    }

    // Behavior: Validasi state stok agar tidak berkurang di bawah nol
    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("[PERINGATAN] Jumlah pengurangan stok harus lebih dari 0.");
            return false;
        }

        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            return true;
        } else {
            System.out.println("[GAGAL] Stok untuk \"" + namaMenu + "\" tidak mencukupi! (Sisa: " + this.stok + ", Diminta: " + jumlah + ")");
            return false;
        }
    }

    // Behavior: Penambahan stok dengan validasi jumlah positif
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
            System.out.println("[INFO] Stok \"" + namaMenu + "\" bertambah " + jumlah + ". Total stok sekarang: " + this.stok);
        } else {
            System.out.println("[PERINGATAN] Jumlah penambahan stok harus lebih dari 0.");
        }
    }

    // Getter dan Setter yang terenkapsulasi
    public String getKodeMenu() {
        return kodeMenu;
    }

    public void setKodeMenu(String kodeMenu) {
        this.kodeMenu = kodeMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        }
    }

    public int getStok() {
        return stok;
    }
}
