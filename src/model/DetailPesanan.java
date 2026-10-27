package model;

public class DetailPesanan {
    private Menu menu;
    private int jumlah;
    private double subtotal;

    public DetailPesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
        this.hitungSubtotal();
    }

    public void hitungSubtotal() {
        if (this.menu != null && this.jumlah > 0) {
            this.subtotal = this.menu.getHarga() * this.jumlah;
        } else {
            this.subtotal = 0.0;
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
