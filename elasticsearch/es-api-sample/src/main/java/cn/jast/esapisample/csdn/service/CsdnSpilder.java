package cn.jast.esapisample.csdn.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import cn.jast.esapisample.csdn.modal.Article;
import cn.jast.esapisample.csdn.modal.ArticleListItem;
import cn.jast.esapisample.util.Jsons;

@Service
public class CsdnSpilder {

    private String categoryUrl1 = "https://elasticstack.blog.csdn.net/category_9209092_%s.html";
    private String categoryUrl = String.format("https://elasticstack.blog.csdn.net/category_9209092_%s.html",1);
    private String articleUrl = "https://elasticstack.blog.csdn.net/article/details/%s";

    public long totalPage() throws IOException {
        Document doc = Jsoup.connect(categoryUrl).get();
        System.out.println(doc.html());
        String lastPageNumStr = doc.select(".ui-pager").last().html();
        return Long.parseLong(lastPageNumStr);

    } 

    public List<Article> getArticleListItems(long totalPage) throws IOException {
        List<Article> list = new ArrayList<>();
        for(long i = 1 ; i<totalPage ; i++){
            Document doc = Jsoup.connect(String.format(categoryUrl1, i)).get();
            Elements els = doc.selectFirst(".column_article_list").select("li");
            els.forEach(el->{
                Article article = new Article();
                article.setTitle(el.select(".column_article_title").select(".title").html());
                article.setDesc(el.select(".column_article_desc").html());
                article.setUrl(el.select("a").attr("href"));
                String[] splits = el.select("a").attr("href").split("/");
                article.setId(Long.parseLong(splits[splits.length-1]));
                Elements spans = el.select("column_article_data").select("span");
                for(int j = 0;j<spans.size();j++){
                    if(j==0){

                    }else if(j==1){
                        
                    }else{

                    }
                }
                
                list.add(article);
            });
        }
        return list;
    }

    public void getById(Article article) throws IOException {
        Document doc = Jsoup.connect(String.format(articleUrl, article.getId())).get();
        article.setContent(doc.select("#content_views").html());
    }


}
