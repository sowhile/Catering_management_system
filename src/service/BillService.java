package service;

import dao.BillDAO;
import domain.Bill;
import domain.DiningTable;

import java.util.List;
import java.util.UUID;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 13:51
 */
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    public boolean orderMenu(int menuId, short nums, int diningTableId) {
        //生成唯一的随机ID
//        String billId = UUID.randomUUID().toString();
        //通过预定桌子的姓名和电话来生成唯一的UUID
        DiningTable diningTable = diningTableService.getDiningTable(diningTableId);
        String str = diningTable.getOrderName() + diningTable.getOrderTel();
        String billId = UUID.nameUUIDFromBytes(str.getBytes()).toString();

        String sql = "INSERT INTO bill VALUES(null, ?, ?, ?, ?, ?, NOW(), '未结账')";
        int update = billDAO.update(sql, billId, menuId, nums,
                menuService.getMenu(menuId).getPrice() * nums, diningTableId);
        if (!(update >= 1)) return false;

        return diningTableService.updateState(diningTableId, "就餐中");
    }

    //返回账单
    public List<Bill> getBillList() {
        String sql = "SELECT * FROM bill";
        return billDAO.queryMulti(sql, Bill.class);
    }

    //是否需要结账
    public double needOrNot(int tableId) {
        String sql = "SELECT * FROM bill WHERE billId = ? AND state = '未结账'";
        List<Bill> bills = billDAO.queryMulti(sql, Bill.class,
                UUID.nameUUIDFromBytes(diningTableService.getNameAddTel(tableId).getBytes()).toString());
        double billMoney = 0.0;
        for (Bill bill : bills)
            billMoney += bill.getMoney();
        return billMoney;
    }

    public boolean payBill(int tableId, String payMode) {
        String sql = "UPDATE bill SET state = ? WHERE billId = ? AND state = '未结账'";
        int update = billDAO.update(sql, payMode,
                UUID.nameUUIDFromBytes(diningTableService.getNameAddTel(tableId).getBytes()).toString());
        if (update >= 1) {
            return diningTableService.customerLeave(tableId);
        } else return false;
    }
}
