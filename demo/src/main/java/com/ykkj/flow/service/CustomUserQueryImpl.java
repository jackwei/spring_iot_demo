package com.ykkj.flow.service;

import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.common.engine.impl.interceptor.CommandExecutor;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.UserQueryImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;

import java.util.*;
import java.util.stream.Collectors;

public class CustomUserQueryImpl extends UserQueryImpl {

    /**
     * Map<UserId,Username>
     */
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        USERS.put("1", "user1");
        USERS.put("2", "user2");
        USERS.put("3", "user3");
        USERS.put("admin", "admin");
        USERS.put("4", "admin");
    }

    public CustomUserQueryImpl() {
    }

    public CustomUserQueryImpl(CommandContext commandContext) {
        super(commandContext);
    }

    public CustomUserQueryImpl(CommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public long executeCount(CommandContext commandContext) {
        return this.executeList(commandContext).size();
    }

    @Override
    public List<User> executeList(CommandContext commandContext) {
        return queryUsers();
    }

    private List<User> queryUsers() {
        List<User> result = new ArrayList<>();
        if (this.getIdIgnoreCase() != null) {
            Optional.ofNullable(USERS.get(this.getIdIgnoreCase())).map(this::buildUser).ifPresent(result::add);
            //result.add(buildUser("admin"));
        } else if (this.getIds() != null) {
            result.addAll(USERS.entrySet()
                    .stream()
                    .filter(entry -> this.getIds().contains(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .map(this::buildUser)
                    .collect(Collectors.toList()));
        }
        return result;
    }

    private UserEntityImpl buildUser(String username) {
        UserEntityImpl userEntity = new UserEntityImpl();
        userEntity.setId(username);
        userEntity.setFirstName(username);
        userEntity.setLastName(username);
        userEntity.setDisplayName(username);
        userEntity.setPassword("test");
        userEntity.setInserted(true);
        userEntity.setUpdated(true);
        userEntity.setDeleted(true);
        return userEntity;
    }
}