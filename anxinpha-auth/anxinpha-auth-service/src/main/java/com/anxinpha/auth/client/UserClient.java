package com.anxinpha.auth.client;

import com.anxinpha.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 尹硕硕
 * @description
 **/
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
