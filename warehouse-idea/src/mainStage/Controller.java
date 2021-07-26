package mainStage;

import Main.Data;
import Main.Search;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller  implements Initializable{
    @FXML
    private TableView<Data> Table;
    @FXML
    private TableView<Data> TableIn;
    @FXML
    private TableView<Data> TableOut;
    @FXML
    private TableColumn<Data, String> name;
    @FXML
    private TableColumn<Data, String> no;
    @FXML
    private TableColumn<Data, String> mf;
    @FXML
    private TableColumn<Data, Integer> num;
    @FXML
    private TextField searchGoods;
    @FXML
    private TextField searchInGoods;
    @FXML
    private TextField searchOutGoods;
    @FXML
    private TableColumn<Data, String> nameIn;
    @FXML
    private TableColumn<Data, String> noIn;
    @FXML
    private TableColumn<Data, String> nameOut;
    @FXML
    private TableColumn<Data, String> noOut;
    @FXML
    private TableColumn<Data, String> adminIn;
    @FXML
    private TableColumn<Data, String> adminOut;
    @FXML
    private TableColumn<Data, String> dateIn;
    @FXML
    private TableColumn<Data, String> dateOut;
    @FXML
    private TableColumn<Data, String> numIn;
    @FXML
    private TableColumn<Data, String> numOut;
    @FXML
    private Button blogin;

    public void login(){
        Parent opmain = null;
        try {
            opmain = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage mainpg =new Stage();
        assert opmain != null;
        Scene scene = new Scene(opmain,233,177);
        mainpg.setScene(scene);
        mainpg.show();
        Stage stage = (Stage)blogin.getScene().getWindow();
        stage.close();
    }


    //商品信息查询
    public void goodsSearch() throws SQLException {
        Search search = new Search();
        search.goodsSearch(searchGoods,Table,name,no,mf,num);
    }

    public void goodsInSearch() throws SQLException {
        Search search = new Search();
        search.inGoodsSearch(searchInGoods,TableIn, noIn,  nameIn,  adminIn, dateIn,numIn);
    }
    public void goodsOutSearch() throws SQLException {
        Search search = new Search();
        search.outGoodsSearch(searchOutGoods,TableOut, noOut, nameOut, adminOut, dateOut, numOut);
    }


    //初始化
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Search search = new Search();
        try {
            search.goodsSearch(Table,name,no,mf,num);
            search.inGoodsSearch(TableIn, noIn,  nameIn,  adminIn, dateIn,numIn);
            search.outGoodsSearch(TableOut, noOut, nameOut, adminOut, dateOut, numOut);

            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}