package com.weixin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.framework.basic.entity.BaseEntity;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by snow on 2015/8/29.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "t_weixin_menu")
public class WeixinMenu extends BaseEntity {
    private String name;
    private String type;
    private String url;
    private String key;
    private List<WeixinMenu> sub_button;
}
