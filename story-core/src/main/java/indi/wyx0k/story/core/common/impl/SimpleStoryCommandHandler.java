package indi.wyx0k.story.core.common.impl;

import com.alibaba.fastjson.JSON;
import indi.wyx0k.story.core.exeception.CommandHandleException;
import indi.wyx0k.story.core.common.BaseResponse;
import indi.wyx0k.story.core.common.StoryCommandHandler;
import indi.wyx0k.story.core.common.StoryContext;
import indi.wyx0k.story.core.constant.ResponseCode;
import indi.wyx0k.story.core.exeception.StoryEngineClientInitialExeception;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

/**
 * story
 * --
 *
 * @author wyx
 * --
 * 2020/4/11
 */
@Slf4j
@Data
public class SimpleStoryCommandHandler implements StoryCommandHandler {
    private TreeMap<Integer,SimpleStoryCommandInvokerHolder> simpleStoryCommandInvokerHolders = new TreeMap<>((o1, o2) -> {
        if(o1.intValue() > o2.intValue()){
            return 1;
        }else if(o1.intValue() <o2.intValue()){
            return -1;
        }
        return 0;
    });
    @Override
    public StoryContext handleCommand(StoryContext storyContext) throws CommandHandleException {
        simpleStoryCommandInvokerHolders.entrySet().stream().forEach(entry -> {
            Method method = entry.getValue().getMethod();
            Object owner = entry.getValue().getOwner();
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
        });
        return storyContext;
    }

    @Override
    public Boolean support(String name) {
        return true;
    }

    public void addHolder(Object owner,Method method,Integer index){
        if(null == method || null == owner){
            throw new StoryEngineClientInitialExeception("初始化命令处理器失败,处理函数或所属对象为空!");
        }
        SimpleStoryCommandInvokerHolder simpleStoryCommandInvokerHolder = new SimpleStoryCommandInvokerHolder();
        simpleStoryCommandInvokerHolder.setMethod(method);
        simpleStoryCommandInvokerHolder.setOwner(owner);
        simpleStoryCommandInvokerHolders.put(index,simpleStoryCommandInvokerHolder);
        if(log.isDebugEnabled()){
            simpleStoryCommandInvokerHolders.forEach((i,s)->{
                log.info("index-> {}, holder-> {}",i,s);
            });
        }
    }
    public static void main(String[] args) {

    }
}
