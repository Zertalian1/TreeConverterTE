package org.example;

import java.util.*;

public class Converter {

    /**
     * Преобразует коллекцию элементов в список деревьев, элементы заполняют уровень слева на право,
     * в порядке их расположения в передаваемом множестве. Предрологается, что id родителя меньше id ребёнка
     * @param entities - множество элементов дерева
     * @return список деревьев в порядке возрастания id корня
     */
    public static List<TreeDTO> convertOrderedByLevelOnIdTree(Collection<TreeEntity> entities) {
        List<TreeEntity> sortedEntities = entities.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        List<TreeDTO> treeRoots = new ArrayList<>();
        Map<Integer, TreeDTO> lastLevelMap = new HashMap<>();
        Integer lastNode = null;
        for (TreeEntity entity: sortedEntities) {
            if (entity.getParentId() == null) {
                TreeDTO coreEntity = treeEntityToTreeDto(entity);
                treeRoots.add(coreEntity);
                lastLevelMap.put(coreEntity.getId(), coreEntity);
                continue;
            }
            if (lastNode == null){
                lastNode = entity.getParentId();
            }
            if (!lastNode.equals(entity.getParentId())) {
                lastLevelMap.remove(lastNode);
                lastNode = entity.getParentId();
            }
            TreeDTO node = treeEntityToTreeDto(entity);
            lastLevelMap.get(lastNode).getChildren().add(node);
            lastLevelMap.put(node.getId(), node);
        }
        return treeRoots.stream().sorted(Comparator.naturalOrder()).toList();
    }

    /**
     * Преобразует коллекцию элементов в список деревьев, элементы заполняют уровень слева на право,
     * в порядке их расположения в передаваемом множестве.
     * @param entities - множество элементов дерева
     * @return список деревьев в порядке возрастания id корня
     */
    public static List<TreeDTO> convertAllTree(Collection<TreeEntity> entities) {
        List<TreeDTO> treeRoots = new ArrayList<>();
        Map<Integer, TreeDTO> lastLevelMap = new HashMap<>();
        for (TreeEntity entity: entities) {
            if (entity.getParentId() == null) {
                TreeDTO coreEntity = treeEntityToTreeDto(entity);
                treeRoots.add(coreEntity);
                lastLevelMap.put(entity.getId(), coreEntity);
            }
        }
        while (!lastLevelMap.isEmpty()) {
            Map<Integer, TreeDTO> newLevelMap = new HashMap<>();
            for (TreeEntity entity: entities) {
                TreeDTO localRoot = lastLevelMap.get(entity.getParentId());
                if (localRoot != null) {
                    TreeDTO node = treeEntityToTreeDto(entity);
                    localRoot.getChildren().add(node);
                    newLevelMap.put(node.getId(), node);
                }
            }
            lastLevelMap = newLevelMap;
        }
        return treeRoots.stream().sorted(Comparator.naturalOrder()).toList();
    }

    private static TreeDTO treeEntityToTreeDto (TreeEntity entity) {
        return new TreeDTO(entity.getId(), entity.getName());
    }

}
