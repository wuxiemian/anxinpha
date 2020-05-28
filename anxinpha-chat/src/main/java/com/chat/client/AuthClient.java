package com.chat.client;

import com.anxinpha.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 尹硕硕
 * @description
 **/
@FeignClient("auth-service")
public interface AuthClient extends AuthApi {
}
