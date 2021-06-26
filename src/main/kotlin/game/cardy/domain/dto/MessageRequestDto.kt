package game.cardy.domain.dto

import game.cardy.domain.MessageType

class MessageRequestDto {
    lateinit var messageType: MessageType
    var playerName: String? = null
    var content: String? = null
    var roomId: String? = null

    constructor(messageType: MessageType, playerName: String)
    constructor(messageType: MessageType, content: String, roomId:String)
    constructor(messageType: MessageType)
}
