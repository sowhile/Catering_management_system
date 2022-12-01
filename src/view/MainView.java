package view;

import domain.Bill;
import domain.DiningTable;
import domain.Menu;
import service.BillService;
import service.DiningTableService;
import service.EmpService;
import service.MenuService;
import utils.BCrypt;
import utils.InputUtility;

import java.util.List;

/**
 * @author sowhile
 * @version 1.0
 * <p>
 * 2022/11/30 23:48
 */
public class MainView {
    public static void main(String[] args) {
        new MainView().mainMenu();
    }

    private boolean mainLoop = true;
    private boolean loop2 = true;
    private EmpService empService = new EmpService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public void mainMenu() {
        while (mainLoop) {
            System.out.println("=================满汉楼登录=================");
            System.out.println("\t\t\t\t1.登录");
            System.out.println("\t\t\t\t2.退出");
            System.out.print("请选择:");
            char key = InputUtility.readMenuSelection(2);
            switch (key) {
                case '1':
                    System.out.print("请输入用户名:");
                    String user = InputUtility.readString(64);
                    //这里查数据库判断用户名是否存在
                    String hashed;
                    if ((hashed = empService.getPassword(user)) == null) {
                        System.out.println("用户名不存在!");
                        break;
                    }
                    waitATime();
                    System.out.print("请输入密  码:");
                    String passwd = InputUtility.readString(128);
                    //验证用户名和密码
                    if (!BCrypt.checkpw(passwd, hashed))
                        //登录失败
                        System.out.println("密码错误!");
                    else {
                        System.out.println("\t\t *登录成功|欢迎," + user + "|GL&HF*");
                        loop2 = true;
                        //二级菜单
                        while (loop2) {
                            System.out.println("==================满汉楼==================");
                            System.out.println("\t\t\t\t1.餐桌状态");
                            System.out.println("\t\t\t\t2.预定座位");
                            System.out.println("\t\t\t\t3.显示所有菜品");
                            System.out.println("\t\t\t\t4.点餐服务");
                            System.out.println("\t\t\t\t5.查看账单");
                            System.out.println("\t\t\t\t6.结账");
                            System.out.println("\t\t\t\t7.退出用户");
                            System.out.println("\t\t\t\t8.退出系统");
                            System.out.print("请选择:");
                            char key2 = InputUtility.readMenuSelection(8);
                            switch (key2) {
                                case '1':
                                    List<DiningTable> diningTables = diningTableService.showAllTables();
                                    waitATime();
                                    System.out.println("================餐桌状态================");
                                    System.out.println("编号\t\t状态");
                                    for (DiningTable showAllTable : diningTables)
                                        System.out.println(showAllTable);
                                    InputUtility.readKeyBoard(100, true);
                                    break;
                                case '2':
                                    orderDiningTable();
                                    break;
                                case '3':
                                    List<Menu> menu = menuService.getMenu();
                                    waitATime();
                                    System.out.println("===================菜单===================");
                                    System.out.println("id\t\t菜名\t\t\t类型\t\t\t价格");
                                    for (Menu menu1 : menu)
                                        System.out.println(menu1);
                                    InputUtility.readKeyBoard(100, true);
                                    break;
                                case '4':
                                    orderMenu();
                                    break;
                                case '5':
                                    showBills();
                                    break;
                                case '6':
                                    checkOut();
                                    break;
                                case '7':
                                    loop2 = false;
                                    break;
                                case '8':
                                    System.out.println("退出系统..");
                                    loop2 = false;
                                    mainLoop = false;
                                    break;
                            }
                        }
                    }
                    break;
                case '2':
                    System.out.println("退出系统..");
                    mainLoop = false;
                    break;
            }
        }
    }

    public static void waitATime() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void orderDiningTable() {
        System.out.println("================预定餐桌================");
        System.out.print("请选择要预定的餐桌编号(-1取消):");
        int orderId = InputUtility.readInt();
        if (orderId == -1) {
            System.out.println("*取消预定*");
            return;
        }

        DiningTable diningTable = diningTableService.getDiningTable(orderId);
        if (diningTable == null) {
            System.out.println("*该餐桌不存在*");
            return;
        }

        if (!"空".equals(diningTable.getState())) {
            System.out.println("*该餐桌不能预定*");
            return;
        }

        waitATime();
        System.out.print("请输入姓  名:");
        String name = InputUtility.readString(64);
        System.out.print("请输入手机号:");
        String tel = InputUtility.readString(11);
        if (diningTableService.orderDiningTable(orderId, name, tel))
            System.out.println("*预定成功*");
        else
            System.out.println("*预定失败*");
    }

    //点餐
    private void orderMenu() {
        System.out.println("===============点餐服务===============");
        System.out.print("请选择要点餐的餐桌编号(-1取消):");
        int tableId = InputUtility.readInt();
        if (tableId == -1) {
            System.out.println("*取消预定*");
            return;
        }
        System.out.print("请选择要点餐的菜品编号(-1取消):");
        int menuId = InputUtility.readInt();
        if (menuId == -1) {
            System.out.println("*取消预定*");
            return;
        }
        System.out.print("请输入菜品数量(-1取消):");
        short num = InputUtility.readShort();
        if (num == -1) {
            System.out.println("*取消预定*");
            return;
        }

        DiningTable diningTable = diningTableService.getDiningTable(tableId);
        if (diningTable == null) {
            System.out.println("*该餐桌不存在*");
            return;
        }
        //堂食
        else if (diningTable.getOrderName().length() == 0)
            diningTableService.eatInHall(tableId);

        Menu menu = menuService.getMenu(menuId);
        if (menu == null) {
            System.out.println("*该菜品不存在*");
            return;
        }

        if (billService.orderMenu(menuId, num, tableId))
            System.out.println("*点餐成功*");
        else System.out.println("*点餐失败*");
    }

    private void showBills() {
        List<Bill> billList = billService.getBillList();
        waitATime();
        System.out.println("===================================所有账单===================================");
        System.out.println("编号\t\t菜品号\t数量\t\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        for (Bill bill : billList)
            System.out.println(bill);
        InputUtility.readKeyBoard(100, true);
    }

    //结账
    private void checkOut() {
        System.out.println("===============结账服务===============");
        System.out.print("请选择要结账的餐桌编号(-1取消):");
        int tableId = InputUtility.readInt();
        if (tableId == -1) {
            System.out.println("*取消结账*");
            return;
        }
        System.out.print("请选择结账方式(q取消):");
        String mode = InputUtility.readString(5);

        if (mode.toLowerCase().charAt(0) == 'q') {
            System.out.println("*取消结账*");
            return;
        }
        DiningTable diningTable = diningTableService.getDiningTable(tableId);
        if (diningTable == null) {
            System.out.println("*该餐桌不存在*");
            return;
        }
        double billMoney;
        if ((billMoney = billService.needOrNot(tableId)) == 0.0) {
            System.out.println("*该餐桌不需要结账*");
            return;
        }
        if (billService.payBill(tableId, mode)) System.out.println("*结账成功,共消费[" + billMoney + "]元*");
        else System.out.println("*结账失败*");
    }
}
