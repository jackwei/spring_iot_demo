package com.ykkj.annotation;

import com.ykkj.enums.TransferStrategyEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserVO implements Serializable {

    @Transfer(transferStrategy = TransferStrategyEnum.ADMIN_USERNAME,targetFieldName = "userNameTransfer")
    private Integer id;

    private String userName;

    private String userNameTransfer;

    @Transfer(transferStrategy = TransferStrategyEnum.FACE_URL,overrideSelf = false,targetFieldName = "faceUrlTransfer")
    private String faceUrl;

    private String faceUrlTransfer;

    public static void main(String[] args) {

        AdminUserVO adminUserVO = new AdminUserVO();
        System.out.println(adminUserVO.getId());
    }

}

