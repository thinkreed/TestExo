package thinkreed.testexo;

/**
 * Created by thinkreed on 2017/10/1.
 */

public class Pair {
    private byte[] mData;
    private int mSize;

    Pair() {
    }

    Pair(byte[] data, int size) {
        mData = data;
        mSize = size;
    }

    public void setData(byte[] data) {
        mData = data;
    }

    public byte[] getData() {
        return mData;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public int getSize() {
        return mSize;
    }

}
