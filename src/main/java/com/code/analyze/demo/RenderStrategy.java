package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;

/**
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public interface RenderStrategy {

    String render(MethodCall methodCall);
}
