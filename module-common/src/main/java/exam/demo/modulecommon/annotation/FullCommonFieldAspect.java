package exam.demo.modulecommon.annotation;

import exam.demo.modulecommon.common.BaseDto;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Slf4j
@Component
@Aspect
public class FullCommonFieldAspect {
    @Autowired
    SnowFlake snowFlake;

    @Pointcut("@annotation(exam.demo.modulecommon.annotation.FullCommonFieldU)")
    public void pointCutU(){}

    @Pointcut("@annotation(exam.demo.modulecommon.annotation.FullCommonField)")
    public void pointCut(){}

    @Around(value = "pointCut()")
    public Object full(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("填充公共字段");
        Object[] params = joinPoint.getArgs();
        if (CommonUtils.isEmpty(params)){
            throw new StarterException(StarterError.SYSTEM_PARAMETER_IS_NULL);
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        FullCommonField fullCommonField = signature.getMethod().getAnnotation(FullCommonField.class);
        Object[] handledParams = new Object[params.length];
        if (EnumOperation.INSERT.equals(fullCommonField.operation())){
            for (int i = 0; i < params.length; i++) {
                // 判断是否为BaseDto子类
                if (params[i] instanceof BaseDto){
                    handledParams[i] = handleDtoObjectInsert((BaseDto) params[i]);
                }
                // 如果是集合类型，则判断集合中元素是否为BaseDto子类
                else if (params[i] instanceof Collection){
                    Collection collection = (Collection) params[i];
                    if (BaseDto.class.isAssignableFrom(getElementClass(collection))){
                        handledParams[i] = handleCollectionDtoObjectInsert(collection);
                    }else {
                        handledParams[i] = params[i];
                    }
                }else {
                    handledParams[i] = params[i];
                }
            }
        }else {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof BaseDto){
                    handledParams[i] = handleDtoObjectUpdate((BaseDto) params[i]);
                }else if (params[i] instanceof Collection){
                    Collection collection = (Collection) params[i];
                    if (BaseDto.class.isAssignableFrom(getElementClass(collection))){
                        handledParams[i] = handleCollectionDtoObjectUpdate(collection);
                    }else {
                        handledParams[i] = params[i];
                    }
                }else {
                    handledParams[i] = params[i];
                }
            }
        }
        return joinPoint.proceed(handledParams);

    }


    /**
     * 处理插入时BaseDto类型
     * @param dto
     * @return
     */
    public Object handleDtoObjectInsert(BaseDto dto){
        UserPermission userPermission = TokenUtils.getUser();
        Long orgId  = userPermission.getOrgId();
        Long companyId = userPermission.getCompanyId();
        Long operator = userPermission.getId();
        dto.setId(snowFlake.nextId());
        dto.setCompanyId(companyId);
        dto.setOrgId(orgId);
        dto.setCreatedBy(operator);
        dto.setCreatedTime(new Date());
        dto.setUpdatedBy(operator);
        dto.setUpdatedTime(dto.getCreatedTime());
        dto.setVersion(dto.getCreatedTime().getTime());
        return dto;
    }

    /**
     * 处理插入时集合中含有BaseDto的情况
     * @param dtoCollection
     * @return
     */
    public Object handleCollectionDtoObjectInsert(Collection<BaseDto> dtoCollection){
        Iterator iterator = dtoCollection.iterator();
        Collection collection = new ArrayList();
        while (iterator.hasNext()){
            collection.add(handleDtoObjectInsert((BaseDto) iterator.next()));
        }
        return collection;
    }

    public Object handleDtoObjectUpdate(BaseDto dto){
        UserPermission userPermission = TokenUtils.getUser();
        dto.setVersion(System.currentTimeMillis());
        dto.setUpdatedBy(userPermission.getId());
        dto.setUpdatedTime(new Date());
        return dto;
    }

    /**
     * 对于User服务定制处理
     * @param dto
     * @return
     */
    public Object handleDtoObjectInsertU(BaseDto dto){
        UserPermission userPermission = TokenUtils.getUser();
        dto.setId(snowFlake.nextId());
        dto.setCreatedBy(userPermission.getId());
        dto.setCreatedTime(new Date());
        dto.setUpdatedBy(userPermission.getId());
        dto.setUpdatedTime(dto.getCreatedTime());
        dto.setVersion(dto.getCreatedTime().getTime());
        return dto;
    }

    public Object handleCollectionDtoObjectInsertU(Collection<BaseDto> dtoCollection){
        Iterator iterator = dtoCollection.iterator();
        Collection collection = new ArrayList();
        while (iterator.hasNext()){
            collection.add(handleDtoObjectInsertU((BaseDto) iterator.next()));
        }
        return collection;
    }

    public Object handleCollectionDtoObjectUpdate(Collection<BaseDto> dtoCollection){
        Iterator iterator = dtoCollection.iterator();
        Collection collection = new ArrayList();
        while (iterator.hasNext()){
            collection.add(handleDtoObjectUpdate((BaseDto) iterator.next()));
        }
        return collection;
    }

    /**
     * 获取集合中元素类型
     * @param collection
     * @return
     */
    public Class getElementClass(Collection collection){
        Iterator iterator = collection.iterator();
        return iterator.next().getClass();
    }

    @Around(value = "pointCutU()")
    public Object fullU(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("填充公共字段");
        Object[] params = joinPoint.getArgs();
        if (CommonUtils.isEmpty(params)){
            throw new StarterException(StarterError.SYSTEM_PARAMETER_IS_NULL);
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        FullCommonFieldU fullCommonField = signature.getMethod().getAnnotation(FullCommonFieldU.class);
        Object[] handledParams = new Object[params.length];
        if (EnumOperation.INSERT.equals(fullCommonField.operation())){
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof BaseDto){
                    handledParams[i] = handleDtoObjectInsertU((BaseDto) params[i]);
                }else if (params[i] instanceof Collection){
                    Collection collection = (Collection) params[i];
                    if (BaseDto.class.isAssignableFrom(getElementClass(collection))){
                        handledParams[i] = handleCollectionDtoObjectInsertU(collection);
                    }else {
                        handledParams[i] = params[i];
                    }
                }else {
                    handledParams[i] = params[i];
                }
            }
        }else {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof BaseDto){
                    handledParams[i] = handleDtoObjectUpdate((BaseDto) params[i]);
                }else if (params[i] instanceof Collection){
                    Collection collection = (Collection) params[i];
                    if (BaseDto.class.isAssignableFrom(getElementClass(collection))){
                        handledParams[i] = handleCollectionDtoObjectUpdate(collection);
                    }else {
                        handledParams[i] = params[i];
                    }
                }else {
                    handledParams[i] = params[i];
                }
            }
        }
        return joinPoint.proceed(handledParams);
    }
}
