package org.example;

import org.junit.Assert;

import java.util.*;

/**
 * Вспомогательный интерфейс для проверки результатов конвертации
 */
public interface TreeResultAnalyzer {
    default void checkTreeDTOFields(List<TreeDTO> converted, Collection<TreeEntity> original) {
        Assert.assertEquals(getTreeSize(converted).intValue(), original.size());
        Set<Integer> originalCheckedIdList = new HashSet<>();
        for (TreeDTO tree: converted) {
            Stack<TreeDTO> treeStack = new Stack<>();
            treeStack.push(tree);
            while (!treeStack.empty()) {
                TreeDTO root = treeStack.pop();
                TreeEntity originalEntity = findInListById(original, root.getId());
                Assert.assertNotNull(originalEntity);
                originalCheckedIdList.add(originalEntity.getId());
                Assert.assertEquals(root.getId().intValue(), originalEntity.getId().intValue());
                Assert.assertEquals(root.getName(), originalEntity.getName());
                for (TreeDTO leaf : root.getChildren()) {
                    treeStack.push(leaf);
                }
            }
        }
        Assert.assertEquals(originalCheckedIdList.size(), original.size());
    }

    default TreeEntity findInListById(Collection<TreeEntity> original, Integer id) {
        return original.stream().filter(entity -> entity.getId().equals(id)).findAny().orElse(null);
    }

    default Integer getTreeSize(List<TreeDTO> treeList) {
        Stack<TreeDTO> treeStack = new Stack<>();
        for (TreeDTO tree: treeList) {
            treeStack.push(tree);
        }
        int cnt = 0;
        while (!treeStack.empty()) {
            TreeDTO root = treeStack.pop();
            cnt++;
            for (TreeDTO leaf: root.getChildren()) {
                treeStack.push(leaf);
            }
        }
        return cnt;
    }
}
