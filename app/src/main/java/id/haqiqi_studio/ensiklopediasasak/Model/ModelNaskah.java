package id.haqiqi_studio.ensiklopediasasak.Model;

public class ModelNaskah {
    String judul, penulis, path;
    Integer jmlHalaman;

    public ModelNaskah(String judul, String penulis, Integer jmlHalaman, String path) {
        this.judul = judul;
        this.penulis = penulis;
        this.jmlHalaman = jmlHalaman;
        this.path = path;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJudul() {

        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public Integer getJmlHalaman() {
        return jmlHalaman;
    }

    public void setJmlHalaman(Integer jmlHalaman) {
        this.jmlHalaman = jmlHalaman;
    }
}
