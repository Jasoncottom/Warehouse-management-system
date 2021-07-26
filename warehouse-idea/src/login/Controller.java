package login;

import Main.DBcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Controller{

    @FXML
    private TextField username;//用户名
    @FXML
    private TextField password;//密码
    @FXML
    private Button blogin;//确认按钮
    @FXML
    private Label lb;//提示
    public static String ID;//登陆界面登陆的用户名


    //登陆方法
    @FXML
    public void handlelogin() {
        String userName = this.username.getText();
        String passWord = this.password.getText();
        ID=userName;//供入库出库操作录入管理员
        DBcon dbcon =new DBcon();
        ResultSet dbrs = dbcon.Db("SELECT 账号,密码 FROM `管理员`");
        String Dbusername;
        String Dbpassword;
        while(true){
            try {
                if (!dbrs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Dbusername =dbrs.getString("账号");
                Dbpassword =dbrs.getString("密码");
                if(Objects.equals(userName, Dbusername)&&Objects.equals(passWord, Dbpassword)){
                    Parent opmain = null;
                    try {
                        opmain = FXMLLoader.load(getClass().getResource("../manage/mangPG.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage mainpg =new Stage();
                    assert opmain != null;
                    Scene scene = new Scene(opmain,530,580);
                    mainpg.setScene(scene);
                    mainpg.show();
                    Stage stage = (Stage)blogin.getScene().getWindow();
                    stage.close();
                }
                else {
                   this.lb.setText("用户名或密码错误！");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
