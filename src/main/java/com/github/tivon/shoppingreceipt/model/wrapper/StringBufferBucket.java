package com.github.tivon.shoppingreceipt.model.wrapper;

import com.github.tivon.shoppingreceipt.utils.PaddingUtils;

/*
 *
 * A wrapper class for StringBuffer instance to manage transformation logic aside of service layer class
 *
 */
public class StringBufferBucket implements IStringBuffer {

    private StringBuffer bucket;

    public StringBufferBucket(StringBuffer bucket) {
        this.bucket = bucket;
    }

    @Override
    public StringBufferBucket append(String text) {
        this.bucket.append(text);
        return this;
    }

    @Override
    public StringBufferBucket padRight(String text) {
        PaddingUtils.right(this.bucket, text);
        return this;
    }

    @Override
    public StringBufferBucket padRight(int customPaddingSize, String text) {
        PaddingUtils.right(this.bucket, text, customPaddingSize);
        return this;
    }

    @Override
    public StringBufferBucket padLeft(String text) {
        PaddingUtils.left(this.bucket, text);
        return this;
    }

    @Override
    public StringBuffer newLine() {
        return PaddingUtils.newLine(this.bucket);
    }

    @Override
    public StringBuffer endLine() {
        return this.bucket;
    }

    @Override
    public StringBuffer getValue() {
        return this.bucket;
    }
}
