package com.yzh.weixin.sortlistview;

import java.util.Comparator;

/**
 * Created by CharlesKim on 2015/10/20.
 */
public class PinyinComparator implements Comparator<SortModel>{

    /**
     * @author CharlesKim
     */
    public int compare(SortModel o1,SortModel o2){
        if (o2.getSortLetters().equals("#")){
            return -1;
        }else if(o1.getSortLetters().equals("#")){
            return 1;
        }else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
