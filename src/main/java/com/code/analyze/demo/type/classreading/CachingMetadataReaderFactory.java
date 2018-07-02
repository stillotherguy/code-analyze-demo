/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*


package com.code.analyze.demo.type.classreading;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CachingMetadataReaderFactory extends SimpleMethodCallReaderFactory {

    public static final int DEFAULT_CACHE_LIMIT = 256;

    private volatile int cacheLimit = DEFAULT_CACHE_LIMIT;

    @SuppressWarnings("serial")
    private final Map<String, MethodCallReader> metadataReaderCache =
            new LinkedHashMap<String, MethodCallReader>(DEFAULT_CACHE_LIMIT, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, MethodCallReader> eldest) {
                    return size() > getCacheLimit();
                }
            };


    public CachingMetadataReaderFactory() {
        super();
    }

    public void setCacheLimit(int cacheLimit) {
        this.cacheLimit = cacheLimit;
    }

    public int getCacheLimit() {
        return this.cacheLimit;
    }

    @Override
    public MethodCallReader getReader(String className, String methodName, String... argTypes) throws IOException {
        if (getCacheLimit() <= 0) {
            return super.getReader(className, methodName, argTypes);
        }
        synchronized (this.metadataReaderCache) {
            final String cacheKey = getCacheKey(className, methodName, argTypes);
            MethodCallReader metadataReader = this.metadataReaderCache.get(cacheKey);
            if (metadataReader == null) {
                metadataReader = super.getReader(className, methodName);
                this.metadataReaderCache.put(cacheKey, metadataReader);
            }
            return metadataReader;
        }
    }

    private String getCacheKey(String className, String methodName, String[] argTypes) {
        StringBuilder sb = new StringBuilder(className);
        sb.append(".")
                .append(methodName);

        if (argTypes != null) {
            for (int i = 0; i < argTypes.length; i++) {
                sb.append(".")
                        .append(argTypes[i]);
            }
        }

        return sb.toString();
    }

    public void clearCache() {
        synchronized (this.metadataReaderCache) {
            this.metadataReaderCache.clear();
        }
    }

}
*/
