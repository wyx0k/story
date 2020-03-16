package indi.wyx0k.story.core.entity;

/**
 * story
 *
 * @Author wyx0k
 * 2019/12/8
 **/
public class Memo {
    // id
    String id;
    // 标题
    String title;
    //照片路径
    String photoURL;
    // 描述 
    String description;
    //创建时间
    String createTime;
    // 修改时间
    String modifyTime;
    // 用户
    String userId;
    //是否对他人显示
    Boolean showToOther;
    //心情
    Integer emotion;
    //标签
    String tag;
    // 地点
    String location;
    // 经度
    Double locationX;
    //纬度
    Double locationY;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getShowToOther() {
        return showToOther;
    }

    public void setShowToOther(Boolean showToOther) {
        this.showToOther = showToOther;
    }

    public Integer getEmotion() {
        return emotion;
    }

    public void setEmotion(Integer emotion) {
        this.emotion = emotion;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLocationX() {
        return locationX;
    }

    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }

    public Double getLocationY() {
        return locationY;
    }

    public void setLocationY(Double locationY) {
        this.locationY = locationY;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userId='" + userId + '\'' +
                ", showToOther=" + showToOther +
                ", emotion=" + emotion +
                ", tag='" + tag + '\'' +
                ", location='" + location + '\'' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                '}';
    }
}
