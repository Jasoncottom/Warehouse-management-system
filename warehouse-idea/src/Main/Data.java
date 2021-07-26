package Main;

import javafx.beans.property.SimpleStringProperty;

public class Data {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty no = new SimpleStringProperty();
    private SimpleStringProperty mf = new SimpleStringProperty();
    private SimpleStringProperty num = new SimpleStringProperty();
    private SimpleStringProperty nameIn = new SimpleStringProperty();
    private SimpleStringProperty nameOut = new SimpleStringProperty();
    private SimpleStringProperty noIn = new SimpleStringProperty();
    private SimpleStringProperty noOut = new SimpleStringProperty();
    private SimpleStringProperty adminIn = new SimpleStringProperty();
    private SimpleStringProperty adminOut = new SimpleStringProperty();
    private SimpleStringProperty dateIn = new SimpleStringProperty();
    private SimpleStringProperty numIn = new SimpleStringProperty();
    private SimpleStringProperty dateOut = new SimpleStringProperty();
    private SimpleStringProperty numOut = new SimpleStringProperty();


    public void  GoodsSearchData(String name ,String no ,String mf ,int num ){
        this.name.set(name);
        this.no.set(no);
        this.mf.set(mf);
        this.num.set(String.valueOf(num));
    }

    public void InGoodsSearchData(String noIn ,String nameIn ,String adminIn ,String dateIn ,int numIn){
        this.nameIn.set(nameIn);
        this.noIn.set(noIn);
        this.adminIn.set(adminIn);
        this.dateIn.set(dateIn);
        this.numIn.set(String.valueOf(numIn));
    }

    public void OutGoodsSearchData(String noOut ,String nameOut ,String adminOut ,String dateOut ,int numOut){
        this.nameOut.set(nameOut);
        this.noOut.set(noOut);
        this.adminOut.set(adminOut);
        this.dateOut.set(dateOut);
        this.numOut.set(String.valueOf(numOut));
    }

    public String getName(){
        return name.get();
    }
    public String getNameIn(){
        return nameIn.get();
    }
    public String getNameOut(){
        return nameOut.get();
    }
    public String getNo(){
        return no.get();
    }
    public String getNoIn(){
        return noIn.get();
    }
    public String getNoOut(){
        return noOut.get();
    }
    public String getMf(){
        return mf.get();
    }
    public String getNum(){
        return num.get();
    }
    public String getAdminIn(){
        return adminIn.get();
    }
    public String getAdminOut(){
        return adminOut.get();
    }
    public String getDateIn(){
        return dateIn.get();
    }
    public String getDateOut(){
        return dateOut.get();
    }
    public String getNumIn(){
        return numIn.get();
    }
    public String getNumOut(){
        return numOut.get();
    }
}

