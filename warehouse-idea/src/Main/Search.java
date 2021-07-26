package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 负责查询并显示结果的类
 */
public class Search {
    //商品信息查询
    public void goodsSearch( TableView Table, TableColumn name, TableColumn no, TableColumn mf,TableColumn num) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

        //获取搜索框中的数据

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 商品名,商品编号,供货商,库存量 from 仓库 ");

        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtname = dbre.getString("商品名");
            String txtno = dbre.getString("商品编号");
            String txtmf = dbre.getString("供货商");
            int txtnum = dbre.getInt("库存量");

            //创建值对象
            Data data = new Data();
            data.GoodsSearchData(txtname,txtno,txtmf,txtnum);

            name.setCellValueFactory(new PropertyValueFactory("name"));//映射
            no.setCellValueFactory(new PropertyValueFactory("no"));
            mf.setCellValueFactory(new PropertyValueFactory("mf"));
            num.setCellValueFactory(new PropertyValueFactory("num"));

            //list添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }

    public void goodsSearch(TextField searchGoods, TableView Table, TableColumn name, TableColumn no, TableColumn mf,TableColumn num) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

        //获取搜索框中的数据
        String goods = searchGoods.getText();

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 商品名,商品编号,供货商,库存量 from 仓库 where  商品编号='"+goods+"' or 商品名='"+goods+"'");


        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtname = dbre.getString("商品名");
            String txtno = dbre.getString("商品编号");
            String txtmf = dbre.getString("供货商");
            int txtnum = dbre.getInt("库存量");

            //创建值对象
            Data data = new Data();
            data.GoodsSearchData(txtname,txtno,txtmf,txtnum);

            name.setCellValueFactory(new PropertyValueFactory("name"));//映射
            no.setCellValueFactory(new PropertyValueFactory("no"));
            mf.setCellValueFactory(new PropertyValueFactory("mf"));
            num.setCellValueFactory(new PropertyValueFactory("num"));

            //list添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }


    public void inGoodsSearch( TableView Table, TableColumn noIn, TableColumn nameIn, TableColumn adminIn,TableColumn dateIn,TableColumn numIn) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

//        //获取搜索框中的数据
//        String goods = searchGoods.getText();

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 仓库.商品编号,商品名,账号,入库时间,入库数量 from 入库单,仓库 where 入库单.`商品编号`=仓库.`商品编号` ");

        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtnoIn = dbre.getString("商品编号");
            String txtnameIn = dbre.getString("商品名");
            String txtadminIn = dbre.getString("账号");
            String txtdeatIn = dbre.getString("入库时间");
            int txtnumIn = dbre.getInt("入库数量");

            //创建值对象
            Data data = new Data();
            data.InGoodsSearchData(txtnoIn,txtnameIn,txtadminIn,txtdeatIn,txtnumIn);

            noIn.setCellValueFactory(new PropertyValueFactory("noIn")); //映射
            nameIn.setCellValueFactory(new PropertyValueFactory("nameIn"));
            adminIn.setCellValueFactory(new PropertyValueFactory("adminIn"));
            dateIn.setCellValueFactory(new PropertyValueFactory("dateIn"));
            numIn.setCellValueFactory(new PropertyValueFactory("numIn"));

            //添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }

    public void inGoodsSearch(TextField searchGoods, TableView Table, TableColumn noIn, TableColumn nameIn, TableColumn adminIn,TableColumn dateIn,TableColumn numIn) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

        String goods = searchGoods.getText();

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 仓库.商品编号,商品名,账号,入库时间,入库数量 from 入库单,仓库 where 入库单.`商品编号`=仓库.`商品编号` and (入库单.商品编号='"+goods+"' or 商品名='"+goods+"')");

        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtnoIn = dbre.getString("商品编号");
            String txtnameIn = dbre.getString("商品名");
            String txtadminIn = dbre.getString("账号");
            String txtdeatIn = dbre.getString("入库时间");
            int txtnumIn = dbre.getInt("入库数量");

            //创建值对象
            Data data = new Data();
            data.InGoodsSearchData(txtnoIn,txtnameIn,txtadminIn,txtdeatIn,txtnumIn);



            noIn.setCellValueFactory(new PropertyValueFactory("noIn")); //映射
            nameIn.setCellValueFactory(new PropertyValueFactory("nameIn"));
            adminIn.setCellValueFactory(new PropertyValueFactory("adminIn"));
            dateIn.setCellValueFactory(new PropertyValueFactory("dateIn"));
            numIn.setCellValueFactory(new PropertyValueFactory("numIn"));

            //添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }

    public void outGoodsSearch( TableView Table, TableColumn noOut, TableColumn nameOut, TableColumn adminOut,TableColumn dateOut,TableColumn numOut) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

//        //获取搜索框中的数据
//        String goods = searchGoods.getText();

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 仓库.商品编号,商品名,账号,出库时间,出库数量 from 出库单,仓库 where 出库单.`商品编号`=仓库.`商品编号` ");

        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtnoOut = dbre.getString("商品编号");
            String txtnameOut = dbre.getString("商品名");
            String txtadminOut = dbre.getString("账号");
            String txtdateOut = dbre.getString("出库时间");
            int txtnumOut = dbre.getInt("出库数量");

            //创建值对象
            Data data = new Data();
            data.OutGoodsSearchData(txtnoOut,txtnameOut,txtadminOut,txtdateOut,txtnumOut);


            noOut.setCellValueFactory(new PropertyValueFactory("noOut")); //映射
            nameOut.setCellValueFactory(new PropertyValueFactory("nameOut"));
            adminOut.setCellValueFactory(new PropertyValueFactory("adminOut"));
            dateOut.setCellValueFactory(new PropertyValueFactory("dateOut"));
            numOut.setCellValueFactory(new PropertyValueFactory("numOut"));

            //添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }

    public void outGoodsSearch(TextField searchGoods, TableView Table, TableColumn noOut, TableColumn nameOut, TableColumn adminOut,TableColumn dateOut,TableColumn numOut) throws SQLException {

        ObservableList<Data> list = FXCollections.observableArrayList();

        //获取搜索框中的数据
        String goods = searchGoods.getText();

        //创建DBcon对象，连接数据库输入sql查询语句，将数据以结果集形式返回
        DBcon dBcon = new DBcon();
        ResultSet dbre = dBcon.Db("select 仓库.商品编号,商品名,账号,出库时间,出库数量 from 出库单,仓库 where 出库单.`商品编号`=仓库.`商品编号` and (出库单.商品编号='"+goods+"' or 商品名='"+goods+"')");

        //通过循环获取返回的结果集的数据
        while (true){
            //若下一组结果集没有数据则关闭结果集并跳出循环
            if (!dbre.next()){
                dbre.close();
                break;
            }
            //从结果集中获取数据
            String txtnoOut = dbre.getString("商品编号");
            System.out.println(txtnoOut);
            String txtnameOut = dbre.getString("商品名");
            String txtadminOut = dbre.getString("账号");
            String txtdateOut = dbre.getString("出库时间");
            int txtnumOut = dbre.getInt("出库数量");

            //创建值对象
            Data data = new Data();
            data.OutGoodsSearchData(txtnoOut,txtnameOut,txtadminOut,txtdateOut,txtnumOut);


            noOut.setCellValueFactory(new PropertyValueFactory("noOut")); //映射
            nameOut.setCellValueFactory(new PropertyValueFactory("nameOut"));
            adminOut.setCellValueFactory(new PropertyValueFactory("adminOut"));
            dateOut.setCellValueFactory(new PropertyValueFactory("dateOut"));
            numOut.setCellValueFactory(new PropertyValueFactory("numOut"));

            //添加值对象
            list.add(data);

            //tableview添加list
            Table.setItems(list);
        }
    }

}
