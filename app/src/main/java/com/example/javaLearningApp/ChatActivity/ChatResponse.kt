package com.example.javaLearningApp

data class ChatResponse(
    val id: String,
    //val object: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage,
    val system_fingerprint: String?
)

data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class Choice(
    val index: Int,
    val message: Message,
    val logprobs: Any?, // You can replace 'Any?' with a more specific type if needed
    val finish_reason: String
)

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)
data class MessageRvModel(
    var message : String,
    var sender : String,
    var senderTime : String,
    var BotTime : String
)