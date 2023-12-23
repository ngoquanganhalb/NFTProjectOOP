package controller;

import controller.DisplaymainController;
import function.TweetFunction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import model.Tweet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TweetViewController extends DisplaymainController implements Initializable {

    @FXML
    private TableColumn<Tweet, String> ContentTweetColumn;
    @FXML
    private TableColumn<Tweet, String> DateTweetColumn;
    @FXML
    private TableColumn<Tweet, String> HashtagTweetColumn;

    @FXML
    private TableColumn<Tweet, Long> LikeTweetColumn;

    @FXML
    private TableColumn<Tweet, String> authorTweetColumn;

    @FXML
    private DatePicker date1choice;

    @FXML
    private DatePicker date2choice;

    @FXML
    private TextField find_tweet_textfield;

    @FXML
    private TextField hottest_hashtag_Day_textfield;
    @FXML
    private TextField hottest_hashtag_Month_textfield;



    @FXML
    private TableView<Tweet> tweetTable;

    @FXML
    void logouttest(ActionEvent event) {

    }

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


      @FXML
    void HottestHashtagByDay(ActionEvent event) {
        tweetTable.getItems().clear();
         String text = hottest_hashtag_Day_textfield.getText();
        TweetFunction tweetFunction = TweetFunction.getInstance();
        ArrayList<Tweet> tweetList = tweetFunction.tweetsWithHottestHashtagByDay(text);
         addToTable(tweetList);
    }

      @FXML
    void findHottestHastagMonth(ActionEvent event) {
          // Xóa dữ liệu hiện tại của TableView
         tweetTable.getItems().clear();
         String text = hottest_hashtag_Month_textfield.getText();

        TweetFunction tweetFunction = TweetFunction.getInstance();
        
        ArrayList<Tweet> tweetList = tweetFunction.hottestHashtagByMonth(text);
       
         addToTable(tweetList);
         
    }
      @FXML
    void findTweetByHashtag(ActionEvent event) {
        // Xóa dữ liệu hiện tại của TableView
        tweetTable.getItems().clear();
        
         String text = find_tweet_textfield.getText();
         TweetFunction tweetFunction = TweetFunction.getInstance();
        
        ArrayList<Tweet> tweetList = tweetFunction.searchByHashtag(text);
         addToTable(tweetList);

    }

    @FXML
    void viewAllTweets(ActionEvent event) {
        // Xóa dữ liệu hiện tại của TableView
        tweetTable.getItems().clear();
        TweetFunction tweetFunction = TweetFunction.getInstance(); // Khởi tạo đối tượng để đọc dữ liệu từ file hoặc nguồn dữ liệu khác
        ArrayList<Tweet> tweetList = tweetFunction.read();
        addToTable(tweetList);


    }

   
     @FXML
    void FindHottestHastagPeriod(ActionEvent event) {
        tweetTable.getItems().clear();
        TweetFunction tweetFunction = TweetFunction.getInstance();
        String val1 = date1choice.getValue().toString();
        String val2 = date2choice.getValue().toString();

        ArrayList<Tweet> tweetList = tweetFunction.tweetsWithHottestHashtagBetweenDates(val1, val2);
        addToTable(tweetList);
        
    }
   public void addToTable(ArrayList<Tweet> tweetList) {
        ContentTweetColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        HashtagTweetColumn.setCellValueFactory(new PropertyValueFactory<>("hashtag"));
        LikeTweetColumn.setCellValueFactory(new PropertyValueFactory<>("like"));
        authorTweetColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        DateTweetColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        tweetTable.getItems().addAll(tweetList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TweetFunction tweetFunction = TweetFunction.getInstance(); // Khởi tạo đối tượng để đọc dữ liệu từ file hoặc nguồn dữ liệu khác
        ArrayList<Tweet> tweetList = tweetFunction.read(); // Đọc dữ liệu từ nguồn dữ liệu và lưu vào ArrayList

        addToTable(tweetList);
    }

}
