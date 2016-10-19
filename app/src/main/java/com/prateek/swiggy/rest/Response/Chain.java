package com.prateek.swiggy.rest.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Chain implements Parcelable {

    public static final Parcelable.Creator<Chain> CREATOR = new Parcelable.Creator<Chain>() {
        @Override
        public Chain createFromParcel(Parcel source) {
            return new Chain(source);
        }

        @Override
        public Chain[] newArray(int size) {
            return new Chain[size];
        }
    };
    private int id;
    private String name;
    private String uuid;
    private String city;
    private String area;
    private String avgRating;
    private String totalRatings;
    private String cloudinaryImageId;
    private List<String> cuisine = new ArrayList<String>();
    private int recommended;
    private int costForTwo;
    private String costForTwoString;
    private int deliveryCharge;
    private int minimumOrder;
    private int opened;
    private String nextOpen;
    private int deliveryTime;
    private int minDeliveryTime;
    private int maxDeliveryTime;
    private Slugs slugs;
    private String nextOpenMessage;
    private boolean tmpClosed;
    private String type;
    private String cityState;
    private String address;
    private int postalCode;
    private String latLong;
    private int cityDeliveryCharge;
    private int threshold;
    private String bannerMessage;
    private String locality;
    private int parentId;
    private int deliveryFeeType;
    private boolean unserviceable;
    private boolean _new;
    private boolean veg;
    private boolean select;
    private boolean favorite;

    public Chain() {
    }

    protected Chain(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.uuid = in.readString();
        this.city = in.readString();
        this.area = in.readString();
        this.avgRating = in.readString();
        this.totalRatings = in.readString();
        this.cloudinaryImageId = in.readString();
        this.cuisine = in.createStringArrayList();
        this.recommended = in.readInt();
        this.costForTwo = in.readInt();
        this.costForTwoString = in.readString();
        this.deliveryCharge = in.readInt();
        this.minimumOrder = in.readInt();
        this.opened = in.readInt();
        this.nextOpen = in.readString();
        this.deliveryTime = in.readInt();
        this.minDeliveryTime = in.readInt();
        this.maxDeliveryTime = in.readInt();
        this.slugs = in.readParcelable(Slugs.class.getClassLoader());
        this.nextOpenMessage = in.readString();
        this.tmpClosed = in.readByte() != 0;
        this.type = in.readString();
        this.cityState = in.readString();
        this.address = in.readString();
        this.postalCode = in.readInt();
        this.latLong = in.readString();
        this.cityDeliveryCharge = in.readInt();
        this.threshold = in.readInt();
        this.bannerMessage = in.readString();
        this.locality = in.readString();
        this.parentId = in.readInt();
        this.deliveryFeeType = in.readInt();
        this.unserviceable = in.readByte() != 0;
        this._new = in.readByte() != 0;
        this.veg = in.readByte() != 0;
        this.select = in.readByte() != 0;
        this.favorite = in.readByte() != 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public String getTotalRatings() {
        return totalRatings;
    }

    public String getCloudinaryImageId() {
        return cloudinaryImageId;
    }

    public List<String> getCuisine() {
        return cuisine;
    }

    public int getRecommended() {
        return recommended;
    }

    public int getCostForTwo() {
        return costForTwo;
    }

    public String getCostForTwoString() {
        return costForTwoString;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public int getMinimumOrder() {
        return minimumOrder;
    }

    public int getOpened() {
        return opened;
    }

    public String getNextOpen() {
        return nextOpen;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public int getMinDeliveryTime() {
        return minDeliveryTime;
    }

    public int getMaxDeliveryTime() {
        return maxDeliveryTime;
    }

    public Slugs getSlugs() {
        return slugs;
    }

    public String getNextOpenMessage() {
        return nextOpenMessage;
    }

    public boolean isTmpClosed() {
        return tmpClosed;
    }

    public String getType() {
        return type;
    }

    public String getCityState() {
        return cityState;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getLatLong() {
        return latLong;
    }

    public int getCityDeliveryCharge() {
        return cityDeliveryCharge;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getBannerMessage() {
        return bannerMessage;
    }

    public String getLocality() {
        return locality;
    }

    public int getParentId() {
        return parentId;
    }

    public int getDeliveryFeeType() {
        return deliveryFeeType;
    }

    public boolean isUnserviceable() {
        return unserviceable;
    }

    public boolean is_new() {
        return _new;
    }

    public boolean isVeg() {
        return veg;
    }

    public boolean isSelect() {
        return select;
    }

    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return "Chain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", avgRating='" + avgRating + '\'' +
                ", totalRatings='" + totalRatings + '\'' +
                ", cloudinaryImageId='" + cloudinaryImageId + '\'' +
                ", cuisine=" + cuisine +
                ", recommended=" + recommended +
                ", costForTwo=" + costForTwo +
                ", costForTwoString='" + costForTwoString + '\'' +
                ", deliveryCharge=" + deliveryCharge +
                ", minimumOrder=" + minimumOrder +
                ", opened=" + opened +
                ", nextOpen='" + nextOpen + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", minDeliveryTime=" + minDeliveryTime +
                ", maxDeliveryTime=" + maxDeliveryTime +
                ", slugs=" + slugs +
                ", nextOpenMessage='" + nextOpenMessage + '\'' +
                ", tmpClosed=" + tmpClosed +
                ", type='" + type + '\'' +
                ", cityState='" + cityState + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", latLong='" + latLong + '\'' +
                ", cityDeliveryCharge=" + cityDeliveryCharge +
                ", threshold=" + threshold +
                ", bannerMessage='" + bannerMessage + '\'' +
                ", locality='" + locality + '\'' +
                ", parentId=" + parentId +
                ", deliveryFeeType=" + deliveryFeeType +
                ", unserviceable=" + unserviceable +
                ", _new=" + _new +
                ", veg=" + veg +
                ", select=" + select +
                ", favorite=" + favorite +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.uuid);
        dest.writeString(this.city);
        dest.writeString(this.area);
        dest.writeString(this.avgRating);
        dest.writeString(this.totalRatings);
        dest.writeString(this.cloudinaryImageId);
        dest.writeStringList(this.cuisine);
        dest.writeInt(this.recommended);
        dest.writeInt(this.costForTwo);
        dest.writeString(this.costForTwoString);
        dest.writeInt(this.deliveryCharge);
        dest.writeInt(this.minimumOrder);
        dest.writeInt(this.opened);
        dest.writeString(this.nextOpen);
        dest.writeInt(this.deliveryTime);
        dest.writeInt(this.minDeliveryTime);
        dest.writeInt(this.maxDeliveryTime);
        dest.writeParcelable(this.slugs, flags);
        dest.writeString(this.nextOpenMessage);
        dest.writeByte(this.tmpClosed ? (byte) 1 : (byte) 0);
        dest.writeString(this.type);
        dest.writeString(this.cityState);
        dest.writeString(this.address);
        dest.writeInt(this.postalCode);
        dest.writeString(this.latLong);
        dest.writeInt(this.cityDeliveryCharge);
        dest.writeInt(this.threshold);
        dest.writeString(this.bannerMessage);
        dest.writeString(this.locality);
        dest.writeInt(this.parentId);
        dest.writeInt(this.deliveryFeeType);
        dest.writeByte(this.unserviceable ? (byte) 1 : (byte) 0);
        dest.writeByte(this._new ? (byte) 1 : (byte) 0);
        dest.writeByte(this.veg ? (byte) 1 : (byte) 0);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    }
}
