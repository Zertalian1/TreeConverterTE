package org.example;

import java.util.*;

public class Converter {

    /**
     * Преобразует коллекцию элементов в список деревьев, элементы заполняют уровень слева на право,
     * в порядке их расположения в передаваемом множестве
     * @param entities - множество элементов дерева
     * @return список деревьев
     */
    public static List<TreeDTO> convertAllTree(Collection<TreeEntity> entities) {
        List<TreeEntity> sortedEntities = entities.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        Map<Integer, TreeDTO> treeMap = new HashMap<>();
        Map<Integer, TreeDTO> lastLevelMap = new HashMap<>();
        Integer lastNode = null;
        for (TreeEntity entity: sortedEntities) {
            if (entity.getParentId() == null) {
                TreeDTO coreEntity = treeEntityToTreeDto(entity);
                treeMap.put(coreEntity.getId(), coreEntity);
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
        return new ArrayList<>(treeMap.values());
    }

    private static TreeDTO treeEntityToTreeDto (TreeEntity entity) {
        return new TreeDTO(entity.getId(), entity.getName());
    }

}
