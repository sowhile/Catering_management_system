package service;

import dao.MenuDAO;
import domain.Menu;

import java.util.List;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 13:29
 */
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> getMenu() {
        String sql = "SELECT * FROM menu";
        return menuDAO.queryMulti(sql, Menu.class);
    }

    //根据ID返回Menu对象
    public Menu getMenu(int id) {
        String sql = "SELECT * FROM menu WHERE id = ?";
        return menuDAO.querySingle(sql, Menu.class, id);
    }
}
