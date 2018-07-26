package com.github.codehorde.mapper.pojo;

import java.util.List;
import java.util.Map;

/**
 * 提交评论
 * <p>
 * Created by Bao.Mingfeng at 2018-04-26 16:51:35
 */
public class PostOrderCommentVo implements java.io.Serializable {

    private Long orderId;

    private List<PostTradeCommentVo> tradeComments;

    private PostTradeCommentVo tradeComment;

    private PostTradeCommentVoHolder holder;

    private Map<PostTradeCommentVo, List<PostTradeCommentVo>> map;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<PostTradeCommentVo> getTradeComments() {
        return tradeComments;
    }

    public void setTradeComments(List<PostTradeCommentVo> tradeComments) {
        this.tradeComments = tradeComments;
    }

    public PostTradeCommentVo getTradeComment() {
        return tradeComment;
    }

    public void setTradeComment(PostTradeCommentVo tradeComment) {
        this.tradeComment = tradeComment;
    }

    public PostTradeCommentVoHolder getHolder() {
        return holder;
    }

    public void setHolder(PostTradeCommentVoHolder holder) {
        this.holder = holder;
    }

    public Map<PostTradeCommentVo, List<PostTradeCommentVo>> getMap() {
        return map;
    }

    public void setMap(Map<PostTradeCommentVo, List<PostTradeCommentVo>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "PostOrderCommentVo{" +
                "orderId=" + orderId +
                ", tradeComments=" + tradeComments +
                ", tradeComment=" + tradeComment +
                ", holder=" + holder +
                ", map=" + map +
                '}';
    }
}
