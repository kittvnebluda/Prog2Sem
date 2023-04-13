package com.prog2sem.shared.exceptions

import com.prog2sem.shared.net.MsgMarker

/**
 * Класс получения ошибки в сообщении
 * @param error строка с маркировкой [MsgMarker.Tags.ERR]
 */
class GotErrorMsgException(error: String) : MsgException(MsgMarker.getError(error))