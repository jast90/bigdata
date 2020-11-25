package cn.jast.esapisample.csdn.modal;

import java.util.Date;

public class ArticleListItem {

    private String title;
    private String desc;
    private long id;
    private Date createTime;
    private long viewCount;
    private long commentCount;
    private String articleUrl;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }


    public String getArticleUrl() {
        return this.articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
