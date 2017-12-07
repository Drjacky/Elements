package ir.hosseinabbasi.elements.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.opencsv.bean.CsvBindByName;

/**
 * Created by Dr.jacky on 2017/11/18.
 */

public class Data implements Parcelable {

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String description;

    @CsvBindByName
    private String image;

    public Data(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    @Override
    public String toString(){
        return
                "Data{" +
                        "title = '" + title + '\'' +
                        ",description = '" + description + '\'' +
                        ",image = '" + image + '\'' +
                        "}";
    }


    protected Data(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}