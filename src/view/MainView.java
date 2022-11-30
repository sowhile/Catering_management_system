package view;

import utils.InputUtility;

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
                    //这里可以查一次数据库判断用户名是否存在
                    System.out.print("请输入密  码:");
                    String passwd = InputUtility.readString(128);
                    //数据库验证用户名和密码
                    if (!passwd.equals("123"))
                        //登录失败
                        System.out.println("登录失败");
                    else {
                        System.out.println("\t\t *登录成功|欢迎使用|GL&HF*");
                        //二级菜单
                        while (loop2) {
                            System.out.println("==================满汉楼==================");
                            System.out.println("\t\t\t\t1.餐桌状态");
                            System.out.println("\t\t\t\t2.预定");
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
                                    System.out.println("餐桌状态");
                                    break;
                                case '2':
                                    break;
                                case '3':
                                    break;
                                case '4':
                                    break;
                                case '5':
                                    break;
                                case '6':
                                    break;
                                case '7':
                                    loop2 = false;
                                    break;
                                case '8':
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
}
