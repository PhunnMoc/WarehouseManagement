package com.example.warehousemanagement.data.util

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.*

class WebSocketManager(
    private val baseUrl: String
) {
    private var webSocket: WebSocket? = null
    private val client = OkHttpClient()

    private val _notifications = MutableStateFlow<String>("")
    val notifications: StateFlow<String> = _notifications

    fun connect() {
        val request = Request.Builder()
            .url(baseUrl) // URL WebSocket của bạn, ví dụ: "ws://your-backend-url/ws"
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Connected to WebSocket")
                // Subscribe to topics nếu cần
                webSocket.send("{\"action\": \"subscribe\", \"topic\": \"/topic/notifications\"}")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d("WebSocket", "Received message: $text")
                _notifications.value = text
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Error: ${t.message}", t)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "WebSocket closed: $reason")
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "Client Closed")
        webSocket = null
    }
}

