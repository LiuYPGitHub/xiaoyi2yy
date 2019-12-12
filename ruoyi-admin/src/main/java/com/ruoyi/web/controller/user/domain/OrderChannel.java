package com.ruoyi.web.controller.user.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @Description：订单渠道号
 * @Author：kk
 * @Date：2019/10/28 10:35
 */
@Data
@ToString
public class OrderChannel extends BaseEntity {

    private String id;
    private String channelName;
    private String channel;
}
