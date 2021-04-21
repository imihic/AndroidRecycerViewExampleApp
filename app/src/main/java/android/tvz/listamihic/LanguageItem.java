package android.tvz.listamihic;

import android.os.Parcel;
import android.os.Parcelable;

public class LanguageItem implements Parcelable {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public LanguageItem(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    protected LanguageItem(Parcel in) {
        mImageResource = in.readInt();
        mText1 = in.readString();
        mText2 = in.readString();
    }

    public static final Creator<LanguageItem> CREATOR = new Creator<LanguageItem>() {
        @Override
        public LanguageItem createFromParcel(Parcel in) {
            return new LanguageItem(in);
        }

        @Override
        public LanguageItem[] newArray(int size) {
            return new LanguageItem[size];
        }
    };

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mText1);
        dest.writeString(mText2);
    }
}