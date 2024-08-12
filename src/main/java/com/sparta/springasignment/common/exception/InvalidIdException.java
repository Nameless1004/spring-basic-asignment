package com.sparta.springasignment.common.exception;

public class InvalidIdException extends IllegalArgumentException {

    public InvalidIdException(Long id) {
        super("존재하지 않는 id 입니다. id: " + id);
    }
}
