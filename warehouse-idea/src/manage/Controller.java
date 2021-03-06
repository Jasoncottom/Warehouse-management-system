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

        ResultSet dbrs = db.Db("SELECT ????????? from ?????? WHERE ???????????? = '" + noIn + "'");
        while (true) {
            try {
                if (!dbrs.next()) {
                    int i =db.Dbin("insert INTO ?????? (????????????,?????????,?????????,?????????)VALUES('" + noIn + "','" + nameIn + "','" + mfIn + "','" + numIn + "')");
                    int j =db.Dbin("insert INTO ????????? (????????????,??????,????????????,????????????)VALUES('" + noIn + "','" + id + "','" + datein + "','" + numIn + "')");
                    inMessage.setText("???????????? 1 ?????????");
                } else {
                    int sum = dbrs.getInt("?????????");
                    int i = db.Dbin("UPDATE ?????? set ?????????='" + (numIn + sum) + "' WHERE ????????????='" + noIn + "'");
                    int j = db.Dbin("insert INTO ????????? (????????????,??????,????????????,????????????)VALUES('" + noIn + "','" + id + "','" + datein + "','" + numIn + "')");
                    inMessage.setText("????????????" + numIn + "???????????????" + noIn + "?????????");
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


        ResultSet dbrs = db.Db("SELECT ????????? from ?????? WHERE ???????????? = '" + out + "'");
        while (true) {
            try {
                if (!dbrs.next()) {
                    outMessage.setText("???????????????");
                } else {
                    int sum = dbrs.getInt("?????????");
                    sum = sum - numout;
                    if (sum >= 0) {
                        int i = db.Dbin("UPDATE ?????? set ?????????='" + sum + "' WHERE ???????????? ='" + out + "'");
                        int j = db.Dbin("insert INTO ????????? (????????????,??????,????????????,????????????)VALUES('" + out + "','" + id + "','" + dateout + "','" + numout + "')");
                        outMessage.setText("????????????" + numout + "???????????????" + out + "?????????");
                    } else {
                        outMessage.setText("????????????");
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
            int i = db.Dbin("insert INTO ????????? (??????,??????,??????)Values('" + user + "','" + password + "','" + name + "')");
            lb.setText("????????????"+i+"????????????");
        }
        else{
            lb.setText("????????????????????????");
        }
    }

    //?????????
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