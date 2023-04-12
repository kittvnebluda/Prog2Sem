package com.prog2sem.shared.exceptions

import java.lang.Exception

/** Ошибка неправильного ввода пользователя */
class InvalidUserInputException(message: String) : Exception(message)