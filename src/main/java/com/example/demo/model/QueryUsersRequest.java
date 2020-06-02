package com.example.demo.model;

public class QueryUsersRequest {

  PageArg pageArg;

  private Integer id;

  private String name;

  private Integer age;

  public PageArg getPageArg() {
    return pageArg;
  }

  public void setPageArg(PageArg pageArg) {
    this.pageArg = pageArg;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
