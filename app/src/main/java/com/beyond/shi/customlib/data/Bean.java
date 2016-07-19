package com.beyond.shi.customlib.data;

/**实体类
 * @Package com.beyond.shi.customlib.data
 * @Title
 * @Description
 * @author WangJinya
 * @Time 2016/7/19 16:18
 */
public class Bean {

    /**
     * date : July 19
     * title : 一座城市的颜色
     * attribute : 美国，拉斯维加斯
     * para1 : 霓虹灯博物馆集馆长马里恩15年的心血，搜集了超过150件拉斯维加斯的著名标牌，通过这些展品给观众带来一场视觉的拉斯维加斯历史。
     * para2 :
     * provider : © Kerrick James/Getty Images
     * imageUrl : /th?id=OSA.B9GpJqpDR9w6vQ&pid=SatAns&w=100&h=100&c=8&rs=1
     * primaryImageUrl : http://hpimges.blob.core.chinacloudapi.cn/coverstory/watermark_neonmuseum_zh-cn8131993872_1920x1080.jpg
     * Country : 美国
     * City : 拉斯维加斯
     * Longitude : -115.135700
     * Latitude : 36.176960
     */

    private String date;
    private String title;
    private String attribute;
    private String para1;
    private String para2;
    private String provider;
    private String imageUrl;
    private String primaryImageUrl;
    private String Country;
    private String City;
    private String Longitude;
    private String Latitude;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", attribute='" + attribute + '\'' +
                ", para1='" + para1 + '\'' +
                ", para2='" + para2 + '\'' +
                ", provider='" + provider + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", primaryImageUrl='" + primaryImageUrl + '\'' +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Latitude='" + Latitude + '\'' +
                '}';
    }
}
