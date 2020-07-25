package com.github.tivon.shoppingreceipt.model.wrapper;

public interface IStringBuffer {
    IStringBuffer append(String text);
    IStringBuffer padRight(String text);
    IStringBuffer padRight(int customPaddingSize, String text);
    IStringBuffer padLeft(String text);
    StringBuffer newLine();
    StringBuffer endLine();
    StringBuffer getValue();
}
