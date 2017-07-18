package com.okhttp.main;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class ParentData {

    /*
    * http://ad.wodpy.com:81/meplusd/hotVideoHome.html
    * */

    /**
     * errorCode : 0
     * errorMessage : success
     * data : {"banner":["https://img.shineup.com.cn/goldenline/4n33a9qhcocc.png","https://img.shineup.com.cn/goldenline/4n33aa2fkho4.png","https://img.shineup.com.cn/goldenline/4n2vlmu5j7eg.jpg"],"listHot":[{"activityCategory":"","activityId":"12226791488859866524","activityName":"就可以舞台剧","activityStatus":1,"channelId":"1222679","coverImgUrl":"https://y3.cnliveimg.com/mobile/images/mobilehead/2016/11/29/1480384460148_small.jpg","duration":0,"endTime":"","extensions":"","isHorizontalScreen":0,"showType":3,"startTime":"20170307114714","nickName":"1222679","faceUrl":"1222679","vipLevel":"1","personTime":"3432","address":"火星","gender":"m","meActionType":"1"},{"activityCategory":"","activityId":"12226791488859629764","activityName":"好low中","activityStatus":1,"channelId":"1222679","coverImgUrl":"https://y3.cnliveimg.com/mobile/images/mobilehead/2016/11/29/1480384460148_small.jpg","duration":0,"endTime":"","extensions":"","isHorizontalScreen":0,"showType":3,"startTime":"20170307114314","nickName":"1222679","faceUrl":"1222679","vipLevel":"1","personTime":"3432","address":"火星","gender":"m","meActionType":"1"},{"activityCategory":"","activityId":"12226791488859577119","activityName":"数据库营销","activityStatus":1,"channelId":"1222679","coverImgUrl":"https://y3.cnliveimg.com/mobile/images/mobilehead/2016/11/29/1480384460148_small.jpg","duration":0,"endTime":"","extensions":"","isHorizontalScreen":0,"showType":3,"startTime":"20170307114222","nickName":"1222679","faceUrl":"1222679","vipLevel":"1","personTime":"3432","address":"火星","gender":"m","meActionType":"1"}]}
     */

    private String errorCode;
    private String errorMessage;
    private DataBean data;

    @Override
    public String toString() {
        return "ParentData{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> banner;
        private List<ListHotBean> listHot;

        @Override
        public String toString() {
            return "DataBean{" +
                    "banner=" + banner +
                    ", listHot=" + listHot +
                    '}';
        }

        public List<String> getBanner() {
            return banner;
        }

        public void setBanner(List<String> banner) {
            this.banner = banner;
        }

        public List<ListHotBean> getListHot() {
            return listHot;
        }

        public void setListHot(List<ListHotBean> listHot) {
            this.listHot = listHot;
        }

        public static class ListHotBean {
            /**
             * activityCategory :
             * activityId : 12226791488859866524
             * activityName : 就可以舞台剧
             * activityStatus : 1
             * channelId : 1222679
             * coverImgUrl : https://y3.cnliveimg.com/mobile/images/mobilehead/2016/11/29/1480384460148_small.jpg
             * duration : 0
             * endTime :
             * extensions :
             * isHorizontalScreen : 0
             * showType : 3
             * startTime : 20170307114714
             * nickName : 1222679
             * faceUrl : 1222679
             * vipLevel : 1
             * personTime : 3432
             * address : 火星
             * gender : m
             * meActionType : 1
             */

            private String activityCategory;
            private String activityId;
            private String activityName;
            private int activityStatus;
            private String channelId;
            private String coverImgUrl;
            private int duration;
            private String endTime;
            private String extensions;
            private int isHorizontalScreen;
            private int showType;
            private String startTime;
            private String nickName;
            private String faceUrl;
            private String vipLevel;
            private String personTime;
            private String address;
            private String gender;
            private String meActionType;

            @Override
            public String toString() {
                return "ListHotBean{" +
                        "activityCategory='" + activityCategory + '\'' +
                        ", activityId='" + activityId + '\'' +
                        ", activityName='" + activityName + '\'' +
                        ", activityStatus=" + activityStatus +
                        ", channelId='" + channelId + '\'' +
                        ", coverImgUrl='" + coverImgUrl + '\'' +
                        ", duration=" + duration +
                        ", endTime='" + endTime + '\'' +
                        ", extensions='" + extensions + '\'' +
                        ", isHorizontalScreen=" + isHorizontalScreen +
                        ", showType=" + showType +
                        ", startTime='" + startTime + '\'' +
                        ", nickName='" + nickName + '\'' +
                        ", faceUrl='" + faceUrl + '\'' +
                        ", vipLevel='" + vipLevel + '\'' +
                        ", personTime='" + personTime + '\'' +
                        ", address='" + address + '\'' +
                        ", gender='" + gender + '\'' +
                        ", meActionType='" + meActionType + '\'' +
                        '}';
            }

            public String getActivityCategory() {
                return activityCategory;
            }

            public void setActivityCategory(String activityCategory) {
                this.activityCategory = activityCategory;
            }

            public String getActivityId() {
                return activityId;
            }

            public void setActivityId(String activityId) {
                this.activityId = activityId;
            }

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public int getActivityStatus() {
                return activityStatus;
            }

            public void setActivityStatus(int activityStatus) {
                this.activityStatus = activityStatus;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getCoverImgUrl() {
                return coverImgUrl;
            }

            public void setCoverImgUrl(String coverImgUrl) {
                this.coverImgUrl = coverImgUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getExtensions() {
                return extensions;
            }

            public void setExtensions(String extensions) {
                this.extensions = extensions;
            }

            public int getIsHorizontalScreen() {
                return isHorizontalScreen;
            }

            public void setIsHorizontalScreen(int isHorizontalScreen) {
                this.isHorizontalScreen = isHorizontalScreen;
            }

            public int getShowType() {
                return showType;
            }

            public void setShowType(int showType) {
                this.showType = showType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getFaceUrl() {
                return faceUrl;
            }

            public void setFaceUrl(String faceUrl) {
                this.faceUrl = faceUrl;
            }

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getPersonTime() {
                return personTime;
            }

            public void setPersonTime(String personTime) {
                this.personTime = personTime;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getMeActionType() {
                return meActionType;
            }

            public void setMeActionType(String meActionType) {
                this.meActionType = meActionType;
            }
        }
    }
}
