package model;

public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private int nomorMeja;

    public Pelanggan(String idPelanggan, String nama, int nomorMeja) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.nomorMeja = nomorMeja;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNomorMeja() {
        return nomorMeja;
    }

    public void setNomorMeja(int nomorMeja) {
        this.nomorMeja = nomorMeja;
    }
}
