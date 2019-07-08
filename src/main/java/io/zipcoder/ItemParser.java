package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import io.zipcoder.utils.match.Match;
import io.zipcoder.utils.match.MatchBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {
        List<Item> answer = new ArrayList<>();
        MatchBuilder test = new MatchBuilder();
        String[] item = valueToParse.split("##");

        for (int i = 0; i <item.length ; i++) {


                try {
                    answer.add(parseSingleItem(item[i]));
                } catch (ItemParseException e) {
                    e.printStackTrace();
                }

            }


        return answer;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        Pattern p = Pattern.compile("(?<=:|@|\\^|%|\\*)(.*?)(?=;|##|$)");
        List<String> test = new ArrayList();
        Item answer;
        Matcher m;

        String name ="";
        Double price=0.0;
        String type="";
        String experation="";
        m = p.matcher(singleItem);
        while(m.find()){
            test.add(m.group());
        }
           if(test.size() == 0){
               throw new ItemParseException();
           }
               name = test.get(0).toLowerCase();
           try {
               price = Double.parseDouble(test.get(1));
           }catch (NumberFormatException e){
               throw new ItemParseException();
           }
               type = test.get(2).toLowerCase();
           try {
               experation = test.get(3).toLowerCase();
           }catch(IndexOutOfBoundsException e){
               throw new ItemParseException();
           }

        answer = new Item(name, price, type, experation);



        return answer;
    }
}
