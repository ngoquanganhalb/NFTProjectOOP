/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.DisplaymainController;
import function.NftFunction;
import function.TweetFunction;
import model.Nft;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tweet;
import test.NftStatistics;

/**
 *
 * @author Admin
 */
public class NftViewController extends DisplaymainController implements Initializable {

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
    private BarChart<String, Integer> chart;
  //  @FXML
   // private BarChart<String, Number> chart;
    @Override
    void switchToHome(ActionEvent event) throws IOException {
        super.switchToHome(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    void switchToTweetDis(ActionEvent event) throws IOException {
        super.switchToTweetDis(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    void switchToNftDis(ActionEvent event) throws IOException {
        super.switchToNftDis(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    void logouttest(ActionEvent event) {
        super.logouttest(event); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
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
        chart.setVisible(false);
        nftTable.setVisible(true);
        ArrayList<Nft> discountnft = NftFunction.getInstance().sortDiscount();
        addToTable(discountnft);
        rankcol.setVisible(false);
        salecol.setVisible(true);
    }

    @FXML
    void whatShould(ActionEvent event) {
        chart.setVisible(false);
        nftTable.setVisible(true);
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
         chart.setVisible(false);
         nftTable.setVisible(true);
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
    
        @FXML
 void thongke(ActionEvent event) {
    List<Tweet> tweets = TweetFunction.getInstance().read();
    List<Nft> nfts = NftFunction.getInstance().read();

    NftFunction nftFunction = NftFunction.getInstance();
    LinkedHashMap<String, Integer> nftNameCount = nftFunction.countNftNamesInTweetsAndHashtags(tweets, nfts);

    // Sort the map entries by value (count) in descending order
    List<Map.Entry<String, Integer>> sortedEntries = nftNameCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(10) // Limit to the top 10 entries
            .collect(Collectors.toList());

    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    for (Map.Entry<String, Integer> entry : sortedEntries) {
        series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
    }

    chart.getData().clear();
    chart.getData().add(series);
    chart.setVisible(true);
    nftTable.setVisible(false);
}
}
