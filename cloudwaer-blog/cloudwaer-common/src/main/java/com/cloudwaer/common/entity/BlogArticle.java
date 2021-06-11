package com.cloudwaer.common.entity;


public class BlogArticle {

  private long id;
  private String articleUniqueCode;
  private String articleTitle;
  private String articleImage;
  private String articleContent;
  private String articleTags;
  private long articleEnableComment;
  private long articleViews;
  private java.sql.Timestamp articleCreatetime;
  private long articleDelflag;
  private java.sql.Timestamp articleUpdatetim;
  private String articleCreatecode;
  private String articleUpdatecode;
  private long articleHot;
  private long articleOrder;
  private String catrgoryCode;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getArticleUniqueCode() {
    return articleUniqueCode;
  }

  public void setArticleUniqueCode(String articleUniqueCode) {
    this.articleUniqueCode = articleUniqueCode;
  }


  public String getArticleTitle() {
    return articleTitle;
  }

  public void setArticleTitle(String articleTitle) {
    this.articleTitle = articleTitle;
  }


  public String getArticleImage() {
    return articleImage;
  }

  public void setArticleImage(String articleImage) {
    this.articleImage = articleImage;
  }


  public String getArticleContent() {
    return articleContent;
  }

  public void setArticleContent(String articleContent) {
    this.articleContent = articleContent;
  }


  public String getArticleTags() {
    return articleTags;
  }

  public void setArticleTags(String articleTags) {
    this.articleTags = articleTags;
  }


  public long getArticleEnableComment() {
    return articleEnableComment;
  }

  public void setArticleEnableComment(long articleEnableComment) {
    this.articleEnableComment = articleEnableComment;
  }


  public long getArticleViews() {
    return articleViews;
  }

  public void setArticleViews(long articleViews) {
    this.articleViews = articleViews;
  }


  public java.sql.Timestamp getArticleCreatetime() {
    return articleCreatetime;
  }

  public void setArticleCreatetime(java.sql.Timestamp articleCreatetime) {
    this.articleCreatetime = articleCreatetime;
  }


  public long getArticleDelflag() {
    return articleDelflag;
  }

  public void setArticleDelflag(long articleDelflag) {
    this.articleDelflag = articleDelflag;
  }


  public java.sql.Timestamp getArticleUpdatetim() {
    return articleUpdatetim;
  }

  public void setArticleUpdatetim(java.sql.Timestamp articleUpdatetim) {
    this.articleUpdatetim = articleUpdatetim;
  }


  public String getArticleCreatecode() {
    return articleCreatecode;
  }

  public void setArticleCreatecode(String articleCreatecode) {
    this.articleCreatecode = articleCreatecode;
  }


  public String getArticleUpdatecode() {
    return articleUpdatecode;
  }

  public void setArticleUpdatecode(String articleUpdatecode) {
    this.articleUpdatecode = articleUpdatecode;
  }


  public long getArticleHot() {
    return articleHot;
  }

  public void setArticleHot(long articleHot) {
    this.articleHot = articleHot;
  }


  public long getArticleOrder() {
    return articleOrder;
  }

  public void setArticleOrder(long articleOrder) {
    this.articleOrder = articleOrder;
  }


  public String getCatrgoryCode() {
    return catrgoryCode;
  }

  public void setCatrgoryCode(String catrgoryCode) {
    this.catrgoryCode = catrgoryCode;
  }

}
