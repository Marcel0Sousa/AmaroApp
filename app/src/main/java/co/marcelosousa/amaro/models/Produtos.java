package co.marcelosousa.amaro.models;

public class Produtos {

    private String name;
    private String image;
    private String actual_price;

    public Produtos(String name, String image, String actual_price) {
        this.name = name;
        this.image = image;
        this.actual_price = actual_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }
}
