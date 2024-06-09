package org.example.composite;

import java.util.Map;
import java.util.Vector;

public class Map2Component {
    public static void buildComponent(Component root, Map jsonMap) {

        // 遍历jsonMap，获取最后一个子节点的key
        Object lastKey = null;
        Object firstKey = null;

        for (Object key : jsonMap.keySet()) {
            firstKey = key;
            break;
        }
        for (Object key : jsonMap.keySet()) {
            lastKey = key;
        }

        for (Object key : jsonMap.keySet()) {
            Object value = jsonMap.get(key);

            // 判断当前节点是否其父亲的最后一个子节点
            boolean isLast = lastKey.equals(key);

            // 判断当前节点是否其父亲的第一个子节点
            boolean isFirst = firstKey.equals(key);

            if (value instanceof Map) {
                Composite subComponent = new Composite(key.toString(), isLast, isFirst);
                Map2Component.buildComponent(subComponent, (Map) value);
                root.add(subComponent);
            } else {
                String valueStr = "";
                // 判断value是否为null
                if (value == null) {
                    valueStr = "";
                }
                else {
                    valueStr = value.toString();
                }
                Leaf leaf = new Leaf(key.toString(), valueStr, isLast, isFirst);
                root.add(leaf);
            }
        }
    }
}
