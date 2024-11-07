package com.atguigu.lease.web.app.service.impl;

import com.atguigu.lease.common.constant.RedisConstant;
import com.atguigu.lease.common.exception.LeaseException;
import com.atguigu.lease.common.result.ResultCodeEnum;
import com.atguigu.lease.common.utils.CodeUtil;
import com.atguigu.lease.common.utils.JwtUtil;
import com.atguigu.lease.model.entity.UserInfo;
import com.atguigu.lease.model.enums.BaseStatus;
import com.atguigu.lease.web.app.mapper.UserInfoMapper;
import com.atguigu.lease.web.app.service.LoginService;
import com.atguigu.lease.web.app.service.SmsService;
import com.atguigu.lease.web.app.vo.user.LoginVo;
import com.atguigu.lease.web.app.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SmsService smsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void getCode(String phone) {
        String randomCode = CodeUtil.getRandomCode(6);
        smsService.sendCode(phone, randomCode);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if(hasKey){
            Long ttl = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if(RedisConstant.APP_LOGIN_CODE_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC){
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }
        stringRedisTemplate.opsForValue().set(key, randomCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
    }

    @Override
    public String login(LoginVo loginVo) {
        if(loginVo.getPhone() == null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if(loginVo.getCode() == null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        String code = stringRedisTemplate.opsForValue().get(key);
        if(code == null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        if(!code.equals(loginVo.getCode())){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper.eq(UserInfo::getPhone, loginVo.getPhone());
        UserInfo userInfo = userInfoMapper.selectOne(userInfoLambdaQueryWrapper);
        if(userInfo == null){
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setNickname("user-" + loginVo.getPhone().substring(7));
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfoMapper.insert(userInfo);
        }else{
            if(userInfo.getStatus() == BaseStatus.DISABLE){
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }
        return JwtUtil.createToken(userInfo.getId(), loginVo.getPhone());
    }

    @Override
    public UserInfoVo getLoginUserVoById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        UserInfoVo userInfoVo = new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
        return userInfoVo;
    }
}