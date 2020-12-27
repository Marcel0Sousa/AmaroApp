package co.marcelosousa.amaro.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Produtos implements Parcelable {

    private String name;
    private String image;
    private String regular_price;
    private String actual_price;
    private String installments;
    private boolean on_sale;
    private ArrayList<Tamanhos> sizes;

    public Produtos(String name, String image, String actual_price) {
        this.name = name;
        this.image = image;
        this.actual_price = actual_price;
    }

    protected Produtos(Parcel in) {
        name = in.readString();
        image = in.readString();
        regular_price = in.readString();
        actual_price = in.readString();
        installments = in.readString();
        on_sale = in.readByte() != 0;
        sizes = in.createTypedArrayList(Tamanhos.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(regular_price);
        parcel.writeString(actual_price);
        parcel.writeString(installments);
        parcel.writeByte((byte) (on_sale ? 1 : 0));
        parcel.writeTypedList(sizes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produtos> CREATOR = new Creator<Produtos>() {
        @Override
        public Produtos createFromParcel(Parcel in) {
            return new Produtos(in);
        }

        @Override
        public Produtos[] newArray(int size) {
            return new Produtos[size];
        }
    };

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

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public boolean isOn_sale() {
        return on_sale;
    }

    public void setOn_sale(boolean on_sale) {
        this.on_sale = on_sale;
    }

    public ArrayList<Tamanhos> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Tamanhos> sizes) {
        this.sizes = sizes;
    }
}
