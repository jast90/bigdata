package cn.jast.esapisample.util;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import cn.jast.esapisample.modal.Post;

public class Jsons {

    private static ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static String objectToJSON(Object object)  {
        try {
            return objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        Post post = new Post();
        post.setDate(new Date());
        post.setUser("jast");
        post.setMessage("helo");
        System.out.println(Jsons.objectToJSON(post));
    }
}
