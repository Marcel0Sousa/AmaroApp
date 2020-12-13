package co.marcelosousa.amaro.model;

import java.util.ArrayList;

public class ProdutosResponse {

    public ArrayList<Produtos>  products;

    public ArrayList<Produtos> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Produtos> products) {
        this.products = products;
    }
}
