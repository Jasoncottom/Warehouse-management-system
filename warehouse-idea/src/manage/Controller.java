package manage;

import Main.DBcon;
import Main.Data;
import Main.Search;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField noIn;
    @FXML
    private TextField out;
    @FXML
    private TextField nameIn;
    @FXML
    private TextField mfIn;
    @FXML
    private DatePicker dateIn;
    @FXML
    private DatePicker dateOut;
    @FXML
    private TextField numIn;
    @FXML
    private TextField numOut;
    @FXML
    private Label inMessage;
    @FXML
    private Label outMessage;
    @FXML
    private Label lb;

    @FXML
    private TableView<Data> Table;
    @FXML
    private TableView<Data> TableIn;
    @FXML
    private TableView<Data> TableOut;
    @FXML
    private TableColumn<Data, String> sename;
    @FXML
    private TableColumn<Data, String> seno;
    @FXML
    private TableColumn<Data, String> semf;
    @FXML
    private TableColumn<Data, Integer> senum;
    @FXML
    private TextField searchGoods;
    @FXML
    private TextField searchInGoods;
    @FXML
    private TextField searchOutGoods;
    @FXML
    private TableColumn<Data, String> senameIn;
    @FXML
    private TableColumn<Data, String> senoIn;
    @FXML
    private TableColumn<Data, String> senameOut;
    @FXML
    private TableColumn<Data, String> senoOut;
    @FXML
    private TableColumn<Data, String> seadminIn;
    @FXML
    private TableColumn<Data, String> seadminOut;
    @FXML
    private TableColumn<Data, String> sedateIn;
    @FXML
    private TableColumn<Data, String> sedateOut;
    @FXML
    private TableColumn<Data, String> senumIn;
    @FXML
    private TableColumn<Data, String> senumOut;
    @FXML
    private TextField newUser;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField conPassword;
    @FXML
    private TextField newName;



    public void goodsIn() {
        String noIn = this.noIn.getText();
        String nameIn = this.nameIn.getText();
        String mfIn = this.mfIn.getText();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateIn = this.dateIn.getValue();
        String datein = formater.format(dateIn);
        int numIn = Integer.parseInt(this.numIn.getText());
        String id = login.Controller.ID;
        DBcon db = new DBcon();

        ResultSet dbrs = db.Db("SELECT 库存量 from 仓库 WHERE 商品编号 = '" + noIn + "'");
        while (true) {
            try {
                if (!dbrs.next()) {
                    int i =db.Dbin("insert INTO 仓库 (商品编号,商品名,供货商,库存量)VALUES('" + noIn + "','" + nameIn + "','" + mfIn + "','" + numIn + "')");
                    int j =db.Dbin("insert INTO 入库单 (商品编号,账号,入库时间,入库数量)VALUES('" + noIn + "','" + id + "','" + datein + "','" + numIn + "')");
                    inMessage.setText("成功入库 1 件物品");
                } else {
                    int sum = dbrs.getInt("库存量");
                    int i = db.Dbin("UPDATE 仓库 set 库存量='" + (numIn + sum) + "' WHERE 商品编号='" + noIn + "'");
                    int j = db.Dbin("insert INTO 入库单 (商品编号,账号,入库时间,入库数量)VALUES('" + noIn + "','" + id + "','" + datein + "','" + numIn + "')");
                    inMessage.setText("成功增加" + numIn + "件编号为：" + noIn + "的物品");
                }
                break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void goodsOut() {
        String out = this.out.getText();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOut = this.dateOut.getValue();
        int numout = Integer.parseInt(this.numOut.getText());
        String id = login.Controller.ID;
        DBcon db = new DBcon();
        String dateout = formater.format(dateOut);


        ResultSet dbrs = db.Db("SELECT 库存量 from 仓库 WHERE 商品编号 = '" + out + "'");
        while (true) {
            try {
                if (!dbrs.next()) {
                    outMessage.setText("物品不存在");
                } else {
                    int sum = dbrs.getInt("库存量");
                    sum = sum - numout;
                    if (sum >= 0) {
                        int i = db.Dbin("UPDATE 仓库 set 库存量='" + sum + "' WHERE 商品编号 ='" + out + "'");
                        int j = db.Dbin("insert INTO 出库单 (商品编号,账号,出库时间,出库数量)VALUES('" + out + "','" + id + "','" + dateout + "','" + numout + "')");
                        outMessage.setText("成功出库" + numout + "件编号为：" + out + "的物品");
                    } else {
                        outMessage.setText("库存不足");
                    }
                }
                break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }

    public void goodsSearch() throws SQLException {
        Search search = new Search();
        search.goodsSearch(searchGoods, Table, sename, seno, semf, senum);
    }

    public void goodsInSearch() throws SQLException {

        Search search = new Search();
        search.inGoodsSearch(searchInGoods, TableIn, senoIn, senameIn, seadminIn, sedateIn, senumIn);
    }

    public void goodsOutSearch() throws SQLException {
        Search search = new Search();
        search.outGoodsSearch(searchOutGoods, TableOut, senoOut, senameOut, seadminOut, sedateOut, senumOut);
    }

    public void res() {
        String user =this.newUser.getText();
        String password = this.newPassword.getText();
        String conpassword=this.conPassword.getText();
        String name=this.newName.getText();
        DBcon db = new DBcon();
        if (password.equals(conpassword)) {
            int i = db.Dbin("insert INTO 管理员 (账号,密码,姓名)Values('" + user + "','" + password + "','" + name + "')");
            lb.setText("成功新增"+i+"位管理员");
        }
        else{
            lb.setText("两次输入密码不同");
        }
    }

    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Search search = new Search();
        try {
            search.goodsSearch(Table, sename, seno, semf, senum);
            search.inGoodsSearch(TableIn, senoIn, senameIn, seadminIn, sedateIn, senumIn);
            search.outGoodsSearch(TableOut, senoOut, senameOut, seadminOut, sedateOut, senumOut);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}