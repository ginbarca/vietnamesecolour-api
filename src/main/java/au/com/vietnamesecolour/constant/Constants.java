package au.com.vietnamesecolour.constant;

public class Constants {

    public static final int EVENT_TYPE_LISTING_MARKET = 1;
    public static final int EVENT_TYPE_UNLISTING_MARKET = 2;
    public static final int EVENT_TYPE_NFT_TRANSFER = 3;
    public static final int EVENT_TYPE_PURCHASE_MARKET = 4;


    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
