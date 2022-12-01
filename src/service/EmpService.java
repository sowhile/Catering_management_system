package service;

import dao.EmpDAO;

/**
 * @author sowhile
 * <p>
 * 完成主要业务
 * 调用EmpDAO完成对Emp表的操作
 * 2022/12/1 10:08
 */
public class EmpService {
    private EmpDAO empDAO = new EmpDAO();

    public String getPassword(String user) {
        String sql = "SELECT password FROM emp WHERE user = ?";
        return (String) empDAO.queryScalar(sql, user);
    }
}
