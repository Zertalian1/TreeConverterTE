package org.example;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;

public abstract class ConverterTestBase extends TestCase {

    public abstract void testConvertSingleBinaryTree();
    public abstract void testConvertSeveralBinaryTree();
    public abstract void testConvertSeveralLevelBinaryTree();
    public abstract void testConvertSingleNonBinaryTree();
    public abstract void testConvertSeveralNonBinaryTree();
    public abstract void testConvertSeveralLevelNoneBinaryTree();

    // проверяет все элементы дерева на соответствие
    public void checkTreeDTOFields(List<TreeDTO> converted, Collection<TreeEntity> original) {
        Set<Integer> originalCheckedIdList = new HashSet<>();
        for (TreeDTO tree: converted) {
            Stack<TreeDTO> treeStack = new Stack<>();
            treeStack.push(tree);
            while (!treeStack.empty()) {
                TreeDTO convertedEntity = treeStack.pop();
                TreeEntity originalEntity = findEntityById(original, convertedEntity.getId());
                Assert.assertNotNull(originalEntity);
                originalCheckedIdList.add(originalEntity.getId());
                checkNodeStructure(convertedEntity, originalEntity, original);
                for (TreeDTO leaf : convertedEntity.getChildren()) {
                    treeStack.push(leaf);
                }
            }
        }
        Assert.assertEquals(originalCheckedIdList.size(), original.size());
    }

    // проверяет элементы поддерева на соответствие
    private void checkNodeStructure(TreeDTO convertedNode, TreeEntity originalNode, Collection<TreeEntity> original) {
        Assert.assertEquals(convertedNode.getId().intValue(), originalNode.getId().intValue());
        Assert.assertEquals(convertedNode.getName(), originalNode.getName());
        List<TreeEntity> originalNodeChildList = findAllByParentId(original, originalNode.getId());
        List<TreeDTO> convertedNodeChildList = convertedNode.getChildren().stream().sorted(Comparator.naturalOrder()).toList();
        Assert.assertEquals(originalNodeChildList.size(), convertedNodeChildList.size());
        for (int i=0; i<originalNodeChildList.size(); i++) {
            Assert.assertEquals(
                    originalNodeChildList.get(i).getId().intValue(),
                    convertedNodeChildList.get(i).getId().intValue()
            );
            Assert.assertEquals(
                    originalNodeChildList.get(i).getName(),
                    convertedNodeChildList.get(i).getName()
            );
        }
    }

    private TreeEntity findEntityById(Collection<TreeEntity> original, Integer id) {
        return original.stream().filter(entity -> entity.getId().equals(id)).findAny().orElse(null);
    }

    private List<TreeEntity> findAllByParentId(Collection<TreeEntity> original, Integer parentId) {
        return original.stream()
                .filter(entity -> Objects.equals(entity.getParentId(), parentId))
                .sorted(Comparator.naturalOrder())
                .toList();
    }
}
