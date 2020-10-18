package com.wgs.refactor.demo1.demo;

public class 职责不单一的方法_06 {

    /**
     * 查询用户信息
     *  create=true ：用户不存在则创建一个
     *  create=false: 纯查询
     *
     *  ----
     *  缺点：
     *  违反单一职责原则
     *  方法名词不达意，是查询但是做了新增的事情
     *
     * @param params
     * @return
     */
    public UserDTO queryUser1(QueryParams params) {
        UserDTO user = this.queryUserByParams(params);

        // 不存在且需要创建
        if (params.create && user == null) {
            return this.createNewUser(params);
        }

        return user;
    }

    /******************************   重构 职责单一法则  ************************/
    // 只是用来查询的
    public UserDTO queryUser(QueryParams params) {
        return  this.queryUserByParams(params);
    }

    public UserDTO createUser(QueryParams params) {
        return this.createNewUser(params);
    }
    /***************************************************************************/


    private UserDTO createNewUser(QueryParams params) {
        // 插入用户
        return null;
    }

    private UserDTO queryUserByParams(QueryParams params) {
        return null;
    }


    static class QueryParams {
        private Integer userId;
        private Boolean create;
    }

    static class UserDTO {
        private Integer userId;

    }
}


