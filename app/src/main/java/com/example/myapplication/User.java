package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class User implements Parcelable {

    private final String name;
    private final String surname;
    private final String city;
    private final String birth;
    private final int department;
    private final List<String> phones;

    public User(String name,
                String surname,
                String city,
                String birth,
                int department,
                List<String> phones) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.birth = birth;
        this.department = department;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    protected User(Parcel in) {
        name = in.readString();
        surname = in.readString();
        city = in.readString();
        birth = in.readString();
        department = in.readInt();
        phones = in.createStringArrayList();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(city);
        dest.writeString(birth);
        dest.writeInt(department);
        dest.writeStringList(phones); // Correcting writing of list
    }

    public int getDepartment() {return department;}
    public String getBirth() {return birth;}
    public List<String> getPhones() {
        return phones;
    }
}
