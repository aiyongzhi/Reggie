package com.ayz.reggie.common;
/*
* 因为前端每次发出request请求，服务器都会为这次请求分配
* 一个新的线程，我们可以利用线程的ThreadLocal在请求到Controller的时候保存当前
* 用户的id到ThreadLocal中这样，我们就可以在MetaObjectHandler中动态获取到当前
* 用户的id
* */
public class UserIdContextHolder {
    private static final ThreadLocal<Long> CONTEXT_HOLDER=new ThreadLocal<>();

    public static void setContextHolder(Long id){
        CONTEXT_HOLDER.set(id);
    }
    public static Long getContextHolder(){
        return CONTEXT_HOLDER.get();
    }
    public static void remove(){
        CONTEXT_HOLDER.remove();
    }
}
