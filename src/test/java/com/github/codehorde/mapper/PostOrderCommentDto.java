package com.github.codehorde.mapper;

import java.util.List;
import java.util.Map;

/**
 * 提交评论
 * <p>
 * Created by baomingfeng at 2018-04-26 16:51:35
 */
public class PostOrderCommentDto implements java.io.Serializable {

    private Long orderId;

    private List<PostTradeCommentDto> tradeComments;

    private PostTradeCommentDto tradeComment;

    private PostTradeCommentDtoHolder holder;

    private Map<PostTradeCommentDto, List<PostTradeCommentDto>> map;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<PostTradeCommentDto> getTradeComments() {
        return tradeComments;
    }

    public void setTradeComments(List<PostTradeCommentDto> tradeComments) {
        this.tradeComments = tradeComments;
    }

    public PostTradeCommentDto getTradeComment() {
        return tradeComment;
    }

    public void setTradeComment(PostTradeCommentDto tradeComment) {
        this.tradeComment = tradeComment;
    }

    public PostTradeCommentDtoHolder getHolder() {
        return holder;
    }

    public void setHolder(PostTradeCommentDtoHolder holder) {
        this.holder = holder;
    }

    public Map<PostTradeCommentDto, List<PostTradeCommentDto>> getMap() {
        return map;
    }

    public void setMap(Map<PostTradeCommentDto, List<PostTradeCommentDto>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "PostOrderCommentDto{" +
                "orderId=" + orderId +
                ", tradeComments=" + tradeComments +
                ", tradeComment=" + tradeComment +
                ", holder=" + holder +
                ", map=" + map +
                '}';
    }
}
