package com.nobodyisperfect.backend.api.socket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(): WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS()
    }

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // the prefix to which messages should be sent to from the client
        // so that they get mapped to the socket controller annotated with @MessageMapping
        config.setApplicationDestinationPrefixes("/app")
        config.enableSimpleBroker("/topic")
    }
}