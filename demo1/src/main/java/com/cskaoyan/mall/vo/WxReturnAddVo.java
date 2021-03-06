package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Address;

public class WxReturnAddVo {
   // String detailedAddress;

    String address;
    int areaId;
    String areaName;
    int cityId;
    String cityName;
    int id;
    boolean isDefault;
    String mobile;
    String name;
    int provinceId;
    String provinceName;

  public WxReturnAddVo(Address address) {
      //this.detailedAddress=address.getProvince()+address.getCity()+address.getArea();
      this.address =address.getAddress();
      this.areaId = address.getAreaId();
      this.areaName = address.getArea();
      this.cityId = address.getCityId();
      this.cityName = address.getCity();
      this.id = address.getId();
      this.isDefault = address.getIsDefault();
      this.mobile = address.getMobile();
      this.name = address.getName();
      this.provinceId = address.getProvinceId();
      this.provinceName = address.getProvince();
  }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
