package domain;

import java.util.Date;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 16:53
 */
public class MultiTableBean {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Short nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;

    //增加来自Menu的name和price
    private String name;
    private double price;

    public MultiTableBean() {
    }

    public MultiTableBean(Integer id, String billId, Integer menuId, Short nums, Double money, Integer diningTableId, Date billDate, String state, String name, double price) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Short getNums() {
        return nums;
    }

    public void setNums(Short nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "\t\t" + menuId + "\t\t" + name + "\t\t" + price + "\t\t" + nums + "\t\t" + money + "\t\t" +
                diningTableId + "\t\t" + billDate + "\t\t" + state;
    }
}
