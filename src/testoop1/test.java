package testoop1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import model.Nft;
import function.NftFunction;
import java.util.List;
import function.TweetFunction;
import model.Tweet;
public class test {

    public static void main(String[] args) {

        String tweetList = TweetFunction.getInstance().hottestHashtagBetweenDates("2022-1-1", "2022-2-1");
        System.out.println(tweetList);
      /*
      for (Tweet tweet : tweetList) {
            System.out.println("ID: " + tweet.getId());
            System.out.println("Hashtags: " + tweet.getHashtag());
            System.out.println("Author: " + tweet.getAuthor());
            System.out.println("Likes: " + tweet.getLike());
            System.out.println("Content: " + tweet.getContent());
            System.out.println("Date: " + tweet.getDate());
            System.out.println();
        }
        
    }
       
    
      List<Nft>nftList=NftFunction.getInstance().read();
        for (Nft nft : nftList) {
            System.out.println("rank: " + nft.getRank());
            System.out.println("name: " + nft.getName());
            System.out.println("price: " + nft.getPrice());
            System.out.println("floor price: " + nft.getFloor_price());
            System.out.println("volume: " + nft.getVolume());
        }

    }
*/
}
}

