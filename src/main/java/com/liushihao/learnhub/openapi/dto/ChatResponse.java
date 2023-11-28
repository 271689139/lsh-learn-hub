package com.liushihao.learnhub.openapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 * 返回值
 */
@Data
public class ChatResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private int index;
        private Message message;
    }
}
