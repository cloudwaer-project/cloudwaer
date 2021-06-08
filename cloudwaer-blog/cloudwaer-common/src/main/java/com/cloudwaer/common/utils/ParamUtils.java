package com.cloudwaer.common.utils;

import com.cloudwaer.common.dto.ResponseCode;
import com.cloudwaer.common.dto.ResponseDto;
import com.cloudwaer.common.exception.BusinessException;
import com.cloudwaer.common.exception.ParamsException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ParamUtils
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 13:37
 * @Version 1.0
 **/
public class ParamUtils {
    private static ThreadLocal<String> param = new ThreadLocal<>();

    /**
     * 判断一个对象中指定的参数集是否存在空值
     * 支持对象中含有List
     * 支持List遍历、父类遍历
     * @param obj
     * @param argsName
     */
    public static ResponseDto checkParmsNotNull(Object obj, String ... argsName) {
        List<String> args = Arrays.asList(argsName);
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsNotNull(args, f, obj, argsName);
            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsNotNull(obj, obj.getClass().getSuperclass(), argsName);
            }
            return ResponseUtils.buildVoByResponseCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            String errorParam = param.get();
            param.remove();
            return ResponseUtils.buildVoByResponseCode(ResponseCode.PARAMS_ERROR,setMsgResult("参数：{} 不能为空", errorParam));
        }
    }

    /**
     * 判断一个对象中，除了id、addTime、updateTime、delFlag以及传入的属性名以外的属性是否存在空值
     * 如果存在一个空值，抛出异常
     * 支持List遍历、父类遍历
     *
     * @return
     */
    public static void isParamsExceNull(Object obj, String... argsName) {
        List<String> args = getArgs(argsName);
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsExceNull(args, f, obj, argsName);
            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsExceNull(obj, obj.getClass().getSuperclass(), argsName);
            }
        } catch (BusinessException | IllegalAccessException e) {
            String errorParam = param.get();
            param.remove();
            throw new ParamsException(ResponseCode.PARAMS_ERROR, "参数：{} 的值不能为空", errorParam);
        }
    }

    /**
     * 判断一个对象中，除了id、addTime、updateTime、delFlag以及传入的属性名以外的属性是否全部为空值
     * 支持对象中含有List
     * 支持List遍历、父类遍历
     * 全部为空抛出异常
     *
     * @return
     */
    public static void isParamsExceNotAllNull(Object obj, String... argsName) {
        List<String> args = getArgs(argsName);
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (args.contains(f.getName())) {
                    continue;
                }
                if (checkParamsExceNotAllNull(args, f, obj, argsName) == 0) {
                    return;
                }
            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsExceNotAllNull(obj, obj.getClass().getSuperclass(), argsName);
                return;
            }
        } catch (Exception e) {
            throw new ParamsException(ResponseCode.PARAMS_ERROR, args + "以外的参数不能全部为空");
        }
        throw new ParamsException(ResponseCode.PARAMS_ERROR, args + "以外的参数不能全部为空");
    }

    private static void isParamsNotNull(Object obj, Class<?> clazz, String... argsName) {
        List<String> args = Arrays.asList(argsName);
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsNotNull(args, f, obj, argsName);

            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!clazz.getClass().getSuperclass().equals(Object.class)) {
                isParamsNotNull(obj, clazz.getClass().getSuperclass(), argsName);
            }
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
    }

    private static void checkParamsNotNull(List<String> args,
                                           Field f, Object obj, String... argsName) throws IllegalAccessException {
        if (args.contains(f.getName())) {
            if (f.get(obj) == null || "".equals(f.get(obj))) {
                param.set(f.getName());
                throw new BusinessException(ResponseCode.PARAMS_ERROR);
            }
            //如果参数是List 遍历List中的参数
            if (f.getType().equals(List.class)) {
                for (Object o : (List) f.get(obj)) {
                    isParamsNotNull(o, argsName);
                }
            }
        }
    }

    private static List<String> getArgs(String[] argsName) {
        List<String> args = new ArrayList<>();
        args.add("id");
        args.add("addTime");
        args.add("updateTime");
        args.add("delFlag");
        args.addAll(Arrays.asList(argsName));
        return args;
    }

    private static void isParamsExceNotAllNull(Object obj, Class<?> clazz, String... argsName) {
        List<String> args = getArgs(argsName);
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                if (args.contains(f.getName())) {
                    continue;
                }
                if (checkParamsExceNotAllNull(args, f, obj, argsName) == 0) {
                    return;
                }

            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!clazz.getClass().getSuperclass().equals(Object.class)) {
                isParamsExceNotAllNull(obj, clazz.getClass().getSuperclass(), argsName);
                return;
            }
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        throw new BusinessException(ResponseCode.PARAMS_ERROR);
    }

    private static int checkParamsExceNotAllNull(List<String> args,
                                                 Field f, Object obj, String... argsName) throws IllegalAccessException {

        if (f.get(obj) != null && !"".equals(f.get(obj))) {
            if (f.getType().equals(List.class)) {
                for (Object o : (List) f.get(obj)) {
                    try {
                        isParamsExceNotAllNull(o, argsName);
                        return 0;
                    } catch (BusinessException e) {
                        /*FALLTHRU*/
                    }
                }
                //该List中全部都是null
                return 1;
            }
            return 0;
        }
        return 1;
    }

    private static void isParamsExceNull(Object obj, Class<?> clazz, String... argsName) {
        List<String> args = getArgs(argsName);
        try {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsExceNull(args, f, obj, argsName);

            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!clazz.getClass().getSuperclass().equals(Object.class)) {
                isParamsExceNull(obj, clazz.getClass().getSuperclass(), argsName);
            }
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
    }

    private static void checkParamsExceNull(List<String> args,
                                            Field f, Object obj, String... argsName) throws IllegalAccessException {
        if (args.contains(f.getName())) {
            return;
        }
        if (f.get(obj) == null || "".equals(f.get(obj))) {
            param.set(f.getName());
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        //如果参数是List 遍历List中的参数
        if (f.getType().equals(List.class)) {
            for (Object o : (List) f.get(obj)) {
                isParamsExceNull(o, argsName);
            }
        }
    }

    public static String setMsgResult(String msg, String ... args) {
        if (args != null && args.length != 0) {
            StringBuilder sb = new StringBuilder(msg);
            for (String arg : args) {
                sb.replace(sb.indexOf("{"), sb.indexOf("}") + 1,
                        new StringBuilder("[").append(arg).append("]").toString());
            }
            msg = sb.toString();
        }
        return msg;
    }

    /**
     * 判断一个对象中指定的参数集是否存在空值，存在抛出异常
     * 支持对象中含有List
     * 支持List遍历、父类遍历
     *
     * @param obj
     * @param argsName
     */
    public static void isParamsNotNull(Object obj, String... argsName) {
        List<String> args = Arrays.asList(argsName);
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                checkParamsNotNull(args, f, obj, argsName);
            }
            //如果含有父类，且父类不为Object，往上遍历父类
            if (!obj.getClass().getSuperclass().equals(Object.class)) {
                isParamsNotNull(obj, obj.getClass().getSuperclass(), argsName);
            }
        } catch (Exception e) {
            String errorParam = param.get();
            param.remove();
            throw new ParamsException(ResponseCode.PARAMS_ERROR, "参数：{} 不能为空", errorParam);
        }
    }
}
