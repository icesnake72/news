package com.example.news.exception;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.TransactionalException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    return e.getMessage() + "\n데이터 무결성 제약 조건 위반 에러가 발생했습니다.";
  }

  @ExceptionHandler(TransactionalException.class)
  public String handleTransactionalException(TransactionalException e) {
    return e.getMessage() + "\n트랜잭션 에러가 발생했습니다.";
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public String handleConstraintViolationException(ConstraintViolationException e) {
    return e.getMessage() + "\n엔티티의 검증 조건에 위배되는 에러가 발생했습니다.";
  }

  @ExceptionHandler(PersistenceException.class)
  public String handlePersistenceException(PersistenceException e) {
    return e.getMessage() + "\nJPA 관련 에러가 발생했습니다.";
  }
}
