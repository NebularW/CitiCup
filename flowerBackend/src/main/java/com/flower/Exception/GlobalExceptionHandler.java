package com.flower.Exception;

import com.flower.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result exception(SQLIntegrityConstraintViolationException e){
        log.error("可能这次插入的数据之前并没有出现过，或者输入信息缺失 -----> {}", e.getMessage());
        return new Result(false, "There are some information are made up or there are some critical information missing!!! Please check with your input!!!");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result exception(DuplicateKeyException e){
        log.error("插入数据有关键信息之前重复 -----> {}", e.getMessage());
        return new Result(false, "There are some critical information are repeated!!! Please check with your input!!!");
    }

}
