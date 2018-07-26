package com.github.codehorde.mapper.pojo;

import java.util.List;

/**
 * 提交评论
 * <p>
 * Created by Bao.Mingfeng at 2018-04-26 16:51:35
 */
public class PostTradeCommentVo implements java.io.Serializable {

    private Long tradeId;

    private Integer score;

    private String name;

    private String content;

    private List<Long> tags;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PostTradeCommentVo{" +
                "tradeId=" + tradeId +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
