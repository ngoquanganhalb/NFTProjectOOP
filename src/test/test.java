package test;

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
import java.util.ArrayList;
import model.Tweet;
public class test {
    
    public static void main(String[] args) {
         ArrayList<Nft>nftList=NftFunction.getInstance().sortDiscount();
       for (Nft nft : nftList) {
           System.out.println("num: " + nft.getNum());
            System.out.println("rank: " + nft.getRank());
            System.out.println("floor price: " + nft.getFloor_price());
             System.out.println("avg: " + nft.getAvg_price());
            
        }
    
    }
}
       /*
 for (Nft nft : nftList) {
            System.out.println("rank: " + nft.getRank());
            System.out.println("name: " + nft.getName());
            System.out.println("price: " + nft.getPrice());
            System.out.println("floor price: " + nft.getFloor_price());
            System.out.println("volume: " + nft.getVolume());
        }

    }
*/
    
     



