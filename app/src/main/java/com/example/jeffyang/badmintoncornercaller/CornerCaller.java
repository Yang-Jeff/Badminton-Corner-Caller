package com.example.jeffyang.badmintoncornercaller;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by JeffYang on 2017-08-05.
 */

public class CornerCaller implements Parcelable {
    private int numberOfCalls;
    private double callInterval;
    private List<Integer> corners = new ArrayList<>();


    public CornerCaller(){
    }



    // this below code is for setting up the object to be passed through intent to the new activity
    public CornerCaller(Parcel in) {
        numberOfCalls = in.readInt();
        callInterval = in.readDouble();
        in.readList(corners, CornerCaller.class.getClassLoader());   // TODO will null break it?

    }
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(numberOfCalls);
        out.writeDouble(callInterval);
        out.writeList(corners);
    }
    public static final Parcelable.Creator<CornerCaller> CREATOR = new Parcelable.Creator<CornerCaller>() {
        public CornerCaller createFromParcel(Parcel in) {
            return new CornerCaller(in);
        }

        public CornerCaller[] newArray(int size) {

            return new CornerCaller[size];
        }
    };





    // this returns the corner as you would expect and not zero index
    public int callCorner(){
        Random rand = new Random();

        System.out.println("Temp value is " + corners.size());

        int randNum = rand.nextInt(corners.size());
        return corners.get(randNum);

    }




    public void decrementNumberOfCalls(){
        numberOfCalls--;
    }







    // getters and setters
    public void setNumberOfCalls(int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public int getNumberOfCalls() {
        return numberOfCalls;
    }


    public double getCallInterval() {
        return callInterval;
    }

    public void setCallInterval(double callInterval) {
        this.callInterval = callInterval;
    }





    // setup for the corners ArrayList
    public void selectCorner1() {
        corners.add(1);
    }
    public void selectCorner2() {
        corners.add(2);
    }
    public void selectCorner3() {
        corners.add(3);
    }
    public void selectCorner4() {
        corners.add(4);
    }
    public void selectCorner5() {
        corners.add(5);
    }
    public void selectCorner6() {
        corners.add(6);
    }

}
