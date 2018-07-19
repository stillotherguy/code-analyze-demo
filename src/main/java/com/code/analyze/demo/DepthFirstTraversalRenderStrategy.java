package com.code.analyze.demo;

import com.code.analyze.demo.type.MethodCall;
import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.util.Scanner;

/**
 * 深度优先遍历渲染策略
 *
 * @author Ethan Zhang
 * @email ethan.zj@antfin.com
 */
public class DepthFirstTraversalRenderStrategy implements RenderStrategy {

    private static DepthFirstTraversalRenderStrategy strategy = new DepthFirstTraversalRenderStrategy();

    private static final String STEP_HAS_BOARD = "|   ";
    private static final String STEP_EMPTY_BOARD = "    ";
    private static final String STEP_FIRST_CHAR = "`---";
    private static final String STEP_NORMAL_CHAR = "+---";

    public static DepthFirstTraversalRenderStrategy getInstance() {
        return strategy;
    }

    @Override
    public String render(MethodCall methodCall) {
        final StringBuilder treeSB = new StringBuilder();
        recursive(0, true, "", methodCall, (deep, isLast, prefix, node) -> {
            final boolean hasChild = !node.getChildren().isEmpty();
            final String stepString = isLast ? STEP_FIRST_CHAR : STEP_NORMAL_CHAR;
            final int stepStringLength = StringUtils.length(stepString);
            treeSB.append(prefix).append(stepString);

            int costPrefixLength = 0;
            if (hasChild) {
                treeSB.append("+");
            }

            final Scanner scanner = new Scanner(new StringReader(node.toString()));
            try {
                boolean isFirst = true;
                while (scanner.hasNextLine()) {
                    if (isFirst) {
                        treeSB.append(scanner.nextLine()).append("\n");
                        isFirst = false;
                    } else {
                        treeSB
                                .append(prefix)
                                .append(StringUtils.repeat(" ", stepStringLength))
                                .append(hasChild ? "|" : " ")
                                .append(StringUtils.repeat(" ", costPrefixLength))
                                .append(scanner.nextLine())
                                .append("\n");
                    }
                }
            } finally {
                scanner.close();
            }
        });

        return treeSB.toString();
    }

    /**
     * 递归遍历
     */
    private void recursive(int deep, boolean isLast, String prefix, MethodCall node, Callback callback) {
        callback.callback(deep, isLast, prefix, node);
        if (!node.isLeaf()) {
            final int size = node.getChildren().size();
            for (int index = 0; index < size; index++) {
                final boolean isLastFlag = index == size - 1;
                final String currentPrefix = isLast ? prefix + STEP_EMPTY_BOARD : prefix + STEP_HAS_BOARD;
                recursive(
                        deep + 1,
                        isLastFlag,
                        currentPrefix,
                        node.getChildren().get(index),
                        callback
                );
            }
        }
    }

    private interface Callback {

        void callback(int deep, boolean isLast, String prefix, MethodCall node);

    }
}
