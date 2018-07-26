package com.github.codehorde.mapper;

import com.github.codehorde.mapper.pojo.*;
import net.sf.cglib.core.DebuggingClassWriter;

import java.util.*;

/**
 * Created by Bao.Mingfeng at 2018-05-02 11:19:36
 */
public class BeanCopierTest {

    public static void main(String[] args) {

        String debugFolder = System.getProperty("user.home") + "/Downloads/cglib";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, debugFolder);

        test1();
        test2();
    }

    private static void test1() {
        PostOrderCommentVo commentVo = new PostOrderCommentVo();
        commentVo.setOrderId(3L);
        List<PostTradeCommentVo> tradeComments = new ArrayList<>();
        PostTradeCommentVo tradeComment = new PostTradeCommentVo();
        tradeComment.setTradeId(33L);
        tradeComment.setScore(5);
        tradeComment.setContent("五星好评！");
        tradeComment.setTags(Arrays.asList(56L, 37L));
        tradeComments.add(tradeComment);
        tradeComment = new PostTradeCommentVo();
        tradeComment.setTradeId(34L);
        tradeComment.setScore(4);
        tradeComment.setContent("赞，五星好评！");
        tradeComment.setTags(Arrays.asList(65L, 73L));
        tradeComments.add(tradeComment);

        commentVo.setTradeComments(tradeComments);

        tradeComment = new PostTradeCommentVo();
        tradeComment.setTradeId(57L);
        tradeComment.setScore(3);
        tradeComment.setContent("五星！");
        tradeComment.setTags(Arrays.asList(87L, 93L));
        commentVo.setTradeComment(tradeComment);

        tradeComment = new PostTradeCommentVo();
        tradeComment.setTradeId(67L);
        tradeComment.setScore(2);
        tradeComment.setContent("五星XXX！");
        tradeComment.setTags(Arrays.asList(91L, 7L, 3L));
        commentVo.setHolder(new PostTradeCommentVoHolder(tradeComment));

        Map<PostTradeCommentVo, List<PostTradeCommentVo>> map = new HashMap<>();
        PostTradeCommentVo key = new PostTradeCommentVo();
        key.setTradeId(111L);
        key.setScore(111);
        key.setContent("Key");
        key.setTags(Arrays.asList(111L, 111L));

        List<PostTradeCommentVo> valueList = new ArrayList<>();
        PostTradeCommentVo value1 = new PostTradeCommentVo();
        value1.setTradeId(222L);
        value1.setScore(222);
        value1.setContent("Value1");
        value1.setTags(Arrays.asList(222L, 222L));
        valueList.add(value1);
        PostTradeCommentVo value2 = new PostTradeCommentVo();
        value2.setTradeId(333L);
        value2.setScore(333);
        value2.setContent("Value2");
        value2.setTags(Arrays.asList(333L, 333L));
        valueList.add(value2);
        map.put(key, valueList);
        commentVo.setMap(map);

        Object retVal = BeanMapper.deepMapBy(commentVo, PostOrderCommentDto.class);

        ConsoleUtils.prettyPrint(retVal);
    }

    @SuppressWarnings("unchecked")
    private static void test2() {
        List<PostTradeCommentVo> valueList = new ArrayList<>();
        PostTradeCommentVo value1 = new PostTradeCommentVo();
        value1.setTradeId(222L);
        value1.setScore(222);
        value1.setContent("Value1");
        value1.setTags(Arrays.asList(222L, 222L));
        valueList.add(value1);
        PostTradeCommentVo value2 = new PostTradeCommentVo();
        value2.setTradeId(333L);
        value2.setScore(333);
        value2.setContent("Value2");
        value2.setTags(Arrays.asList(333L, 333L));
        valueList.add(value2);

        PageResult<PostTradeCommentVo> postTradeCommentVoPageResult = new PageResult<>();
        postTradeCommentVoPageResult.setPageNo(7);
        postTradeCommentVoPageResult.setPageSize(3);
        postTradeCommentVoPageResult.setTotal(703L);
        postTradeCommentVoPageResult.setData(valueList);

        List<PostTradeCommentVo>[] datas = new List[2];
        PostTradeCommentVo datas0 = new PostTradeCommentVo();
        datas0.setName("datas0");
        PostTradeCommentVo datas1 = new PostTradeCommentVo();
        datas1.setName("datas1");
        datas[0] = Arrays.asList(datas0, datas1);
        PostTradeCommentVo datas10 = new PostTradeCommentVo();
        datas10.setName("datas10");
        PostTradeCommentVo datas11 = new PostTradeCommentVo();
        datas11.setName("datas11");
        datas[1] = Arrays.asList(datas10, datas11);
        postTradeCommentVoPageResult.setDatas(datas);

        PostTradeCommentVo[] items = new PostTradeCommentVo[2];
        items[0] = new PostTradeCommentVo();
        items[0].setContent("items 0");
        items[1] = new PostTradeCommentVo();
        items[1].setContent("items 1");
        postTradeCommentVoPageResult.setItems(items);

        PageResult<PostTradeCommentDto> postTradeCommentDtoPageResult
                = BeanMapper.deepMapBy(postTradeCommentVoPageResult, new TypeRef<PageResult<PostTradeCommentDto>>() {
        });


        ConsoleUtils.prettyPrint(postTradeCommentVoPageResult);
        ConsoleUtils.prettyPrint(postTradeCommentDtoPageResult);

        List<PostTradeCommentDto> dtoList
                = BeanMapper.deepMapBy(valueList, new TypeRef<List<PostTradeCommentDto>>() {
        });

        ConsoleUtils.prettyPrint(dtoList);
    }
}
