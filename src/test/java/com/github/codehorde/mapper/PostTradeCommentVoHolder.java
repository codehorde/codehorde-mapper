package com.github.codehorde.mapper;

/**
 * Created by baomingfeng at 2018-04-28 16:04:14
 */
public class PostTradeCommentVoHolder {

    private PostTradeCommentVo value;

    public PostTradeCommentVoHolder() {
    }

    public PostTradeCommentVoHolder(PostTradeCommentVo value) {
        this.value = value;
    }

    public PostTradeCommentVo getValue() {
        return value;
    }

    public void setValue(PostTradeCommentVo value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PostTradeCommentVoHolder{" +
                "value=" + value +
                '}';
    }
}