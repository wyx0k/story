package indi.wyx0k.story.core.common.impl;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.CommandHandleException;
import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.common.StoryCommandHandler;
import indi.wyx0k.story.core.common.StoryContext;
import indi.wyx0k.story.core.constant.ResponseCode;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
@Data
public class SimpleStoryCommandHandler implements StoryCommandHandler {
    private Method method;
    private Object owner;
    @Override
    public StoryContext handleCommand(StoryContext storyContext) throws CommandHandleException {
        Parameter[] parameters = method.getParameters();
        List<Object> objects = new ArrayList<>();
        Arrays.stream(parameters).forEach(parameter -> {
            Type parameterType = parameter.getType();
            Object o = JSON.parseObject(storyContext.getData(parameter.getName()),parameterType);
            objects.add(o);
        });
        Object result = null;
        try {
            result = method.invoke(owner,objects);
        } catch (Exception e){
            e.printStackTrace();
            throw new CommandHandleException("处理命令时发生了异常: "+e.getMessage());
        }
        boolean bizDealfaliure = false;
        if(result instanceof BaseResponse){
            Object msg = ((BaseResponse) result).getMsg();
            storyContext.putData(msg.getClass().getTypeName(),JSON.toJSONString(msg));
            if(ResponseCode.FAILURE.code().equals(((BaseResponse) result).getCode())){
                bizDealfaliure = true;
            }
        }else {
            storyContext.putData(result.getClass().getTypeName(),JSON.toJSONString(result));
        }

        storyContext.setLastResponse(JSON.toJSONString(result));
        if(bizDealfaliure){
            throw new CommandHandleException("处理命令时发生了异常: "+((BaseResponse) result).getMsg());
        }
        return storyContext;
    }

    @Override
    public Boolean support(String name) {
        return true;
    }

    public static void main(String[] args) {

    }
}
