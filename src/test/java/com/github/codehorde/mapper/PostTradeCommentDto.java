package com.github.codehorde.mapper;

/*
import org.springframework.web.multipart.MultipartFile;
*/
import java.util.List;

/**
 * 提交评论
 * <p>
 * Created by Bao.Mingfeng at 2018-04-26 16:51:35
 */
public class PostTradeCommentDto implements java.io.Serializable {

    private Long tradeId;

    private Integer score;

    private String content;

    //private List<MultipartFile> pictures;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public void setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
    }*/

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PostTradeCommentDto{" +
                "tradeId=" + tradeId +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
