package com.nobodyisperfect.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import java.util.*

@ResponseStatus(code = HttpStatus.CONFLICT)
@ResponseBody
class TooManyPlayersException(): RuntimeException(String.format("Already too many players have joined this game"))