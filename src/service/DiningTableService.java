package service;

import dao.DiningTableDAO;
import domain.DiningTable;

import java.util.List;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 10:51
 */
public class DiningTableService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //显示所有餐桌
    public List<DiningTable> showAllTables() {
        String sql = "SELECT id, state FROM dining_table";
        return diningTableDAO.queryMulti(sql, DiningTable.class);
    }

    public DiningTable getDiningTable(int id) {
        String sql = "SELECT * FROM dining_table WHERE id = ?";
        return diningTableDAO.querySingle(sql, DiningTable.class, id);
    }

    //预定
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        String sql = "UPDATE dining_table SET state = '已预定', orderName = ?, orderTel = ? WHERE id = ?";
        return diningTableDAO.update(sql, orderName, orderTel, id) >= 1;
    }

    public boolean customerLeave(int id) {
        String sql = "UPDATE dining_table SET state = '空', orderName = '', orderTel = '' WHERE id = ?";
        return diningTableDAO.update(sql, id) >= 1;
    }

    //更新餐桌的状态
    public boolean updateState(int id, String state) {
        String sql = "UPDATE dining_table SET state = ? WHERE id = ?";
        int update = diningTableDAO.update(sql, state, id);
        return update >= 1;
    }

    //客户直接堂食，用当前时间毫秒数当做姓名
    public boolean eatInHall(int id) {
        String sql = "UPDATE dining_table SET orderName = ? WHERE id = ?";
        System.out.println("s");
        int update = diningTableDAO.update(sql, String.valueOf(System.currentTimeMillis()), id);
        return update >= 1;
    }

    //得到桌子对应的姓名和电话字符串之和
    public String getNameAddTel(int tableId) {
        String sql = "SELECT * FROM dining_table WHERE id = ?";
        DiningTable diningTable = diningTableDAO.querySingle(sql, DiningTable.class, tableId);
        return diningTable.getOrderName() + diningTable.getOrderTel();
    }
}
