package com.nobodyisperfect.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import java.util.*

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@ResponseBody
class NotFoundException(val identifier: String, val type: String): RuntimeException(String.format("Could not find %s with identifier %s", type, identifier))