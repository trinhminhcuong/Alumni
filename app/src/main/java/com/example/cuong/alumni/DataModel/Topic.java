package com.example.cuong.alumni.DataModel;

/**
 * Created by Cuong on 4/25/2018.
 */

public class Topic {

    private String idtopic;
    private String user;
    private String topicName;
    private String content;

    public Topic(String idtopic,String user, String topicName, String content) {
        this.idtopic=idtopic;
        this.user = user;
        this.topicName = topicName;
        this.content = content;
    }

    public String getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(String idtopic) {
        this.idtopic = idtopic;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
