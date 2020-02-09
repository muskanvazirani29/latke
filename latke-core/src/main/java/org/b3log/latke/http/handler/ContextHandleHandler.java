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
package org.b3log.latke.http.handler;

import org.b3log.latke.http.RequestContext;
import org.b3log.latke.ioc.BeanManager;

import java.lang.reflect.Method;

/**
 * Invokes processing method ({@link org.b3log.latke.http.function.ContextHandler#handle(RequestContext)}.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.0.0, Feb 9, 2020
 * @since 2.4.34
 */
public class ContextHandleHandler implements Handler {

    @Override
    public void handle(final RequestContext context) throws Exception {
        final RouteResolution result = (RouteResolution) context.attr(RouteHandler.MATCH_RESULT);
        final ContextHandlerMeta contextHandlerMeta = result.getContextHandlerMeta();
        final Method invokeHolder = contextHandlerMeta.getInvokeHolder();
        final BeanManager beanManager = BeanManager.getInstance();
        final Object classHolder = beanManager.getReference(invokeHolder.getDeclaringClass());
        invokeHolder.invoke(classHolder, context);
        context.handle();
    }
}
