package id.haqiqi_studio.ensiklopediasasak.Model;

public class ModelVideo {
    String judul, type, durasi, path;

    public ModelVideo(String judul, String type, String durasi, String path) {
        this.judul = judul;
        this.type = type;
        this.durasi = durasi;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }
}
