/*
 * Copyright 2002-2014 the original author or authors.
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
 */

package com.code.analyze.demo.type;

import java.util.Set;

public interface ClassMetadata {

	String getClassName();

	boolean isInterface();

	boolean isAnnotation();

	boolean isAbstract();

	boolean isConcrete();

	boolean isFinal();

	boolean isIndependent();

	boolean hasEnclosingClass();

	String getEnclosingClassName();

	boolean hasSuperClass();

	String getSuperClassName();

	String[] getInterfaceNames();

	Set<String> getMemberClassNames();
}
