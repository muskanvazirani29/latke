/*
 * Copyright (c) 2009-present, b3log.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.b3log.latke.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Websocket session.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.0, Nov 6, 2019
 * @since 3.0.2
 */
public class WebSocketSession {

    String id;
    ChannelHandlerContext ctx;
    /**
     * 关联的 HTTP 会话.
     */
    Session session;

    Map<String, String> params = new HashMap<>();

    WebSocketSession(final ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.id = RandomStringUtils.randomAlphanumeric(16);
    }

    public void sendText(final String text) {
        ctx.writeAndFlush(new TextWebSocketFrame(text));
    }

    public String getId() {
        return id;
    }

    public String getParameter(final String name) {
        return params.get(name);
    }

    public Session getHttpSession() {
        return session;
    }
}
