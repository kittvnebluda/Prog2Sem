package com.prog2sem.client

import java.lang.Exception

/** Ошибка неправильного ввода пользователя */
class InvalidUserInputException(message:String): Exception(message)