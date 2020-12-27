package co.marcelosousa.amaro.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Tamanhos implements Parcelable {

    private String size;
    private boolean available;

    public Tamanhos(String size, boolean available) {
        this.size = size;
        this.available = available;
    }

    protected Tamanhos(Parcel in) {
        size = in.readString();
        available = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(size);
        dest.writeByte((byte) (available ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tamanhos> CREATOR = new Creator<Tamanhos>() {
        @Override
        public Tamanhos createFromParcel(Parcel in) {
            return new Tamanhos(in);
        }

        @Override
        public Tamanhos[] newArray(int size) {
            return new Tamanhos[size];
        }
    };

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
