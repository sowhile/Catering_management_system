package service;

import dao.MultiTableDAO;
import domain.MultiTableBean;

import java.util.List;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 16:58
 */
public class MultiTableService {
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    public List<MultiTableBean> getBillListUnion() {
        String sql = "SELECT * FROM bill, menu WHERE bill.menuId = menu.id";
        return multiTableDAO.queryMulti(sql, MultiTableBean.class);
    }
}
