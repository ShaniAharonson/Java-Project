package JavaBeans;

import java.util.Date;

public class Coupon {
    private Integer id;
    private Integer companyID;
    private int category;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int amount;
    private Double price;
    private String image;

    public Coupon() {

    }

    public Coupon(Integer companyID, int category, String title, String description,
                  java.sql.Date startDate, java.sql.Date endDate, int amount, Double price, String image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(Integer id, Integer companyID, int category, String title,
                  String description, Date startDate, Date endDate,
                  Integer amount, Double price, String image) {
        this.id = id;
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = (java.sql.Date) startDate;
        this.endDate = (java.sql.Date) endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = (java.sql.Date) startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = (java.sql.Date) endDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '\n';
    }
}
