package com.ykkj.utils.excel;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 登录用户信息
 *
 * @author 云酷科技
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 租户编号
     */
    private Long tenantId;

    // ========== UserTypeEnum.ADMIN 独有字段 ==========
    // TODO 云酷：可以通过定义一个 Map<String, String> exts 的方式，去除管理员的字段。不过这样会导致系统比较复杂，所以暂时不去掉先；
    /**
     * 角色编号数组
     */
    private Set<Long> roleIds;
    /**
     * 部门编号
     */
    private Long deptId;

    /**
     * 上次修改密码时间
     */
    private Date lastUpdatePasswordDate;

    /**
     * 失败次数
     */
    private Integer lockNum;

    /**
     * 解锁时间
     */
    private Date lockDate;

    /**
     * 锁定状态
     */
    private Integer lockStatus;
    /**
     * 需要修改密码：0或null无需修改;1首次登录修改;2密码长时间未修改
     */
    private Integer needUpdatePasswordType=0;

    /**
     * 所属公司
     */
    private Long ucIdFlag;

    /**
     * 公司编码
     */
    private String ucCode;

    /**
     * 作业公司
     */
    private Long asextUcIdFlag;

    /**
     * 作业公司
     */
    private String joinStatus;

    /**
     * 作业公司
     */
    private String nickname;

    // ========== 上下文 ==========
    /**
     * 上下文字段，不进行持久化
     *
     * 1. 用于基于 LoginUser 维度的临时缓存
     */
    @JsonIgnore
    private Map<String, Object> context;

    @Override
    @JsonIgnore// 避免序列化
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isEnabled() {
        return true;//CommonStatusEnum.ENABLE.getStatus().equals(status);
    }

    @Override
    @JsonIgnore// 避免序列化
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isAccountNonExpired() {
        return true; // 返回 true，不依赖 Spring Security 判断
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isAccountNonLocked() {
        if(null != this.lockDate && new Date().compareTo(this.lockDate) < 0&&lockStatus.equals(1)){
            return false;
        }
        return true; // 返回 true，不依赖 Spring Security 判断
    }

    @Override
    @JsonIgnore// 避免序列化
    public boolean isCredentialsNonExpired() {
        return true;  // 返回 true，不依赖 Spring Security 判断
    }

    // ========== 上下文 ==========

    public void setContext(String key, Object value) {
        if (context == null) {
            context = new HashMap<>();
        }
        context.put(key, value);
    }

    public <T> T getContext(String key, Class<T> type) {
        return MapUtil.get(context, key, type);
    }

}
