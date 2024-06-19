package lk.simplicuspvtltd.Domains;

public class ItemDomain {
    private String name;
    private int price;

    public ItemDomain(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
