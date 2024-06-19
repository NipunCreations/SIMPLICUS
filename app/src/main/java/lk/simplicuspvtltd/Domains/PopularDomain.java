package lk.simplicuspvtltd.Domains;

public class PopularDomain {
    private String title;
    private String item;
    private String description;
    private int number;
    private String pic;
    private int price;

    public PopularDomain(String title, String item, String description, int number, String pic, int price) {
        this.title = title;
        this.item = item;
        this.description = description;
        this.number = number;
        this.pic = pic;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
