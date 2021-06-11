package com.cloudwaer.common.entity;


public class BlogCartgory {

  private long id;
  private String catrgoryName;
  private String catrgoryCode;
  private long cartgoryHeat;
  private String cartgoryBgimg;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCatrgoryName() {
    return catrgoryName;
  }

  public void setCatrgoryName(String catrgoryName) {
    this.catrgoryName = catrgoryName;
  }


  public String getCatrgoryCode() {
    return catrgoryCode;
  }

  public void setCatrgoryCode(String catrgoryCode) {
    this.catrgoryCode = catrgoryCode;
  }


  public long getCartgoryHeat() {
    return cartgoryHeat;
  }

  public void setCartgoryHeat(long cartgoryHeat) {
    this.cartgoryHeat = cartgoryHeat;
  }


  public String getCartgoryBgimg() {
    return cartgoryBgimg;
  }

  public void setCartgoryBgimg(String cartgoryBgimg) {
    this.cartgoryBgimg = cartgoryBgimg;
  }

}
