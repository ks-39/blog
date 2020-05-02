package com.ks39.myblog.util;

import com.ks39.myblog.model.entity.tag;
import org.springframework.util.StringUtils;

import java.util.List;

public class TagsConverter {

    public static tag[] parse(String str){
        if(StringUtils.isEmpty(str)) return null ;
        String[] tagStrArr = str.split(";");
        if(tagStrArr.length == 0){
            return null ;
        }
        tag[] tags = new tag[tagStrArr.length];

        int index = 0 ;
        for (String s : tagStrArr) {
            tag tag = new tag();
            tag.setTag_name(s);
            tags[index ++ ] =tag ;
        }

        return tags ;
    }

    public static String parse(tag[] tags){
        if(tags == null ||  tags.length == 0)
            return "" ;
        StringBuilder stringBuilder = new StringBuilder() ;

        for (tag tag : tags) {
            stringBuilder.append(tag.getTag_name());
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }

    public static String parse(List<tag> tags){
        if(tags == null ||  tags.size() == 0)
            return "" ;
        StringBuilder stringBuilder = new StringBuilder() ;

        for (tag tag : tags) {
            stringBuilder.append(tag.getTag_name());
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }
}
