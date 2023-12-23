/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import function.NftFunction;
import model.Nft;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class NftViewController implements Initializable {

    @FXML
    private TableColumn<Nft, String> floorpricecol;
    //  @FXML
    //private WebView webView;
    @FXML
    private TableColumn<Nft, String> avgpricecol;
    @FXML
    private TableColumn<Nft, String> namecol;

    @FXML
    private TableColumn<Nft, String> pricecol;

    @FXML
    private TableColumn<Nft, String> rankcol;

    @FXML
    private TableView<Nft> nftTable;

    @FXML
    private TableColumn<Nft, String> urlcol;

    @FXML
    private TableColumn<Nft, String> volumecol;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TableColumn<Nft, Double> salecol;

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("giaodienchinh2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.show();
    }

    @FXML
    void switchToNftDis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("giaodienNft.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("NFT Analyse");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void switchToTweetDis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("giaodienTweet.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tweets Analyse");
        stage.show();

    }

    public void addToTable(ArrayList<Nft> nftList) {
        rankcol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        floorpricecol.setCellValueFactory(new PropertyValueFactory<>("floor_price"));
        volumecol.setCellValueFactory(new PropertyValueFactory<>("volume"));
        avgpricecol.setCellValueFactory(new PropertyValueFactory<>("avg_price"));
        urlcol.setCellValueFactory(new PropertyValueFactory<>("url"));
        salecol.setCellValueFactory(new PropertyValueFactory<>("num"));

        // Custom cell factory for displaying Double values in salecol
        salecol.setCellFactory(tc -> new TableCell<Nft, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item)); // Format to display two decimal places
                }
            }
        });
        urlcol.setCellFactory(tc -> new TableCell<Nft, String>() {
            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                if (empty || url == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Hyperlink hyperlink = new Hyperlink(url);
                    hyperlink.setOnAction(event -> {
                        Desktop desktop = Desktop.getDesktop();
                        //   webView.getEngine().load(url);
                        try {

                            desktop.browse(java.net.URI.create(url));
                        } catch (IOException ex) {
                            Logger.getLogger(NftViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    setGraphic(hyperlink);
                }
            }
        });

        nftTable.getItems().addAll(nftList);
    }

    @FXML
    void discountBtn(ActionEvent event) {
        nftTable.getItems().clear();
        ArrayList<Nft> discountnft = NftFunction.getInstance().sortDiscount();
        addToTable(discountnft);
        rankcol.setVisible(false);
        salecol.setVisible(true);
    }

    @FXML
    void whatShould(ActionEvent event) {
         rankcol.setVisible(true);
        salecol.setVisible(false);
        nftTable.getItems().clear();
        ArrayList<Nft> topList = NftFunction.getInstance().read();
        Collections.sort(topList, Comparator.comparing(Nft::getOwners_percent).reversed()); // Sắp owners_percent down
        ArrayList<Nft> topTen = new ArrayList<>(topList.subList(0, Math.min(10, topList.size()))); // Lấy 10 phần tử đầu tiên hoặc ít hơn nếu danh sách không đủ 10 phần tử

        nftTable.getItems().clear(); // Xóa dữ liệu hiện tại trong bảng
        addToTable(topTen); // Thêm 10 NFT có owners_percent cao nhất vào bảng
    }

    @FXML
    void searchBtn(ActionEvent event) {
         rankcol.setVisible(true);
        salecol.setVisible(false);
        nftTable.getItems().clear();
        ArrayList<Nft> list = NftFunction.getInstance().Search(textFieldSearch.getText());
        addToTable(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Nft> nftList = NftFunction.getInstance().read();
        addToTable(nftList);
    }

}
