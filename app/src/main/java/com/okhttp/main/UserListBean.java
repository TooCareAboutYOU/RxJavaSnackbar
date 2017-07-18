package com.okhttp.main;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class UserListBean {


    /*
    * http://ad.wodpy.com:81/meplusd/attentionVideoHome.html?uid=23434&version=1.0&plat=a
    * */

    /**
     * errorCode : 0
     * errorMessage : success
     * data : {"other":[{"activityCategory":"","activityId":"11216611488355155832i","activityName":"2222222","activityStatus":2,"channelId":"1121661","coverImgUrl":"https://y1.cnliveimg.com/mobile/images/mobilehead/2016/12/28/1482908632976_small.jpg","duration":0,"endTime":"20170301154610","extensions":"","isHorizontalScreen":0,"showType":4,"startTime":"20170301153537","nickName":"1121661","faceUrl":"1121661","vipLevel":"1","personTime":"3432","address":"火星","gender":"m","meActionType":"1"}]}
     */

    private String errorCode;
    private String errorMessage;
    private DataBean data;

    @Override
    public String toString() {
        return "UserListBean{" +
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
        private List<OtherBean> other;

        @Override
        public String toString() {
            return "DataBean{" +
                    "other=" + other +
                    '}';
        }

        public List<OtherBean> getOther() {
            return other;
        }

        public void setOther(List<OtherBean> other) {
            this.other = other;
        }

        public static class OtherBean {
            /**
             * activityCategory :
             * activityId : 11216611488355155832i
             * activityName : 2222222
             * activityStatus : 2
             * channelId : 1121661
             * coverImgUrl : https://y1.cnliveimg.com/mobile/images/mobilehead/2016/12/28/1482908632976_small.jpg
             * duration : 0
             * endTime : 20170301154610
             * extensions :
             * isHorizontalScreen : 0
             * showType : 4
             * startTime : 20170301153537
             * nickName : 1121661
             * faceUrl : 1121661
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
                return "OtherBean{" +
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
