package com.liushihao.learnhub.frequancycontrol.strategy;

import com.liushihao.learnhub.dto.TokenBucketDTO;
import com.liushihao.learnhub.frequancycontrol.AbstractFrequencyControlService;
import com.liushihao.learnhub.manager.TokenBucketManager;
import com.liushihao.learnhub.utils.FrequencyControlConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抽象类频控服务 -使用redis实现 维护一个令牌桶来限制操作的发生次数
 *
 * @author admin
 */
@Slf4j
@Service
public class TokenBucketFrequencyController extends AbstractFrequencyControlService<TokenBucketDTO> {

    private TokenBucketManager tokenBucketManager;

    @Autowired
    public void setTokenBucketManager(TokenBucketManager tokenBucketManager) {
        this.tokenBucketManager = tokenBucketManager;
    }

    @Override
    protected boolean reachRateLimit(Map<String, TokenBucketDTO> frequencyControlMap) {
        // 批量获取redis统计的值
        List<String> frequencyKeys = new ArrayList<>(frequencyControlMap.keySet());
        for (String key : frequencyKeys) {
            // 获取 1 个令牌
            return tokenBucketManager.tryAcquire(key, 1);
        }
        return false;
    }

    @Override
    protected void addFrequencyControlStatisticsCount(Map<String, TokenBucketDTO> frequencyControlMap) {
        List<String> frequencyKeys = new ArrayList<>(frequencyControlMap.keySet());
        for (int i = 0; i < frequencyKeys.size(); i++) {
            String key = frequencyKeys.get(i);
            TokenBucketDTO tokenBucketDTO = frequencyControlMap.get(key);
            tokenBucketManager.createTokenBucket(key, tokenBucketDTO.getCapacity(), tokenBucketDTO.getRefillRate());
            // 扣减 1 个令牌
            tokenBucketManager.deductionToken(key, 1);
        }
    }

    @Override
    protected String getStrategyName() {
        return FrequencyControlConstant.TOKEN_BUCKET;
    }
}
