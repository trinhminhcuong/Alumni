package com.example.cuong.alumni.DataModel;

/**
 * Created by Cuong on 4/25/2018.
 */

public class Comment {

    private String idcomment;
    private String com;
    private String topicid;
    private String usercomment;

    public Comment(String idcomment, String com, String topicid, String usercomment) {
        this.idcomment = idcomment;
        this.com = com;
        this.topicid = topicid;
        this.usercomment = usercomment;
    }

    public String getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(String idcomment) {
        this.idcomment = idcomment;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }
}
