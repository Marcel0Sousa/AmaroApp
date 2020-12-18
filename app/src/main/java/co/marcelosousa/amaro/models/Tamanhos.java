package co.marcelosousa.amaro.models;

public class Tamanhos {

    private String size;
    private boolean available;

    public Tamanhos(String size, boolean available) {
        this.size = size;
        this.available = available;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
