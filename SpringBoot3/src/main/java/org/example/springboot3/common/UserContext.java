package org.example.springboot3.common;

public class UserContext {
    //创建了一个名叫threadLocal的ThreadLocal容器，该容器只能用来装Integer类型的东西
    private static final ThreadLocal<Integer> currentUser = new ThreadLocal<>();
    private static final ThreadLocal<String> currentRole = new ThreadLocal<>();


    public static String getCurrentRole(){
        return currentRole.get();
    }

    public static Integer getCurrentUserId() {
        return currentUser.get();
    }
    public static void setCurrentRole(String role) {
        currentRole.set(role);
    }

    public static void setCurrentUser(Integer UserId) {
        currentUser.set(UserId);
    }
    public static void removeThreadId() {
        currentUser.remove();
        currentRole.remove();
    }


}
