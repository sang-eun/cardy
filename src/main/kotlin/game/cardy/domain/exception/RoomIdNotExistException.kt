package game.cardy.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value= HttpStatus.NOT_FOUND)
class RoomIdNotExistException : Throwable(){

//    constructor() {
//        super("RoomId가 존재하지 않습니다.")
//    }
}
