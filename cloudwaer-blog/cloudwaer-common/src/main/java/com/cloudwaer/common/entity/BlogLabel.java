package com.cloudwaer.common.entity;


public class BlogLabel {

  private long id;
  private String labelName;
  private String labelCode;
  private String labelBgimg;
  private long labelHeat;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }


  public String getLabelCode() {
    return labelCode;
  }

  public void setLabelCode(String labelCode) {
    this.labelCode = labelCode;
  }


  public String getLabelBgimg() {
    return labelBgimg;
  }

  public void setLabelBgimg(String labelBgimg) {
    this.labelBgimg = labelBgimg;
  }


  public long getLabelHeat() {
    return labelHeat;
  }

  public void setLabelHeat(long labelHeat) {
    this.labelHeat = labelHeat;
  }

}
